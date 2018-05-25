package com.dkrasnov.currencycalculator.util

class CurrencyValueHelper {

    companion object {
        fun roundValue(value: Double) = Math.round(value * 100).toDouble() / 100

        fun valuePresentationEquals(presentation: String, value: Double): Boolean {
            return try {
                val parsedValue = presentation.toDouble()

                isSameValues(parsedValue, value)
            } catch (e: NumberFormatException) {
                false
            }
        }

        fun isSameValues(value1: Double, value2: Double) = Math.abs(value1.minus(value2)) < 0.00001F
    }
}