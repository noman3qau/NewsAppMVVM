package com.noman.newsapp.repository

import android.provider.SyncStateContract
import com.noman.newsapp.api.RetrofitInstance
import com.noman.newsapp.common.Constants
import com.noman.newsapp.db.ArticleDatabase
import com.noman.newsapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {

    ///// Below APIs Functions
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.apiRequest.getBreakingNews(
            countryCode,
            pageNumber
        )

    suspend fun searchForNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.apiRequest.searchForNews(
            searchQuery,
            pageNumber
        )

    ///// Below DB Functions
    suspend fun upsertArticle(article: Article) {
        db.getArticlesDao().upsertArticle(article)
    }

    fun getAllSavedArticle() = db.getArticlesDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticlesDao().deleteArticle(article)
}