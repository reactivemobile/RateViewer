package com.reactivemobile.app.injection.component

import com.reactivemobile.app.injection.module.AppModule
import com.reactivemobile.app.injection.module.RatesNetworkModule
import com.reactivemobile.app.presentation.ui.rates.RatesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RatesNetworkModule::class])
interface AppComponent {
    fun inject(fragment: RatesFragment)
}