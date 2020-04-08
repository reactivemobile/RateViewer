package com.reactivemobile.app.presentation.ui.rates

import android.text.SpannableString
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AmountInputFilterTest {

    private val amountInputFilter = AmountInputFilter()

    @Test
    fun filterFiltersCharactersCorrectly() {
        assertEquals(
            "0.",
            amountInputFilter.filter(".", 0, 1, SpannableString(""), 0, 0)
        )
    }
}