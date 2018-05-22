package com.dkrasnov.currencycalculator.mvp

import com.dkrasnov.currencycalculator.model.data.CurrencyRate

data class CurrencyRateItem(val currencyRate: CurrencyRate, val value: Float) {

    override fun toString(): String {
        return "CurrencyRateItem rate: $currencyRate value: $value"
    }
}