package com.dev.notification.backend.api.infrastructure.event

import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.domain.service.SendNotification
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NotificationEventListener(
    private val sendNotification: SendNotification
) {
    private val logger = LoggerFactory.getLogger(NotificationEventListener::class.java)

    @EventListener
    fun handlerNotification(event: NotificationDomain){
        try {
            sendNotification.execute(event)
        } catch (e: Exception) {
            logger.error("Error with send notification event.")
            // should be retry?
            throw e
        }
    }
}