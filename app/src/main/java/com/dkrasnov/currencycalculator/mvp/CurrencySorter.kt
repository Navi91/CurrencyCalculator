package com.dkrasnov.currencycalculator.mvp

import com.dkrasnov.currencycalculator.model.data.Currency
import com.dkrasnov.currencycalculator.model.data.CurrencyRate

class CurrencySorter() {

    private var orderMap = mutableMapOf<String, Int>()

    fun setOrder(orderList: List<Currency>) {
        orderList.forEachIndexed({ index, currency ->
            orderMap[currency.code] = index
        })
    }

    fun haveOrder() = orderMap.isNotEmpty()

    fun setFirstCurrency(currency: Currency) {
        val currentPosition = orderMap[currency.code] ?: return

        orderMap.entries.forEach {
            if (it.value < currentPosition) {
                orderMap[it.key] = it.value + 1
            }
        }

        orderMap[currency.code] = 0
    }

    fun sort(currencyRateList: List<CurrencyRate>): List<CurrencyRate> {
        val result = currencyRateList.toMutableList()

        currencyRateList.forEach {
            val position = orderMap[it.code] ?: throw IllegalStateException()
            result[position] = it
        }

        return result
    }
}