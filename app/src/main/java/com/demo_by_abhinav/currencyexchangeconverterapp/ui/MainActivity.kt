package com.demo_by_abhinav.currencyexchangeconverterapp.ui


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.WorkInfo
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.demo_by_abhinav.currencyexchangeconverterapp.Entity.CurrencyExchange
import com.demo_by_abhinav.currencyexchangeconverterapp.R
import com.demo_by_abhinav.currencyexchangeconverterapp.databinding.ActivityMainBinding
import com.demo_by_abhinav.currencyexchangeconverterapp.model.CurrencyRate
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: CurrencyAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
        viewModel.getDataFromAPI()
        viewModel.outputWorkInfo.observe(this, workInfoObserver())
        viewModel.currencyRatesDB.observe(this, dbDataObserver())
        binding.convertBtn.setOnClickListener {
            adapter.updateList(
                binding.currencySpinner.selectedItem.toString(),
                if (binding.editTextNumberDecimal.text.toString().isEmpty()) 0.0 else binding.editTextNumberDecimal.text.toString().toDouble()
            )
            adapter.notifyDataSetChanged()
        }
    }

    private fun initializeUI() {
        binding.progressbar.isVisible = true
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainActivityViewModel::class.java]
        binding.convertBtn.isClickable = false
        binding.currencyRecycler.apply { GridLayoutManager(this@MainActivity, 3) }
        adapter = CurrencyAdapter()
        adapter.also { binding.currencyRecycler.adapter = it }

    }

    /*made public for unit test
    *can be tested using reflect
    */
    private fun getCurrencyNames(list: ArrayList<CurrencyExchange>): ArrayList<String> {
        var arrList = ArrayList<String>()
        for (item in list) arrList.add(item.currencyName)
        return arrList
    }


    private fun setSpinner(list: ArrayList<CurrencyExchange>) {
        val arrAdapter = ArrayAdapter(this, R.layout.spinner_item, getCurrencyNames(list))
        binding.currencySpinner.adapter = arrAdapter
        val selectedCurrency = binding.currencySpinner.selectedItem.toString()
        binding.currencySpinner.setSelection(arrAdapter.getPosition(if (selectedCurrency.isNullOrEmpty()) "USD" else selectedCurrency))
    }

    private suspend fun onDataFetchedFromAPISuccess(workInfo: WorkInfo) {

        val resultJson: String = workInfo.outputData.keyValueMap[Constants.RATES_FROM_API] as String
        val mapper = jacksonObjectMapper()
        val currencyRate = mapper.readValue<CurrencyRate>(resultJson)
        val currencyExchangeList = ArrayList<CurrencyExchange>()
        for (item in currencyRate.rates) {
            currencyExchangeList.add(CurrencyExchange(item.key, item.value))
        }
        viewModel.setDataToDB(currencyExchangeList)
    }

    private fun workInfoObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }
            val workInfo = listOfWorkInfo[0]
            if (workInfo.state.isFinished && workInfo.state == WorkInfo.State.SUCCEEDED) {
                lifecycleScope.launch() {
                    onDataFetchedFromAPISuccess(workInfo)
                }
            }
        }
    }

    private fun dbDataObserver(): Observer<List<CurrencyExchange>> {
        return Observer { list ->

            if (list.isNullOrEmpty()) {
                return@Observer
            }
            setSpinner(list as ArrayList<CurrencyExchange>)
            adapter.updateList(
                list, binding.currencySpinner.selectedItem.toString(),
                if (binding.editTextNumberDecimal.text.toString().isEmpty()) 0.0 else binding.editTextNumberDecimal.text.toString().toDouble()
            )
            adapter.notifyDataSetChanged()
            binding.progressbar.isVisible = false
            binding.convertBtn.isClickable = true
        }
    }

    companion object {
        fun getStartIntent(splashScreen: SplashScreen): Intent =
            Intent(splashScreen, MainActivity::class.java)

    }

}

