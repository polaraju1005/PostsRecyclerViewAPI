package com.example.apidatarecycler.network

import com.example.apidatarecycler.model.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response
}