package com.reactivemobile.app.presentation.ui.rates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reactivemobile.app.R
import com.reactivemobile.app.domain.RateEntry
import com.reactivemobile.app.presentation.util.CircleTransform
import com.reactivemobile.app.presentation.util.formatAmount
import com.squareup.picasso.Picasso
import java.math.BigDecimal

class RatesAdapter(val rowClickListener: View.OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private val picasso = Picasso.get()

    val circleTransform = CircleTransform()

    var rates = listOf<RateEntry>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount() = rates.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rateEntry = rates[position]
        val currency = rateEntry.currency
        val rate = rateEntry.amount
        val path = rateEntry.path

        (holder as RowViewHolder).let {
            it.setCurrencyName(currency.displayName)
            it.setCurrencyCode(currency.currencyCode)
            it.setExchangeRate(rate)
            if (path != null) {
                it.setCurrencyFlag(path)
            }
        }
    }

    inner class RowViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val currencyCode = view.findViewById<TextView>(R.id.currency_code)
        private val currencyName = view.findViewById<TextView>(R.id.currency_name)
        private val currencyFlag = view.findViewById<ImageView>(R.id.flag_image)
        private val exchangeRate = view.findViewById<EditText>(R.id.exchange_rate)

        init {
            view.setOnClickListener(rowClickListener)
        }

        fun setCurrencyCode(code: String) {
            currencyCode.text = code
            view.tag = code
        }

        fun setCurrencyFlag(path: String) {
            picasso.load(path)
                .error(R.drawable.drawable_error_loading_flag)
                .placeholder(R.drawable.drawable_image_loading)
                .transform(circleTransform)
                .into(currencyFlag)
        }

        fun setCurrencyName(text: String) {
            currencyName.text = text
        }

        fun setExchangeRate(rate: BigDecimal) =
            exchangeRate.setText(formatAmount(rate))
    }
}