package com.reactivemobile.app.presentation.ui.rates.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reactivemobile.app.data.remote.Repository
import com.reactivemobile.app.domain.FetchRatesUseCase

class RatesViewModelFactory(val fetchRatesUseCase: FetchRatesUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RatesViewModel(fetchRatesUseCase) as T
    }
}