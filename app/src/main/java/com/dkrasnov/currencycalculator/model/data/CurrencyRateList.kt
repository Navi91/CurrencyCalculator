package com.dkrasnov.currencycalculator.model.data

class CurrencyRateList(val baseCurrencyRate: CurrencyRate, val currencyRateList: List<CurrencyRate>) {

    fun changeBaseCurrencyRate(newBaseCurrencyRate: CurrencyRate): CurrencyRateList {
        val newBaseRate = newBaseCurrencyRate.rate

        val newCurrencyRateList = currencyRateList.map {
            CurrencyRate(it.currency, it.rate.div(newBaseRate))
        }

        return CurrencyRateList(newBaseCurrencyRate, newCurrencyRateList)
    }

    override fun toString(): String {
        return "CurrencyRateList base: $baseCurrencyRate rates: $currencyRateList"
    }
}