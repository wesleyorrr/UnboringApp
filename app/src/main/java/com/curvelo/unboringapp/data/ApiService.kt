package com.curvelo.unboringapp.data


import com.curvelo.unboringapp.model.Bored
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
        @GET("api/activity/")
        fun apiRandomActivity(): Call<Bored>
    }
