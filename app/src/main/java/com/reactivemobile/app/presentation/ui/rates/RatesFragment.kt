package com.reactivemobile.app.presentation.ui.rates

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.reactivemobile.app.App
import com.reactivemobile.app.R
import com.reactivemobile.app.presentation.ui.rates.adapter.RatesAdapter
import com.reactivemobile.app.presentation.ui.rates.viewmodel.RatesViewModel
import com.reactivemobile.app.presentation.ui.rates.viewmodel.RatesViewModelFactory
import kotlinx.android.synthetic.main.rates_fragment.*
import javax.inject.Inject

class RatesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: RatesViewModelFactory

    private val adapter: RatesAdapter by lazy { RatesAdapter(rowClickListener) }

    private val viewModel: RatesViewModel by activityViewModels { viewModelFactory }

    private val rowClickListener = View.OnClickListener { v: View ->
        fetchRates(v.tag as String)
    }

    companion object {
        fun newInstance() = RatesFragment()
        const val TAG = "RatesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rates_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        setupViewModel()

        fetchRates()
    }

    private fun setupView() {
        recycler_view.adapter = adapter
        base_currency_view.setTextChangedListener { e -> viewModel.setBaseAmount(e) }
    }

    private fun fetchRates(currency: String? = null) {
        viewModel.startFetchingRates(currency)
    }

    private fun setupViewModel() {

        viewModel.rates.observe(viewLifecycleOwner, Observer {
            adapter.rates = it.rates
            base_currency_view.setRateEntry(it.baseCurrency)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            showMessage(getString(R.string.error_loading_data))
        })

        viewModel.rates.value?.let {
            base_currency_view.setRateEntry(it.baseCurrency, true)
        }
    }

    private fun showMessage(message: String) =
        Snackbar.make(main_view, message, Snackbar.LENGTH_SHORT).show()
}
