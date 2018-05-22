package com.dkrasnov.currencycalculator.dagger

import android.content.Context
import com.dkrasnov.currencycalculator.app.CurrencyApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: CurrencyApplication) {

    @Provides
    fun provideApplication(): CurrencyApplication = application

    @Provides
    fun provideContext(application: CurrencyApplication): Context = application
}