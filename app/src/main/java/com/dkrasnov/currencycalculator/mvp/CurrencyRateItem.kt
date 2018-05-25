package com.dkrasnov.currencycalculator.mvp

import com.dkrasnov.currencycalculator.model.data.CurrencyRate

data class CurrencyRateItem(val currencyRate: CurrencyRate, var value: Double) {

    val code = currencyRate.code

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * hashCode + currencyRate.hashCode()
        hashCode += 31 * hashCode + java.lang.Double.doubleToLongBits(value).toInt()

        return hashCode
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true

        if (other !is CurrencyRateItem) return false

        return currencyRate == other.currencyRate && Math.abs(value.minus(other.value)) < 0.00001F
    }

    override fun toString(): String {
        return "CurrencyRateItem rate: $currencyRate value: $value"
    }
}