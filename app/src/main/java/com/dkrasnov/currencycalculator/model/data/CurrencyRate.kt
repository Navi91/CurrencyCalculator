package com.dkrasnov.currencycalculator.model.data

import com.dkrasnov.currencycalculator.util.CurrencyValueHelper

data class CurrencyRate(val currency: Currency, val rate: Double) {
    val code = currency.code

    companion object {
        fun create(name: String, rate: Double = 1.0) = CurrencyRate(Currency(name), rate)
    }

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * hashCode + currency.hashCode()
        hashCode += 31 * hashCode + java.lang.Double.doubleToLongBits(rate).toInt()

        return hashCode
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true

        if (other !is CurrencyRate) return false


        return other.currency == currency && CurrencyValueHelper.isSameValues(rate, other.rate)
    }

    override fun toString(): String {
        return "CurrencyRate currency: ${currency.code} rate: $rate"
    }
}