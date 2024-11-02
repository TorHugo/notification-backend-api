package com.dev.notification.backend.api.infrastructure.service

import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.domain.gateway.NotificationGateway
import com.dev.notification.backend.api.domain.service.SendNotification
import com.dev.notification.backend.api.infrastructure.messaging.SendNotificationTopic
import org.springframework.stereotype.Service

@Service
class SendNotificationImpl(
    private val notificationGateway: NotificationGateway,
    private val sendNotificationTopic: SendNotificationTopic
): SendNotification {
    override fun execute(domain: NotificationDomain) {
        notificationGateway.save(domain)
        sendNotificationTopic.execute(domain)
    }
}