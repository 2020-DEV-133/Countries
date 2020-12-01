package com.meg.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meg.countries.model.Country

class ListViewModel : ViewModel() {

    val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>>
        get() = _countries
    val _countryLoadError = MutableLiveData<Boolean>()
    val countryLoadError : LiveData<Boolean>
        get() = _countryLoadError
    val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading
    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI")
        )

        _countryLoadError.value = false
        _loading.value = false;
        _countries.value = mockData
    }

}