package com.hb.composeapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun BannerImage(url: String?, contentDescription: String = "", size: Dp = 180.dp) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(size),
        color = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(
                model = url,
                contentScale = ContentScale.FillBounds,
            ),
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription,
        )
    }
}
