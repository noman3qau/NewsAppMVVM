package com.noman.newsapp.api

import com.noman.newsapp.common.Constants
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            /**
             * Retrofit Logging level set
             */
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            /**
             * Make a OkHttp client
             */
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            /**
             * Create Retrofit Object
             */
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        /**
         * Return the API request Object
         */
        val apiRequest by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }

}