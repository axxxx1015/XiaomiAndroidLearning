package com.example.day11

data class ListItem(
    val type: Int,
    val text: String?,
    val imageUrl: String?,
    var liked: Boolean
) {
    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_IMAGE = 1
    }
} 