package com.dev.notification.backend.api.domain.entity

import com.dev.notification.backend.api.domain.objects.Parameter
import com.dev.notification.backend.api.domain.utils.IdentifierUtils
import com.dev.notification.backend.api.domain.utils.JsonUtils
import java.time.LocalDateTime
import java.util.UUID

data class NotificationDomain(
    override val identifier: UUID,
    val contact: String,
    val subject: String,
    val template: String,
    val parameters: List<Parameter>,
    val createdAt: LocalDateTime
) : AggregateRoot<UUID>() {

    fun getParameters(): String {
        return JsonUtils.toJson(parameters)
    }

    companion object {
        fun create(
            contact: String,
            subject: String,
            template: String,
            parameters: List<Parameter>
        ): NotificationDomain {
            return NotificationDomain(
                identifier = IdentifierUtils.generated(),
                contact = contact,
                subject = subject,
                template = template,
                parameters = parameters,
                createdAt = LocalDateTime.now()
            )
        }
    }
}
