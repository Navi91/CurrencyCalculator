package com.dkrasnov.currencycalculator.app

import android.app.Application
import com.dkrasnov.currencycalculator.dagger.ComponentHolder

class CurrencyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentHolder.initApplicationComponents(this)
    }
}