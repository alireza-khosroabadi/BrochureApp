package com.alireza.brochure.database.entity

sealed class BaseBrochure{
    data class SuperBannerWrapperEntity(val entity: List<SuperBannerEntity>) : BaseBrochure() {
        override val orderIndex: Int
            get() =entity.takeIf { it.isNotEmpty() }?.first()?.orderIndex?:0
    }

    data class BrochureWrapperEntity(val entity: BrochureEntity) : BaseBrochure() {
        override val orderIndex: Int
            get() = entity.orderIndex
    }

    abstract val orderIndex: Int
}