package com.example.shopapp.data.local.user

import android.provider.ContactsContract.Data
import android.util.Log
import com.example.shopapp.data.BaseRepository
import com.example.shopapp.data.SignInException
import com.example.shopapp.data.UniqueEmailException
import com.example.shopapp.data.local.settings.AppSettings
import com.example.shopapp.data.local.user.models.SignInTuple
import com.example.shopapp.domain.AppException
import com.example.shopapp.domain.LocalResponse
import com.example.shopapp.domain.user.SignUpData
import com.example.shopapp.domain.user.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appSettings: AppSettings,
    private val usersDao: UsersDao,
) : BaseRepository(), UserRepository {

    private val currentAccount = MutableStateFlow(appSettings.getCurrentAccountId())

    override suspend fun signIn(email: String): LocalResponse<Unit> = doRequest {
        usersDao.signIn(email).also {
            setCurrentAccount(it?.id)
        }
    }

    override suspend fun signUp(data: SignUpData): LocalResponse<Unit> = doRequest {
        delay(2000)
        val validate = usersDao.findUserByEmail(data.email)
        if(validate?.id != null) throw UniqueEmailException()
        usersDao.signUp(data.toData())
    }

    override suspend fun signOut(): LocalResponse<Unit> = doRequest {
        appSettings.setCurrentAccountId(AppSettings.UNDEFINED_ID)
        currentAccount.value = AppSettings.UNDEFINED_ID
    }

    override suspend fun getAuthState(): LocalResponse<Boolean> = doRequest {
        currentAccount.value != AppSettings.UNDEFINED_ID
    }

    private fun setCurrentAccount(id:Long?) {
        currentAccount.value = id?:throw SignInException()
    }
}

