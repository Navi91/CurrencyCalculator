package com.dkrasnov.currencycalculator.mvp

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.dkrasnov.currencycalculator.api.exchangerate.CurrencyRateApi
import com.dkrasnov.currencycalculator.dagger.ComponentHolder
import com.dkrasnov.currencycalculator.model.data.Currency
import com.dkrasnov.currencycalculator.model.data.CurrencyRate
import com.dkrasnov.currencycalculator.model.data.CurrencyRateList
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var currencyRateApi: CurrencyRateApi

    private var baseCurrencyRate: CurrencyRate = createDefaultCurrencyRate()
    private var baseCurrencyValue: Float = 0F
    private lateinit var currentCurrencyRateList: CurrencyRateList

    private var disposable: Disposable? = null
    private val sorter: CurrencySorter = CurrencySorter()

    init {
        ComponentHolder.applicationComponent().inject(this)
    }

    fun requestData() {

        disposable = Flowable.interval(1, TimeUnit.SECONDS)
                .map { baseCurrencyRate.currency }
                .flatMap {
                    currencyRateApi.requestCurrencyRateList(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("main_trace", "onNext $it")

                    if (!sorter.haveOrder()) {
                        sorter.setOrder(it.currencyRateList.map { it.currency })
                    }

                    changeCurrencyRateList(it)
                }, {
                    Log.d("main_trace", "onError $it")
                })
    }

    private fun changeCurrencyRateList(currencyRateList: CurrencyRateList) {
        currentCurrencyRateList = currencyRateList

        updateViewState()
    }

    fun changeBaseCurrencyRateAndValue(currencyRate: CurrencyRate, value: Float) {
        disposable?.dispose()

        sorter.setFirstCurrency(currencyRate.currency)
        currentCurrencyRateList = currentCurrencyRateList.changeBaseCurrencyRate(currencyRate)
        baseCurrencyRate = currencyRate
        baseCurrencyValue = value

        updateViewState()

        requestData()
    }

    fun changeBaseValue(value: Float) {
        baseCurrencyValue = value

        updateViewState()
    }

    private fun updateViewState() {
        val sortedList = sorter.sort(currentCurrencyRateList.currencyRateList)

        val items = sortedList.map { CurrencyRateItem(it, baseCurrencyValue * it.rate) }

        Log.d("main_trace", "updateViewState $items")
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
    }

    private fun createDefaultCurrencyRate() = CurrencyRate(Currency("EUR"), 1F)
}