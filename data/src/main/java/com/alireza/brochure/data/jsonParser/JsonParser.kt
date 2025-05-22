package com.alireza.brochure.data.jsonParser

import kotlinx.serialization.json.JsonElement

interface JsonParser<T> {
    fun parse(json: JsonElement): T?
}