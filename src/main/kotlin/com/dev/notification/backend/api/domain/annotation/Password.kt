package com.dev.notification.backend.api.domain.annotation

import com.dev.notification.backend.api.domain.validation.PasswordValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Password(
    val message: String = "Invalid password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
