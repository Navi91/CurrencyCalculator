package com.dkrasnov.currencycalculator.ui.adapter.diffutils

import android.support.v7.util.DiffUtil
import android.util.Log
import com.dkrasnov.currencycalculator.mvp.CurrencyRateItem

class CurrencyRateItemsDiffUtils(private val oldItems: List<CurrencyRateItem>, private val newItems: List<CurrencyRateItem>)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name == newItems[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].value == newItems[newItemPosition].value
                && ((newItemPosition != 0 && oldItemPosition != 0) || (newItemPosition == 0 && oldItemPosition == 0))
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size
}