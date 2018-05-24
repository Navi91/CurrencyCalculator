package com.dkrasnov.currencycalculator.model.data

data class CurrencyRate(val currency: Currency, val rate: Float) {
    val code = currency.code

    companion object {
        fun create(name: String, rate: Float = 1F) = CurrencyRate(Currency(name), rate)
    }

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * hashCode + currency.hashCode()
        hashCode += 31 * hashCode + java.lang.Float.floatToIntBits(rate)

        return hashCode
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true

        if (other !is CurrencyRate) return false

        return other.currency == currency && Math.abs(rate.minus(other.rate)) < 0.00001F
    }

    override fun toString(): String {
        return "CurrencyRate currency: ${currency.code} rate: $rate"
    }
}