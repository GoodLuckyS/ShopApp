package com.example.shopapp.data.local.settings

import android.content.Context
import com.example.shopapp.data.local.settings.AppSettings.Companion.UNDEFINED_ID

class SharedPreferencesAppSettings(context: Context) : AppSettings {

    private val sharedPreferences =
        context.getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE)


    override fun getCurrentAccountId(): Long = sharedPreferences.getLong(
        PREF_NODE_ACCOUNT_ID,
        UNDEFINED_ID
    )

    override fun setCurrentAccountId(id: Long) {
        sharedPreferences.edit()
            .putLong(PREF_NODE_ACCOUNT_ID,id)
            .apply()
    }

    override fun isAuth(): Boolean = getCurrentAccountId() != UNDEFINED_ID



    companion object {
        private const val PREF_KEY_SETTINGS = "SETTINGS"
        private const val PREF_NODE_ACCOUNT_ID = "currentAccountId"
    }

}