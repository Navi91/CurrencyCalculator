package com.dkrasnov.currencycalculator.api.exchangerate

import com.dkrasnov.currencycalculator.api.retrofit.RetrofitApi
import com.dkrasnov.currencycalculator.model.data.Currency
import com.dkrasnov.currencycalculator.model.data.CurrencyRate
import com.dkrasnov.currencycalculator.model.data.CurrencyRateList
import io.reactivex.Flowable
import javax.inject.Inject

class CurrencyRateApi @Inject constructor(private val retrofitApi: RetrofitApi) {

    fun requestCurrencyRateList(currency: Currency): Flowable<CurrencyRateList> {
        return retrofitApi.getCurrencyRateList(currency.name).map {
            val baseCurrencyRate = CurrencyRate.create(it.getBase())
            val currencyRates = mutableListOf(baseCurrencyRate)

            for (rateEntry in it.getRates()) {
                currencyRates.add(CurrencyRate(Currency(rateEntry.key), rateEntry.value))
            }

            return@map CurrencyRateList(baseCurrencyRate, currencyRates)
        }
    }
}