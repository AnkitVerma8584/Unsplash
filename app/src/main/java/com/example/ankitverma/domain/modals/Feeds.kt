package com.example.ankitverma.domain.modals

data class Feeds(
    val id: String,
    val description: String,
    val image: String,
    val likes: Int,
    val userName: String,
    val userInstagram: String,
    val userImage: String
)