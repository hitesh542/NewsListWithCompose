package com.hb.composeapp.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Article>?)

@Parcelize
data class Article(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) : Parcelable

val dummyArticle = Article(
    author = "Gear Team",
    title = "CES 2024: The 26 Best Gadgets You Can Buy Right Now",
    description = "From workout headphones to an iPhone case with a physical keyboard, here’s everything announced at the big tech trade show that you can actually order—or preorder—today.",
    url = "https://www.wired.com/story/ces-2024-gadgets-you-can-buy-right-now/",
    urlToImage = " \"https://media.wired.com/photos/659f9fb15baaf77c7f3dd011/191:100/w_1280,c_limit/01.09.24_WIRED__CES_1584-2.jpg",
    publishedAt = "2024-01-11T18:49:16Z",
    content = "It's barely two weeks into the new year, but the flood of new gadgets for 2024 has already started. Thanks to CES, we've seen a ton of innovative tech launch into the marketplace. While many products… [+16853 chars]"
)

class NavTypeArticle : NavType<Article>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Article {
        return Gson().fromJson(value, Article::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putParcelable(key, value)
    }
}
