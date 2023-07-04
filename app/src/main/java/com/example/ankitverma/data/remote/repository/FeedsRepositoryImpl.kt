package com.example.ankitverma.data.remote.repository

import com.example.ankitverma.data.remote.api.ImagesApi
import com.example.ankitverma.data.remote.mappers.mapToFeeds
import com.example.ankitverma.domain.modals.Feeds
import com.example.ankitverma.domain.repository.FeedsRepository

class FeedsRepositoryImpl(
    private val imagesApi: ImagesApi
) : FeedsRepository {

    override suspend fun getFeeds(): List<Feeds> {
        return try {
            val response = imagesApi.getImages()
            if (response.isSuccessful && response.body() != null)
                response.body()!!.mapToFeeds()
            else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}