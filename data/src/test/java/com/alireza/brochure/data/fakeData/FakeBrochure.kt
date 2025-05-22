package com.alireza.brochure.data.fakeData

import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import com.alireza.brochure.domain.model.brochure.RegularBrochure

val regularBrochure = RegularBrochure("2", "Brochure number 2", distance = 5.5, imageUrl = "")
val premiumBrochure = PremiumBrochure("3", "Brochure Premium 2", distance = 4.0, imageUrl = "")


val brochureList = listOf<Brochure>(regularBrochure, premiumBrochure)
