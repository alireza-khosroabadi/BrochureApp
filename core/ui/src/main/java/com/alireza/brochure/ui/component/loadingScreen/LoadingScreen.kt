package com.alireza.brochure.ui.component.loadingScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun LoadingComponent(){
    Box(modifier = Modifier.fillMaxSize().testTag("Loading"), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}