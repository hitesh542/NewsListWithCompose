package com.hb.composeapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hb.composeapp.component.AppBar
import com.hb.composeapp.component.BannerImage
import com.hb.composeapp.data.Article

@Composable
fun NewsDetailScreen(newsArticle: Article, navController: NavController?) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(title = "News", isBack = true) {
                navController?.navigateUp()
            }
        },
    ) { paddings ->
        Surface(modifier = Modifier.padding(paddings)) {
            with(newsArticle) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    BannerImage(url = urlToImage, contentDescription = description ?: "", size = 240.dp)
                    NewsContent(title, description, author, publishedAt, space = 12.dp)
                }
            }
        }
    }
}