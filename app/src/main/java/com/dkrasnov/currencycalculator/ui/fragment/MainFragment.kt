package com.dkrasnov.currencycalculator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dkrasnov.currencycalculator.mvp.MainPresenter
import com.dkrasnov.currencycalculator.mvp.MainView
import com.dkrasnov.currencycalculator.R

class MainFragment : MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.f_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainPresenter.requestData()
    }
}