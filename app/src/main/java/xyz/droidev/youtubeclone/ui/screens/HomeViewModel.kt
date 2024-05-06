package xyz.droidev.youtubeclone.ui.screens

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
class HomeViewModel @Inject constructor(
    private val videoRepository: VideoRepository
): ViewModel(){

    private var allVideo: List<Video> = emptyList()
    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos = _videos.asStateFlow()
    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)
    val state = _state.asStateFlow()

    fun getAllVideos(){
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try{
                _videos.value = videoRepository.getAllVideos()
                allVideo = videos.value
                _state.value = HomeState.Success
            }catch (e: Exception){
                _state.value = HomeState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun filterVideos(query: String){
        _videos.value = allVideo.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}

sealed class HomeState{
    data object Idle: HomeState()
    data object Loading: HomeState()
    data object Success: HomeState()
    data class Error(val message: String): HomeState()
}