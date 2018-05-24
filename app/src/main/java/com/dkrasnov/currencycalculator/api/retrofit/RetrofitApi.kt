package com.dkrasnov.currencycalculator.api.retrofit

import com.dkrasnov.currencycalculator.model.response.CurrencyRateListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/latest")
    fun getCurrencyRateList(@Query("base") currencyName: String): Flowable<CurrencyRateListResponse>
}