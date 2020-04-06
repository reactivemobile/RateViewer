package com.reactivemobile.app.presentation.ui.rates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.reactivemobile.app.R

class RatesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = RatesFragment.newInstance()
        supportFragmentManager.commitNow {
            replace(
                R.id.root_container,
                fragment,
                RatesFragment.TAG
            )
        }
    }
}
