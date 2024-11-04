package com.dev.notification.backend.api.infrastructure.messaging

import com.dev.notification.backend.api.infrastructure.messaging.producer.KafkaProducer
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SendNotificationTopic(
    val kafkaProducer: KafkaProducer,
    val gson: Gson,
    @Value("\${spring.kafka.producer.send-event-notification-topic}")
    val topic: String,
) {
    fun execute(message: Any) {
        kafkaProducer.sendMessage(topic, gson.toJson(message))
    }
}
