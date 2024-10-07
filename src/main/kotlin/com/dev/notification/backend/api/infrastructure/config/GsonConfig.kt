package com.dev.notification.backend.api.infrastructure.config

import com.dev.notification.backend.api.infrastructure.adapter.LocalDateTimeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class GsonConfig {

    @Bean
    fun gson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
    }
}