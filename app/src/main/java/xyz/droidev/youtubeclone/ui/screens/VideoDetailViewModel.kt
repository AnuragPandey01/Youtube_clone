package xyz.droidev.youtubeclone.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.droidev.youtubeclone.data.model.Video
import xyz.droidev.youtubeclone.data.repository.VideoRepository
import javax.inject.Inject

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@HiltViewModel
class VideoDetailViewModel @Inject constructor(
    private val videoRepository: VideoRepository
): ViewModel(){

    private val _state = MutableStateFlow<VideoDetailState>(VideoDetailState.Idle)
    val state = _state.asStateFlow()

    fun getVideoData(videoId: Int){
        _state.value = VideoDetailState.Loading
        viewModelScope.launch {
            try{
                val video = videoRepository.getVideoById(videoId)
                Log.d(TAG, "getVideoData:  $video")
                _state.value = VideoDetailState.Success(video)
            }catch (e: Exception){
                _state.value = VideoDetailState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    companion object{
        private const val TAG = "VideoDetailViewModel"
    }
}

sealed class VideoDetailState{
    data object Idle: VideoDetailState()
    data object Loading: VideoDetailState()
    data class Success(val videoDetail : Video): VideoDetailState()
    data class Error(val message: String): VideoDetailState()
}