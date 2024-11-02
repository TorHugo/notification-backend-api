package com.dev.notification.backend.api.domain.gateway

import com.dev.notification.backend.api.domain.entity.NotificationDomain

interface NotificationGateway {
    fun save(domain: NotificationDomain)
}