package com.hb.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hb.composeapp.component.FullScreenErrorView
import com.hb.composeapp.component.FullScreenLoadingView
import com.hb.composeapp.screen.MainScreen
import com.hb.composeapp.screen.NewsDetailScreen
import com.hb.composeapp.screen.NewsItem
import com.hb.composeapp.data.Article
import com.hb.composeapp.data.NavTypeArticle
import com.hb.composeapp.data.dummyArticle
import com.hb.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigationController(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationController(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "latest_news") {
        composable("latest_news") {
            MainScreen(mainViewModel = mainViewModel, navController = navController)
        }

        composable(
            route = "news_detail/{article}",
            arguments = listOf(navArgument(name = "article") {
                type = NavTypeArticle()
            })
        ) {
            val article = it.arguments?.getParcelable<Article>("article")
            NewsDetailScreen(newsArticle = article!!, navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeAppTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun NewsArticleItem() {
    ComposeAppTheme {
        NewsItem(dummyArticle)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    ComposeAppTheme {
        FullScreenLoadingView()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ComposeAppTheme {
        FullScreenErrorView {

        }
    }
}