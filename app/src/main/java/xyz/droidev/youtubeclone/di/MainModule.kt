package xyz.droidev.youtubeclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.JacksonSerializer
import xyz.droidev.youtubeclone.BuildConfig
import xyz.droidev.youtubeclone.data.repository.VideoRepository
import javax.inject.Singleton

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun providesSupabaseClient() = createSupabaseClient(
        supabaseUrl = "https://tcsdqrbfcuqpsxehpldg.supabase.co",
        supabaseKey = BuildConfig.SUPABASE_KEY
    ){
        defaultSerializer = JacksonSerializer()
        install(Postgrest)
    }

    @Provides
    @Singleton
    fun providesVideoRepository(supabase: SupabaseClient) = VideoRepository(supabase)
}