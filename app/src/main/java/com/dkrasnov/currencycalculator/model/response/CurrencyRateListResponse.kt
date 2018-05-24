package com.dkrasnov.currencycalculator.model.response

class CurrencyRateListResponse {

    private var base: String = ""
    private var date: String = ""
    private var rates: MutableMap<String, Float> = mutableMapOf()

    fun getBase() = base

    fun getRates() = rates

    override fun toString(): String {
        return "CurrencyRateListResponse base: $base date: $date rates: $rates"
    }
}