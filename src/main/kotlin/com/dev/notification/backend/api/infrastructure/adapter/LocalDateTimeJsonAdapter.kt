package com.dev.notification.backend.api.infrastructure.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    }

    override fun serialize(localDateTime: LocalDateTime, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
        return JsonPrimitive(localDateTime.format(formatter))
    }

    override fun deserialize(jsonElement: JsonElement, type: Type, jsonDeserializationContext: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime.parse(jsonElement.asString, formatter)
    }
}