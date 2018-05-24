package com.dkrasnov.currencycalculator.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dkrasnov.currencycalculator.R
import com.dkrasnov.currencycalculator.api.exchangerate.ExtendedCurrencyProvider
import com.dkrasnov.currencycalculator.dagger.ComponentHolder
import com.dkrasnov.currencycalculator.mvp.CurrencyRateItem
import kotlinx.android.synthetic.main.v_currency_rate_item.view.*
import javax.inject.Inject

class CurrencyRateAdapter(val listener: CurrencyRAteAdapterListener) : RecyclerView.Adapter<CurrencyRateAdapter.CurrencyRateItemViewHolder>() {

    @Inject
    lateinit var extendedCurrencyProvider: ExtendedCurrencyProvider

    var items: List<CurrencyRateItem> = listOf()
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                val value = if (s.toString().isEmpty()) 0F else s.toString().toFloat()

                items[0].value = Math.round(value * 100).toFloat() / 100
                listener.onValueChange(value)
            } catch (e: NumberFormatException) {

            }
        }
    }

    init {
        ComponentHolder.applicationComponent().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateItemViewHolder {
        val context = parent.context

        return CurrencyRateItemViewHolder(listener,
                LayoutInflater.from(context).inflate(R.layout.v_currency_rate_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onBindViewHolder(holder: CurrencyRateItemViewHolder, position: Int) {
        val item = items[position]
        val value = item.value

        val extendedCurrency = extendedCurrencyProvider.getExtendedCurrencyByCode(item.code)

        holder.itemView.codeTextView.text = extendedCurrency?.code
        holder.itemView.nameTextView.text = extendedCurrency?.name
        holder.itemView.iconImageView.setImageResource(extendedCurrency?.flag ?: -1)

        val editText = holder.itemView.valueEditText
        editText.removeTextChangedListener(textWatcher)

        if (Math.abs(value) < 0.00001F) {
            editText.setText("")
        } else if (position != 0 || editText.text.toString() != item.value.toString()) {
            editText.setText(item.value.toString())
        }

        editText.isEnabled = position == 0

        if (position == 0) {
            editText.addTextChangedListener(textWatcher)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClicked(item)
        }
    }

    class CurrencyRateItemViewHolder(listener: CurrencyRAteAdapterListener, itemView: View) : RecyclerView.ViewHolder(itemView)

    interface CurrencyRAteAdapterListener {
        fun onValueChange(value: Float)

        fun onItemClicked(item: CurrencyRateItem)
    }
}