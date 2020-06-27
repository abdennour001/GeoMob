package com.example.geomob

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_image_swap_layout.*
import java.util.EnumSet.range


class CountryImageFragment : Fragment() {

    private val URL = "URL"
    private val POSITION = "POSITION"
    private val COUNT = "COUNT"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_image_swap_layout, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(arguments?.getString(URL)).into(country_image)
        image_count.text = "${arguments?.getInt(POSITION)!!+1}/${arguments?.getInt(COUNT)}"
        prepareDots(view, dots_container, arguments?.getInt(POSITION)!!, arguments?.getInt(COUNT)!!)
    }

    private fun prepareDots(view: View, dotsContainer: LinearLayout, position: Int, count: Int) {
        if (dotsContainer.childCount >= 0) {
            dotsContainer.removeAllViews()

            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            for (i in 0..count) {
                val dot = ImageView(context)
                if (i == position) {
                    dot.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.active_dot) })
                } else {
                    dot.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.inactive_dot) })
                }
                dotsContainer.addView(dot, params)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(image: String, position: Int, count: Int) =
            CountryImageFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, image)
                    putInt(POSITION, position)
                    putInt(COUNT, count)
                }
            }
    }
}
