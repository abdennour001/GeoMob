package com.example.geomob

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class YoutubePagerAdapter(fragmentManager: FragmentManager, private val videos: List<String>?)
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return YoutubeVideosFragment.newInstance(videos?.get(position)!!)
    }


    override fun getCount(): Int {
        return videos?.size!!
    }

}