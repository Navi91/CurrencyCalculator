package com.dkrasnov.currencycalculator.ui.fragment

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dkrasnov.currencycalculator.R
import com.dkrasnov.currencycalculator.mvp.CurrencyRateItem
import com.dkrasnov.currencycalculator.mvp.MainPresenter
import com.dkrasnov.currencycalculator.mvp.MainView
import com.dkrasnov.currencycalculator.ui.adapter.CurrencyRateAdapter
import com.dkrasnov.currencycalculator.ui.adapter.diffutils.CurrencyRateItemsDiffUtils
import kotlinx.android.synthetic.main.f_main.*

class MainFragment : MvpAppCompatFragment(), MainView, CurrencyRateAdapter.CurrencyRAteAdapterListener {


    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var adapter: CurrencyRateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.f_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CurrencyRateAdapter(this)
//        adapter.setHasStableIds(true)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        mainPresenter.resume()
    }

    override fun onPause() {
        super.onPause()

        mainPresenter.pause()
    }

    override fun updateData(items: List<CurrencyRateItem>) {
        val scrollToTop = isTopCurrencyChanged(adapter.items, items)

        val diffResult = DiffUtil.calculateDiff(CurrencyRateItemsDiffUtils(adapter.items, items))
        adapter.items = items
        diffResult.dispatchUpdatesTo(adapter)

        if (scrollToTop) recyclerView.smoothScrollToPosition(0)
    }

    override fun onValueChange(value: Float) {
        mainPresenter.changeBaseValue(value)
    }

    override fun onItemClicked(item: CurrencyRateItem) {
        mainPresenter.changeBaseCurrencyRateAndValue(item.currencyRate, item.value)
    }

    private fun isTopCurrencyChanged(oldItems: List<CurrencyRateItem>, newItems: List<CurrencyRateItem>): Boolean {
        val oldTopItem = oldItems.getOrNull(0)
        val newTopItem = newItems.getOrNull(0)

        if (oldTopItem == null || newTopItem == null) return false

        return oldTopItem.code != newTopItem.code
    }
}