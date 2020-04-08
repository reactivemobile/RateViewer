package com.reactivemobile.app.presentation.ui.rates.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.reactivemobile.app.domain.FetchRatesUseCase
import com.reactivemobile.app.domain.RateData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class RatesViewModelTest {

    lateinit var ratesViewModel: RatesViewModel

    private val fetchRatesUseCase = object : FetchRatesUseCase {
        override suspend fun getRates(baseCurrency: String): RateData {
            return if (baseCurrency == "EUR") rateData else errorRateData
        }

        override fun setBaseAmount(amount: BigDecimal): RateData {
            return rateData
        }
    }

    @Mock
    private lateinit var ratesObserver: Observer<RateData>

    @Mock
    private lateinit var errorObserver: Observer<Boolean>

    private val rateData: RateData = RateData()

    private val errorRateData: RateData = RateData(error = true)

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        setupViewModel()
    }

    @Test
    fun `test start fetching rates when successful notifies observers`() {
        ratesViewModel.fetchRates("EUR")

        verify(ratesObserver).onChanged(eq(rateData))
    }

    @Test
    fun `test start fetching rates when failed notifies observers`() {
        ratesViewModel.fetchRates("GBP")

        verify(errorObserver).onChanged(eq(true))
    }

    @Test
    fun `test set base amount notifies observers`() {
        ratesViewModel.setBaseAmount("100.0")

        verify(ratesObserver).onChanged(eq(rateData))
    }

    private fun setupViewModel() {
        ratesViewModel = RatesViewModel(fetchRatesUseCase)
        ratesViewModel.rates.observeForever(ratesObserver)
        ratesViewModel.error.observeForever(errorObserver)
    }
}