package com.dkrasnov.currencycalculator.ui.activity

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.dkrasnov.currencycalculator.R
import com.dkrasnov.currencycalculator.mvp.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
    }
}
