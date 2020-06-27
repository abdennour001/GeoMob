package com.example.geomob

import com.example.geomob.models.Country
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_list_item.view.*

class RecyclerAdapter(private val countries: List<Country>?)
    : RecyclerView.Adapter<RecyclerAdapter.CountryHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryHolder(inflatedView)
    }

    override fun getItemCount(): Int = countries?.size!!

    override fun onBindViewHolder(holder: RecyclerAdapter.CountryHolder, position: Int) {
        val itemCountry = countries?.get(position)
        if (itemCountry != null) {
            holder.bindCountry(itemCountry)
        }
    }


    class CountryHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var country: Country? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val showPhotoIntent = Intent(context, CountryActivity::class.java)
            showPhotoIntent.putExtra(COUNTRY_KEY, country)
            context.startActivity(showPhotoIntent)
        }

        fun bindCountry(country: Country) {
            Log.d("Country", country.toString())
            this.country = country

            val imageUrl = country.images[0]
            Picasso.get().load(imageUrl).into(view.country_poster)
            view.country_name.text = country.name
            view.country_name.setTextColor(Color.WHITE)
        }

        companion object {
            private const val COUNTRY_KEY = "country"
        }
    }

}
