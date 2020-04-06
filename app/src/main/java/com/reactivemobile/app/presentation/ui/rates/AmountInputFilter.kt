package com.reactivemobile.app.presentation.ui.rates

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class AmountInputFilter : InputFilter {

    private val pattern = Pattern.compile("^[0-9]*(\\.)?[0-9]*\$")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {

        val output = dest.toString().replaceRange(dstart, dend, source!!.subSequence(start, end))

        var ret: String? = null

        if (output == ".") {
            ret = "0."
        }

        val matcher = pattern.matcher(output)
        if (!matcher.matches()) {
            ret= ""
        }

        return ret
    }
}