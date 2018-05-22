package com.dkrasnov.currencycalculator.model.data

data class CurrencyRate(val currency: Currency, val rate: Float) {
    val name = currency.name

    companion object {
        fun create(name: String, rate: Float = 1F) = CurrencyRate(Currency(name), rate)
    }
}