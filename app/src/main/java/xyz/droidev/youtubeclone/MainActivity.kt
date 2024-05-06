package xyz.droidev.youtubeclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import xyz.droidev.youtubeclone.ui.SetUpNavGraph
import xyz.droidev.youtubeclone.ui.theme.YoutubeCloneTheme

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YoutubeCloneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SetUpNavGraph(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}