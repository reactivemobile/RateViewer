package com.reactivemobile.app

import android.app.Application
import com.reactivemobile.app.injection.component.AppComponent
import com.reactivemobile.app.injection.component.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}