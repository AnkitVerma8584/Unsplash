package com.example.ankitverma.domain.repository

import com.example.ankitverma.domain.modals.Feeds
import kotlinx.coroutines.flow.Flow

interface FeedsRepository {
    suspend fun getFeeds(): List<Feeds>
}