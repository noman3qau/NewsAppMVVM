package com.noman.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noman.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticlesDao(): ArticleDao

    companion object {
        /**
         * Declare the Singleton instance of Room Database
         */
        @Volatile
        private var instance: ArticleDatabase? = null
        private val Lock = Any()

        /**
         * Get the Database instance
         */
        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it }
        }

        /**
         * Create Database Object
         */
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articles_db.db"
            ).build()
    }


}