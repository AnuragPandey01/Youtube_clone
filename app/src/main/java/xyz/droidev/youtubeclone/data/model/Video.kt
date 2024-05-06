package xyz.droidev.youtubeclone.data.model

import androidx.annotation.Keep

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Keep
data class Video(
    val id: Int,
    val title: String,
    val image: String,
    val description : String,
    val views: Int,
    val likes: Int,
    val channel: Channel
)