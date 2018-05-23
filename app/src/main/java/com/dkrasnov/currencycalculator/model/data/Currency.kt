package com.dkrasnov.currencycalculator.model.data

data class Currency(val name: String) {

    override fun hashCode(): Int {
        var hashCode = 17

        hashCode += 31 * hashCode + name.hashCode()

        return hashCode
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true

        if (other !is Currency) return false

        return name == other.name
    }

    override fun toString(): String {
        return "Currency $name"
    }
}