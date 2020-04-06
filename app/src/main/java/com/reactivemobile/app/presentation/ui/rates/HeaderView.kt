package com.reactivemobile.app.presentation.ui.rates

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.reactivemobile.app.R
import com.reactivemobile.app.domain.RateEntry
import com.reactivemobile.app.presentation.util.CircleTransform
import com.reactivemobile.app.presentation.util.formatAmount
import com.squareup.picasso.Picasso

class HeaderView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var currencyCode: TextView
    private var currencyName: TextView
    private var currencyFlag: ImageView
    private val exchangeRate: TextView

    private val picasso = Picasso.get()

    private val circleTransform = CircleTransform()

    init {
        inflate(getContext(), R.layout.list_item, this)

        currencyCode = findViewById(R.id.currency_code)
        currencyName = findViewById(R.id.currency_name)
        currencyFlag = findViewById(R.id.flag_image)
        exchangeRate = findViewById(R.id.exchange_rate)
        exchangeRate.text = "1.0"
        exchangeRate.isEnabled = true
        exchangeRate.editableText.filters = arrayOf(AmountInputFilter())
    }

    fun setRateEntry(rateEntry: RateEntry, updateAmount: Boolean = false) {
        val currency = rateEntry.currency
        currencyName.text = currency.displayName
        currencyCode.text = currency.currencyCode

        if (updateAmount) {
            exchangeRate.text = formatAmount(rateEntry.amount)
        }

        picasso.load(rateEntry.path)
            .error(R.drawable.drawable_error_loading_flag)
            .placeholder(R.drawable.drawable_image_loading)
            .transform(circleTransform)
            .into(currencyFlag)
    }

    fun setTextChangedListener(listener: (Editable?) -> Unit) {
        exchangeRate.doAfterTextChanged(listener)
    }
}