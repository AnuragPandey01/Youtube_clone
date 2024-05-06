package xyz.droidev.youtubeclone.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.droidev.youtubeclone.ui.components.SearchTextField
import xyz.droidev.youtubeclone.ui.components.VideoItem

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Composable
fun Home(
    onVideoClick : (Int) -> Unit
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val videos by viewModel.videos.collectAsState()
    var query by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.getAllVideos()
    }
    LaunchedEffect(query){
        viewModel.filterVideos(query)
    }

    Column{

    }
    when(state){
        is HomeState.Idle -> {
            //Idle
        }
        is HomeState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is HomeState.Error -> {
            val msg = (state as HomeState.Error).message
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = msg,style = MaterialTheme.typography.headlineMedium)
                Button(onClick = { viewModel.getAllVideos() }) {
                    Text(text = "Retry")
                }
            }
        }
        is HomeState.Success -> {
            Column{
                SearchTextField(
                    onSearch = {
                        query = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                LazyColumn {
                    //Videos
                    items(videos){
                        VideoItem(
                            video = it,
                            modifier = Modifier
                                .clickable {
                                    onVideoClick(it.id)
                                }
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }


}

