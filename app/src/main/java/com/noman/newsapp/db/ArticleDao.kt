package com.noman.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noman.newsapp.models.Article

@Dao
interface ArticleDao {
    /**
     * This function is used to insert or replaced the Articles record
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article): Long

    /**
     * This function is used to get All Articles from the Database
     */
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    /**
     * This function is used to delete the given Article
     */
    @Delete
    suspend fun deleteArticle(article: Article)
}