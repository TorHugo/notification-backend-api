package com.dev.notification.backend.api.domain.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

object JsonUtils {
    private val objectMapper: ObjectMapper = ObjectMapper().apply {
        registerModule(JavaTimeModule())
        registerModule(KotlinModule.Builder().build())
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun <T> toJson(value: T): String {
        return try {
            objectMapper.writeValueAsString(value)
        } catch (e: Exception) {
            throw JsonConversionException("Error convert object to JSON.", e)
        }
    }

    fun <T> fromJson(json: String, typeReference: TypeReference<T>): T {
        return try {
            objectMapper.readValue(json, typeReference)
        } catch (e: Exception) {
            throw JsonConversionException("Error convert JSON to object.", e)
        }
    }

    class JsonConversionException(message: String, cause: Throwable? = null) :
        RuntimeException(message, cause)
}
