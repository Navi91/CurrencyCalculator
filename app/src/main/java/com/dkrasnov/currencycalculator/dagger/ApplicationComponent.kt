package com.dkrasnov.currencycalculator.dagger

import com.dkrasnov.currencycalculator.mvp.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, CurrencyCalculatorModule::class])
interface ApplicationComponent {

    fun inject(mainPresenter: MainPresenter)

}