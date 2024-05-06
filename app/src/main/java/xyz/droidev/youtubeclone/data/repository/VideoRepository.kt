package xyz.droidev.youtubeclone.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import xyz.droidev.youtubeclone.data.model.Video
import java.util.Locale.filter

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
class VideoRepository (
    private val supabase: SupabaseClient
){

    suspend fun getAllVideos() = supabase.from("videos").
    select(Columns.raw("id, title, description, image, likes, views, channel(id, name, image)")).decodeList<Video>()

    suspend fun getVideoById(id: Int) = supabase
        .from("videos")
        .select (
            columns = Columns.raw(
                "id, title, description, image, likes, views, channel(id, name, image)"
            )
        ){
            filter{
                Video::id eq id
            }
        }.decodeSingle<Video>()

}