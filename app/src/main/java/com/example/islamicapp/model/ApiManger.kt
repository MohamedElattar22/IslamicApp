package com.example.islamy_project.api.model

import android.util.Log
import com.example.islamicapp.Data.Constants

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManger {
    companion object {
        private var retrofit: Retrofit? = null


        fun getInstance(): Retrofit {
            //http interceptor > 2
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.e("api", message)
                    }

                })
            //call okHTTP > 2
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            //call retrofit >1
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    //2
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
        fun getWebService(): WebService {
            return getInstance().create(WebService::class.java)
        }
    }
//3 to implement the instance class

}