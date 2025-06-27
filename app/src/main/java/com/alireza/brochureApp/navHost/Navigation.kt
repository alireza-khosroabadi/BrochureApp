package com.alireza.brochureApp.navHost

import kotlinx.serialization.Serializable

sealed class Navigation {

    @Serializable
    data object BrochureList : Navigation()
    @Serializable
    data class BrochureDetail(val brochureId: String) : Navigation()
}