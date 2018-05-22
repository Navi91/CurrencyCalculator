package com.dkrasnov.currencycalculator.model.response

class RateListResponse {

    private var base: String = ""
    private var date: String = ""
    private var rates: MutableMap<String, Float> = mutableMapOf()

    fun getBase() = base

    fun getRates() = rates

    override fun toString(): String {
        return "RateListResponse base: $base date: $date rates: $rates"
    }
}