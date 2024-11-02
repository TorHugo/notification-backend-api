package com.dev.notification.backend.api.domain.validation

import com.dev.notification.backend.api.domain.annotation.Password
import com.dev.notification.backend.api.domain.exception.template.DomainException
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordValidator : ConstraintValidator<Password, String> {
    override fun isValid(password: String?, context: ConstraintValidatorContext?): Boolean {
        if (password == null) {
            throw DomainException("Password cannot be null!")
        }
        if (password.length < 8) {
            throw DomainException("This password must be at least 8 characters.")
        }
        if (!password.matches(Regex(".*[A-Z].*"))) {
            throw DomainException("This password does not contain uppercase letters.")
        }
        if (!password.matches(Regex(".*[a-z].*"))) {
            throw DomainException("This password does not contain lowercase letters.")
        }
        if (!password.matches(Regex(".*\\W.*"))) {
            throw DomainException("This password must have a special character.")
        }
        return true
    }
}
