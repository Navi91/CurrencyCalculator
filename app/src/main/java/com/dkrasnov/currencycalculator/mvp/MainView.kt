package com.dkrasnov.currencycalculator.mvp

import com.arellomobile.mvp.MvpView

interface MainView: MvpView {

    fun updateData(items: List<CurrencyRateItem>)
}