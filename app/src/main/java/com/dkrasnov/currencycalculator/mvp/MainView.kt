package com.dkrasnov.currencycalculator.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateData(items: List<CurrencyRateItem>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(message: String)
}