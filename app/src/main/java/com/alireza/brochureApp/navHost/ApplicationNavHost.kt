package com.alireza.brochureApp.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alireza.brochure.brochureDetail.screen.BrochureDetailScreen
import com.alireza.brochure.feature_brochure.brochure.ui.BrochureListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Navigation.BrochureList
    ){
        composable<Navigation.BrochureList> {
            BrochureListScreen(onBrochureClick = {
                navController.navigate(Navigation.BrochureDetail(it))
            })
        }

        composable<Navigation.BrochureDetail> {
            BrochureDetailScreen{
                navController.navigateUp()
            }
        }
    }
}