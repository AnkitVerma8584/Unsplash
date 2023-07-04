package com.example.ankitverma.data.remote.mappers

import com.example.ankitverma.domain.modals.Feeds
import com.example.ankitverma.data.remote.modals.Images

fun List<Images>.mapToFeeds(): List<Feeds> = this.map {
    Feeds(
        it.id,
        it.description ?: "",
        it.urls.regular,
        it.likes,
        it.user.name ?: "",
        it.user.instagram_username ?: "",
        it.user.profile_image.medium
    )
}