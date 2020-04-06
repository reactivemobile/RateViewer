package com.reactivemobile.app.injection.module

import com.reactivemobile.app.data.remote.RatesNetworkService
import com.reactivemobile.app.data.remote.Repository
import com.reactivemobile.app.domain.FetchRatesUseCase
import com.reactivemobile.app.domain.FetchRatesUseCaseImpl
import com.reactivemobile.app.domain.ResponseToRateDataMapper
import com.reactivemobile.app.presentation.ui.rates.viewmodel.RatesViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import java.math.BigDecimal
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(networkService: RatesNetworkService): Repository {
        return Repository(networkService)
    }

    @Provides
    @Singleton
    fun providesFetchUseCase(repository: Repository): FetchRatesUseCase =
        FetchRatesUseCaseImpl(repository, ResponseToRateDataMapper(), BigDecimal(1))

    @Provides
    @Singleton
    fun provideViewModelFactory(fetchRatesUseCase: FetchRatesUseCase): RatesViewModelFactory {
        return RatesViewModelFactory(fetchRatesUseCase)
    }
}