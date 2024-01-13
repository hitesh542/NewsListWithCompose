package com.hb.composeapp.screen

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hb.composeapp.MainViewModel
import com.hb.composeapp.component.AppBar
import com.hb.composeapp.component.BannerImage
import com.hb.composeapp.component.BodyText1
import com.hb.composeapp.component.FullScreenErrorView
import com.hb.composeapp.component.FullScreenLoadingView
import com.hb.composeapp.component.HeaderText
import com.hb.composeapp.component.RoundedCornerCard
import com.hb.composeapp.data.Article
import com.hb.composeapp.data.NewsResponse
import com.hb.composeapp.data.Resource
import com.hb.composeapp.ui.theme.black100
import com.hb.composeapp.ui.theme.blue100
import com.hb.composeapp.ui.theme.cardBackground

@Composable
fun MainScreen(mainViewModel: MainViewModel = MainViewModel(), navController: NavController? = null) {
    val apiResult = mainViewModel.newsResponseLiveData.observeAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() },
    ) { paddings ->
        Surface(modifier = Modifier.padding(paddings)) {
            when (apiResult.value) {
                is Resource.Failure -> {
                    FullScreenErrorView((apiResult.value as Resource.Failure<NewsResponse?>).error ?: "") {
                        mainViewModel.loadData()
                    }
                }

                is Resource.Loading -> {
                    FullScreenLoadingView()
                }

                is Resource.Success -> {
                    LazyColumn {
                        items((apiResult.value as Resource.Success<NewsResponse?>).data!!.articles!!) { article ->
                            NewsItem(newsArticle = article) {
                                val json = Uri.encode(Gson().toJson(article))
                                navController?.navigate("news_detail/${json}")
                            }
                        }
                    }
                }

                null -> {
                    FullScreenErrorView {
                        mainViewModel.loadData()
                    }
                }
            }

        }
    }
}

@Composable
fun NewsItem(newsArticle: Article, onClick: () -> Unit = {}) {
    with(newsArticle) {
        RoundedCornerCard {
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }) {
                BannerImage(url = urlToImage, contentDescription = description ?: "")
                NewsContent(title, description, author, publishedAt)
            }
        }
    }
}

@Composable
fun NewsContent(headerText: String?, description: String?, author: String?, publishedAt: String?, space: Dp = 4.dp) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        color = MaterialTheme.colorScheme.cardBackground
    ) {
        Column {
            if (headerText != null) {
                HeaderText(text = headerText)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(space)
                )
            }

            if (description != null) {
                BodyText1(text = description)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(space)
                )
            }

            if (author != null) {
                Text(
                    text = "~${author}",
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.blue100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(space)
                )
            }

            if (publishedAt != null) {
                Text(
                    text = publishedAt,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.black100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

