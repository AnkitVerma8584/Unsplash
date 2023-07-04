package com.example.ankitverma.data.remote.modals

data class Images(
    val id: String,
    val description: String?,
    val urls: Urls,
    val likes: Int,
    val user: User
)
