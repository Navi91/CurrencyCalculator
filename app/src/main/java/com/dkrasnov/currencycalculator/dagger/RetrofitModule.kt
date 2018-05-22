package com.dkrasnov.currencycalculator.dagger

import com.dkrasnov.currencycalculator.api.retrofit.RetrofitApi
import com.dkrasnov.currencycalculator.api.retrofit.RetrofitCreator
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        val retrofit = RetrofitCreator().create()

        return retrofit.create(RetrofitApi::class.java)
    }
}