package xyz.droidev.youtubeclone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import xyz.droidev.youtubeclone.ui.screens.Home
import xyz.droidev.youtubeclone.ui.screens.VideoDetails
import xyz.droidev.youtubeclone.ui.util.Destination

/**
 * Project : Youtube clone.
 * @author PANDEY ANURAG.
 */
@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        composable(Destination.Home.route){
            Home{
                navController.navigate(Destination.VideoDetail.createRoute(it))
            }
        }
        composable(
            Destination.VideoDetail.route,
            arguments = listOf(
                navArgument("videoId"){
                    type = NavType.IntType
                }
            )
        ){
            val videoId = it.arguments?.getInt("videoId")
            VideoDetails(videoId!!)
        }
    }
}