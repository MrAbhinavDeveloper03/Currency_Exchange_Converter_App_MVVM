package com.demo_by_abhinav.currencyexchangeconverterapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo_by_abhinav.currencyexchangeconverterapp.Entity.CurrencyExchange
import com.demo_by_abhinav.currencyexchangeconverterapp.R
import com.demo_by_abhinav.currencyexchangeconverterapp.databinding.FragmentCurrencyListBinding

class CurrencyAdapter : RecyclerView.Adapter<MainCurrencyViewHolder>() {
    private val convertedList: ArrayList<CurrencyExchange> = ArrayList<CurrencyExchange>()
    private var currentRateList: ArrayList<CurrencyExchange> = ArrayList<CurrencyExchange>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCurrencyViewHolder {
        val v = FragmentCurrencyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainCurrencyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MainCurrencyViewHolder, position: Int) {

        if(convertedList!=null){
            val currentItem = convertedList[position]
            holder.binding.value.text= String.format("%.3f",currentItem.rate)
            holder.binding.currencyName.text= currentItem.currencyName
        }
    }





    override fun getItemCount(): Int {
        return convertedList.size
    }
    fun updateList(list:List<CurrencyExchange>,conversionFrom:String,amountToConvert: Double){
        var selCurrValInUSD=1.0
        if(list!==null){
            convertedList.clear()
            currentRateList.clear()
            for(item in list){
                if(conversionFrom == item.currencyName){
                    selCurrValInUSD=1.0/item.rate
                    break;
                }
            }
            for (item in list)
            {
                convertedList.add(CurrencyExchange(item.currencyName,amountToConvert*item.rate*selCurrValInUSD))
                currentRateList.add(item)
            }
        }
    }

    fun updateList(conversionFrom:String,amountToConvert: Double){
        convertedList.clear()
        var selCurrValInUSD:Double=1.0
        if(currentRateList!==null){
            for(item in currentRateList){
                if(conversionFrom == item.currencyName){
                    selCurrValInUSD=1.0/item.rate
                    break;
                }
            }
            for (item in currentRateList)
            {
                convertedList.add(CurrencyExchange(item.currencyName,amountToConvert*item.rate*selCurrValInUSD))
            }
        }
    }
}
class MainCurrencyViewHolder(val binding: FragmentCurrencyListBinding) : RecyclerView.ViewHolder(binding.root) {
}