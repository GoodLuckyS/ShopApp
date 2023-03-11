package com.example.shopapp.data.local

import com.example.shopapp.data.BaseRepository
import com.example.shopapp.data.local.settings.AppSettings
import com.example.shopapp.data.local.user.UsersDao
import com.example.shopapp.data.local.user.models.UserUpdateImageTuple
import com.example.shopapp.data.local.user.models.mapToData
import com.example.shopapp.data.utils.AuthException
import com.example.shopapp.data.utils.SignInException
import com.example.shopapp.data.utils.UniqueException
import com.example.shopapp.domain.user.UserRepository
import com.example.shopapp.domain.user.models.SignUpData
import com.example.shopapp.domain.user.models.User
import com.example.shopapp.domain.utils.AppResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appSettings: AppSettings,
    private val usersDao: UsersDao,
) : BaseRepository(), UserRepository {

    private val currentAccount = MutableStateFlow(appSettings.getCurrentAccountId())

    override suspend fun signIn(firstName: String): AppResponse<Unit> = doRequest {
        usersDao.findUserByFirstName(firstName).also { setCurrentAccount(it?.id) }
    }

    override suspend fun signUp(data: SignUpData): AppResponse<Unit> = doRequest {
        delay(500)
        if (!validateEmail(data.email) || !validateFirstName(data.firstName)) throw UniqueException()
        usersDao.signUp(data.mapToData()).also { signIn(data.firstName) }
    }

    override suspend fun signOut(): AppResponse<Unit> = doRequest {
        if (currentAccount.value == AppSettings.UNDEFINED_ID) throw AuthException()
        appSettings.setCurrentAccountId(AppSettings.UNDEFINED_ID)
        currentAccount.value = appSettings.getCurrentAccountId()
    }

    override suspend fun getUserById(): AppResponse<Flow<User>> = doRequest {
        usersDao.getUserById(currentAccount.value).mapNotNull {
            it?.mapToDomain()
        }
    }

    override suspend fun getUserImage(): AppResponse<Flow<String>> = doRequest {
        usersDao.getUserImage(currentAccount.value)
    }

    override fun getAuthState(): Flow<Boolean> = flow { emit(appSettings.isAuth()) }

    override suspend fun updateUserImage(uri: String): AppResponse<Unit> = doRequest {
        if (currentAccount.value == AppSettings.UNDEFINED_ID) throw AuthException()
        usersDao.updateUserImage(UserUpdateImageTuple(currentAccount.value, uri))
    }

    override suspend fun signInWithGoogle(): AppResponse<Unit> = doRequest {
        if (!validateEmail(GOOGLE_SIGNUP_DATA.email) && !validateFirstName(GOOGLE_SIGNUP_DATA.firstName))
            signIn(GOOGLE_SIGNUP_DATA.firstName)
        else signUp(GOOGLE_SIGNUP_DATA)
    }

    private suspend fun validateEmail(email: String) = usersDao.findUserByEmail(email)?.id == null

    private suspend fun validateFirstName(firstName: String) =
        usersDao.findUserByFirstName(firstName)?.id == null

    private fun setCurrentAccount(id: Long?) {
        appSettings.setCurrentAccountId(id ?: throw SignInException())
        currentAccount.value = id
    }

    companion object {
        val GOOGLE_SIGNUP_DATA = SignUpData("Google", "Android", "")
    }
}

