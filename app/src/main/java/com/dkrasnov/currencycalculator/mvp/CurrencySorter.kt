package com.dkrasnov.currencycalculator.mvp

import com.dkrasnov.currencycalculator.model.data.Currency
import com.dkrasnov.currencycalculator.model.data.CurrencyRate
import java.util.*

class CurrencySorter() {

    private var orderList: List<Currency> = listOf()

    fun setOrder(orderList: List<Currency>) {
        this.orderList = orderList
    }

    fun haveOrder() = orderList.isNotEmpty()

    fun setFirstCurrency(currency: Currency) {
        Collections.swap(orderList, 0, orderList.indexOf(currency))
    }

    fun sort(currencyRateList: List<CurrencyRate>): List<CurrencyRate> {
        val result = currencyRateList.toMutableList()

        currencyRateList.forEach {
            result[orderList.indexOf(it.currency)] = it
        }

        return result
    }
}