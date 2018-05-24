package com.dkrasnov.currencycalculator.api.exchangerate

import com.mynameismidori.currencypicker.ExtendedCurrency

class ExtendedCurrencyProvider() {

    private val extendedCurrencyCacheMap = mutableMapOf<String, ExtendedCurrency?>()

    fun getExtendedCurrencyByCode(code: String): ExtendedCurrency? {
        if (extendedCurrencyCacheMap.containsKey(code)) {
            return extendedCurrencyCacheMap.get(code)
        } else {
            val extendedCurrency = ExtendedCurrency.getAllCurrencies().findLast { it.code == code }
            extendedCurrencyCacheMap.put(code, extendedCurrency)

            return extendedCurrency
        }
    }
}