package com.example.apidatarecycler.network

import android.content.Context
import android.preference.DialogPreference
import android.util.Log
import com.example.apidatarecycler.model.Response
import com.example.apidatarecycler.utils.AppUtils.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun getPosts(context: Context, callback: (Response) -> Unit) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val posts = service.getPosts()
                CoroutineScope(Dispatchers.Main).launch {
                    callback(posts)
                }
            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    Log.e("API_Exception", "$e")
                }
            }
        }
    }
}