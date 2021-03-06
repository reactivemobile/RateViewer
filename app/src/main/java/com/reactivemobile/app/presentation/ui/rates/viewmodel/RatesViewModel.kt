package com.reactivemobile.app.presentation.ui.rates.viewmodel

import androidx.lifecycle.*
import com.reactivemobile.app.domain.FetchRatesUseCase
import com.reactivemobile.app.domain.RateData
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class RatesViewModel(private val fetchRatesUseCase: FetchRatesUseCase) : ViewModel(),
    LifecycleObserver {
    val rates = MutableLiveData<RateData>()

    val error = MutableLiveData<Boolean>()

    lateinit var scheduledFuture: ScheduledFuture<*>

    private var isPaused = false

    private var baseCurrency = "EUR"

    private val timerTask = object : TimerTask() {
        override fun run() {
            if (!isPaused) {
                fetchRates(baseCurrency)
            }
        }
    }

    private val scheduler = Executors.newScheduledThreadPool(1)

    fun startFetchingRates(currency: String?) {
        if (currency != null) {
            baseCurrency = currency
        }

        if (!::scheduledFuture.isInitialized) {
            scheduledFuture = scheduler.scheduleAtFixedRate(timerTask, 0L, 1, TimeUnit.SECONDS)
        }
    }

    fun fetchRates(baseCurrency: String) {
        viewModelScope.launch {
            val rateData = fetchRatesUseCase.getRates(baseCurrency)

            handleRateDataResponse(rateData)
        }
    }

    fun setBaseAmount(amount: String) {
        val amountNumber =
            try {
                BigDecimal(if (amount.isEmpty()) 1.0 else amount.toDouble())
            } catch (exception: NumberFormatException) {
                BigDecimal(0)
            }

        val rateData = fetchRatesUseCase.setBaseAmount(amountNumber)
        handleRateDataResponse(rateData)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun paused() {
        isPaused = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumed() {
        isPaused = false
    }

    private fun handleRateDataResponse(rateData: RateData) {
        if (rateData.error) {
            error.postValue(true)
        } else {
            rates.postValue(rateData)
        }
    }
}