package com.dkrasnov.currencycalculator.dagger

import com.dkrasnov.currencycalculator.app.CurrencyApplication

class ComponentHolder {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun initApplicationComponents(application: CurrencyApplication) {
            applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(application)).build()
        }

        fun applicationComponent() = applicationComponent
    }
}