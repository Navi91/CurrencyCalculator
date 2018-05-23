package com.dkrasnov.currencycalculator.mvp

import com.dkrasnov.currencycalculator.model.data.CurrencyRate

data class CurrencyRateItem(val currencyRate: CurrencyRate, val value: Float) {

    val name = currencyRate.name

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * currencyRate.hashCode()
        hashCode += 31 * java.lang.Float.floatToIntBits(value)

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