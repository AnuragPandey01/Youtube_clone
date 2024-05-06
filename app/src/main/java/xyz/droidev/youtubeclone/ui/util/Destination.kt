package xyz.droidev.youtubeclone.ui.util

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
sealed class Destination(val route: String) {
    data object Home: Destination("home")
    data object VideoDetail: Destination("video_detail/{videoId}"){
        fun createRoute(videoId: Int) = "video_detail/$videoId"
    }
}