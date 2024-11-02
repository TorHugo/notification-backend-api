package com.dev.notification.backend.api.domain.entity

abstract class AggregateRoot<T> {
    abstract val identifier: T

    fun getIdentifier(): String {
        return identifier.toString()
    }
}
