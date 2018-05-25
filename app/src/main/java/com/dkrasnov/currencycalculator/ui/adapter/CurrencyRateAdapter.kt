package com.dkrasnov.currencycalculator.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.dkrasnov.currencycalculator.R
import com.dkrasnov.currencycalculator.api.exchangerate.CurrencyValueHelper
import com.dkrasnov.currencycalculator.api.exchangerate.ExtendedCurrencyProvider
import com.dkrasnov.currencycalculator.dagger.ComponentHolder
import com.dkrasnov.currencycalculator.mvp.CurrencyRateItem
import kotlinx.android.synthetic.main.v_currency_rate_item.view.*
import javax.inject.Inject

class CurrencyRateAdapter(val listener: CurrencyRAteAdapterListener) : RecyclerView.Adapter<CurrencyRateAdapter.CurrencyRateItemViewHolder>() {

    @Inject
    lateinit var extendedCurrencyProvider: ExtendedCurrencyProvider

    var items: List<CurrencyRateItem> = listOf()
    private val valueTextWatcher = CurrencyValueTextWatcher()

    init {
        ComponentHolder.applicationComponent().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateItemViewHolder {
        val context = parent.context

        return CurrencyRateItemViewHolder(LayoutInflater.from(context).inflate(R.layout.v_currency_rate_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onBindViewHolder(holder: CurrencyRateItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    inner class CurrencyRateItemViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CurrencyRateItem, position: Int) {
            bindCurrencyInfo(item, position, itemView)

            val editText = itemView.valueEditText

            bindValueAndTextWatcher(item, position, editText)
            bindOnFocusChangeListener(item, position, editText)
            bindOnCLickListener(item, position, itemView, editText)
        }

        private fun bindCurrencyInfo(item: CurrencyRateItem, position: Int, itemView: View) {
            val extendedCurrency = extendedCurrencyProvider.getExtendedCurrencyByCode(item.code)

            itemView.codeTextView.text = extendedCurrency?.code
            itemView.nameTextView.text = extendedCurrency?.name
            itemView.iconImageView.setImageResource(extendedCurrency?.flag ?: -1)
        }

        private fun bindValueAndTextWatcher(item: CurrencyRateItem, position: Int, editText: EditText) {
            editText.removeTextChangedListener(valueTextWatcher)

            bindValue(item, position, editText)

            if (position == 0) {
                editText.setSelection(editText.text.length)
                valueTextWatcher.setCallback {
                    val newValue = CurrencyValueHelper.roundValue(it)
                    items[0].value = newValue
                    listener.onValueChange(newValue)
                }
                editText.addTextChangedListener(valueTextWatcher)
            }
        }

        private fun bindValue(item: CurrencyRateItem, position: Int, editText: EditText) {
            val value = item.value

            if (CurrencyValueHelper.isSameValues(value, 0F)) {
                editText.setText("")
            } else if (!CurrencyValueHelper.valuePresentationEquals(editText.text.toString(), item.value)) {
                editText.setText(item.value.toString())
            }
        }

        private fun bindOnFocusChangeListener(item: CurrencyRateItem, position: Int, editText: EditText) {
            if (position == 0) {
                editText.onFocusChangeListener = null
            } else {
                editText.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        listener.onItemClicked(item)
                    }
                }
            }
        }

        private fun bindOnCLickListener(item: CurrencyRateItem, position: Int, itemView: View, editText: EditText) {
            itemView.setOnClickListener {
                if (position != 0) {
                    listener.onItemClicked(item)
                    editText.requestFocus()
                }
            }
        }
    }

    class CurrencyValueTextWatcher : TextWatcher {
        private var valueChangeCallback: ((Float) -> Unit?)? = null

        override fun afterTextChanged(s: Editable?) {
            try {
                val value = if (s.toString().isEmpty()) 0F else s.toString().toFloat()

                valueChangeCallback?.invoke(value)
            } catch (e: NumberFormatException) {

            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        fun setCallback(callback: (Float) -> Unit) {
            valueChangeCallback = callback
        }
    }

    interface CurrencyRAteAdapterListener {
        fun onValueChange(value: Float)

        fun onItemClicked(item: CurrencyRateItem)
    }
}