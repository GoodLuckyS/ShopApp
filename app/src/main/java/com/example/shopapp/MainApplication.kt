package com.example.shopapp

import android.app.Application
import com.example.shopapp.di.DaggerAppComponent

class MainApplication : Application() {

    val appComponent by lazy {
      DaggerAppComponent.factory().create(this)
    }

}