package com.dkrasnov.currencycalculator.dagger

import com.dkrasnov.currencycalculator.api.exchangerate.CurrencyRateApi
import com.dkrasnov.currencycalculator.api.exchangerate.ExtendedCurrencyProvider
import com.dkrasnov.currencycalculator.api.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides

@Module(includes = [(RetrofitModule::class)])
class CurrencyCalculatorModule {

    @Provides
    fun provideCurrencyRateApi(retrofitApi: RetrofitApi) = CurrencyRateApi(retrofitApi)

    @Provides
    fun provideExtendedCurrencyProvider() = ExtendedCurrencyProvider()
}