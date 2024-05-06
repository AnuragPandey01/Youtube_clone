package xyz.droidev.youtubeclone.data.model

import androidx.annotation.Keep

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Keep
data class Channel(
    val id: Int,
    val name: String,
    val image: String
)