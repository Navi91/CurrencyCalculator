package com.dkrasnov.currencycalculator.api.exchangerate

class CurrencyValueHelper {

    companion object {
        fun roundValue(value: Float) = Math.round(value * 100).toFloat() / 100

        fun valuePresentationEquals(presentation: String, value: Float): Boolean {
            return try {
                val parsedValue = presentation.toFloat()

                Math.abs(parsedValue.minus(value)) < 0.0001F
            } catch (e: NumberFormatException) {
                false
            }
        }

        fun isSameValues(value1: Float, value2: Float) = Math.abs(value1.minus(value2)) < 0.0001F
    }
}