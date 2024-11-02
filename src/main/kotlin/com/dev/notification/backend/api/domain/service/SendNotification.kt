package com.dev.notification.backend.api.domain.service

import com.dev.notification.backend.api.domain.entity.NotificationDomain

interface SendNotification {
    fun execute(domain: NotificationDomain)
}
