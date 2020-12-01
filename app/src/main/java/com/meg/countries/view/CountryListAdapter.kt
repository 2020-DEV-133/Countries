package com.meg.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meg.countries.R
import com.meg.countries.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private val countries : ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries : List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindCountries(country: Country) = with(itemView) {
            countryName.text = country.countryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false))
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       holder.bindCountries(countries[position])
    }
}