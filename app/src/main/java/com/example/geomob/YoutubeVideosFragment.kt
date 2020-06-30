package com.example.geomob

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.youtube_video_swap.*




class YoutubeVideosFragment : Fragment() {

    private val ID = "ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.youtube_video_swap, container, false)
    }

    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        youtube_web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }

        val webSettings = youtube_web_view.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        youtube_web_view.loadUrl("https://www.youtube.com/embed/${arguments?.getString(ID)}")
        Log.d("####", "https://www.youtube.com/embed/${arguments?.getString(ID)}")
    }
    companion object {
        @JvmStatic
        fun newInstance(video: String) =
            YoutubeVideosFragment().apply {
                arguments = Bundle().apply {
                    putString(ID, video)
                }
            }
    }
}
