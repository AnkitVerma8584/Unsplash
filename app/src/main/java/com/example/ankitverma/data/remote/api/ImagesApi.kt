package com.example.ankitverma.data.remote.api

import com.example.ankitverma.BuildConfig
import com.example.ankitverma.data.remote.Urls
import com.example.ankitverma.data.remote.modals.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET(Urls.GET_RANDOM_PHOTOS)
    suspend fun getImages(
        @Query("count") count: Int = Urls.COUNT,
        @Query("client_id") client_id: String = BuildConfig.ACCESS_KEY
    ): Response<List<Images>>

}