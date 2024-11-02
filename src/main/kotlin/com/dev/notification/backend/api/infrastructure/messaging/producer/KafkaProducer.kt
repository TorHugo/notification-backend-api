package com.dev.notification.backend.api.infrastructure.messaging.producer

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, String>
) {
    protected val logger: Log = LogFactory.getLog(this.javaClass)

    fun sendMessage(topic: String, message: String){
        try {
            kafkaTemplate.send(topic, message)
        } catch (e: Exception) {
            logger.error("Cannot to send message to kafka-topic.")
            // should be retry?
        }
    }
}