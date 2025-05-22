package com.alireza.brochure.core.ui.asyncImageLoader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.alireza.brochure.core.R

@Composable
fun AsyncImageLoader(modifier: Modifier= Modifier, imageUrl: String, contentDescription: String? = null){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .scale(Scale.FIT)
            .build(),
        placeholder = painterResource(id = R.drawable.placeholder_brochure),
        error = painterResource(id = R.drawable.placeholder_brochure),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}