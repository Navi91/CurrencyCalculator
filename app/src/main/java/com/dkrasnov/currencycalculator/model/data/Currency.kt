package com.dkrasnov.currencycalculator.model.data

data class Currency(val code: String) {

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * hashCode + code.hashCode()

        return hashCode
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true

        if (other !is Currency) return false

        return code == other.code
    }

    override fun toString(): String {
        return "Currency $code"
    }
}