package com.noman.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.noman.newsapp.R
import com.noman.newsapp.ui.NewsActivity
import com.noman.newsapp.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleNewsFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    val args: ArticleNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article

        article.url?.let {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }

        fab.setOnClickListener {
            viewModel.insertArticle(article)
            Snackbar.make(view, "Article saved successfully.", Snackbar.LENGTH_SHORT).show()
        }

    }

}