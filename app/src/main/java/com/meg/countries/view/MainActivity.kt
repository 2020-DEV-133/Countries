package com.meg.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meg.countries.R
import com.meg.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ListViewModel
    lateinit var countryListAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        initViews()
        observeData()
    }

    private fun initViews() {
        countryListAdapter = CountryListAdapter(arrayListOf())
        countriesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = countryListAdapter
        }
    }

    private fun observeData() {
        viewModel.countries.observe(this, Observer {countries ->
             countries?.let {
                 countriesList.visibility = View.VISIBLE
                 countryListAdapter.updateCountries(it)
             }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let {
              tv_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    tv_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }
}