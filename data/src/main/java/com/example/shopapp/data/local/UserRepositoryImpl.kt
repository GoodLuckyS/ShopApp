package com.example.shopapp.data.local

import com.example.shopapp.data.AuthException
import com.example.shopapp.data.BaseRepository
import com.example.shopapp.data.SignInException
import com.example.shopapp.data.UniqueEmailException
import com.example.shopapp.data.local.settings.AppSettings
import com.example.shopapp.data.local.user.UsersDao
import com.example.shopapp.data.local.user.models.UserUpdateImageTuple
import com.example.shopapp.data.local.user.models.mapToData
import com.example.shopapp.domain.user.UserRepository
import com.example.shopapp.domain.user.models.SignUpData
import com.example.shopapp.domain.user.models.User
import com.example.shopapp.domain.utils.AppResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appSettings: AppSettings,
    private val usersDao: UsersDao,
) : BaseRepository(), UserRepository {

    private val currentAccount = MutableStateFlow(appSettings.getCurrentAccountId())

    override suspend fun signIn(email: String): AppResponse<Unit> = doRequest {
        usersDao.signIn(email).also {
            setCurrentAccount(it?.id)
        }
    }

    override suspend fun signUp(data: SignUpData): AppResponse<Unit> = doRequest {
        delay(1000)
        if (!validate(data.email)) throw UniqueEmailException()
        usersDao.signUp(data.mapToData()).also {
            signIn(data.email)
        }
    }

    override suspend fun signOut(): AppResponse<Unit> = doRequest {
        if (currentAccount.value == AppSettings.UNDEFINED_ID) throw AuthException()
        appSettings.setCurrentAccountId(AppSettings.UNDEFINED_ID)
        currentAccount.value = appSettings.getCurrentAccountId()
    }

    override suspend fun getUserById(): AppResponse<User> = doRequest {
        usersDao.getUserById(currentAccount.value).mapToDomain()
    }

    override fun getAuthState(): Flow<Boolean> = flow { emit(appSettings.isAuth()) }

    override suspend fun updateUserImage(uri: String): AppResponse<Unit> = doRequest {
        if (currentAccount.value == AppSettings.UNDEFINED_ID) throw AuthException()
        usersDao.updateUserImage(UserUpdateImageTuple(currentAccount.value, uri))
    }

    override suspend fun signInWithGoogle(): AppResponse<Unit> = doRequest {
        if (!validate(GOOGLE_SIGNIN_DATA_SOAP)) signIn(GOOGLE_SIGNIN_DATA_SOAP)
        else signUp(GOOGLE_SIGNUP_DATA_SOAP)
    }

    private suspend fun validate(email: String) =
        usersDao.findUserByEmail(email)?.id == null

    private fun setCurrentAccount(id: Long?) {
        appSettings.setCurrentAccountId(id ?: throw SignInException())
        currentAccount.value = id
    }

    companion object {
        val GOOGLE_SIGNUP_DATA_SOAP = SignUpData("Google", "Android", "")
        const val GOOGLE_SIGNIN_DATA_SOAP = ""
    }
}

