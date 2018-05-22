package com.dkrasnov.currencycalculator.api.retrofit

import com.dkrasnov.currencycalculator.model.response.RateListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/latest")
    fun getCurrencyRateList(@Query("name") currencyName: String): Flowable<RateListResponse>
}