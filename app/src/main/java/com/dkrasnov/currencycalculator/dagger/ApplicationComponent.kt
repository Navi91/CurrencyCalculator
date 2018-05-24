package com.dkrasnov.currencycalculator.dagger

import com.dkrasnov.currencycalculator.mvp.MainPresenter
import com.dkrasnov.currencycalculator.ui.adapter.CurrencyRateAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, CurrencyCalculatorModule::class])
interface ApplicationComponent {

    fun inject(mainPresenter: MainPresenter)

    fun inject(mainPresenter: CurrencyRateAdapter)
}