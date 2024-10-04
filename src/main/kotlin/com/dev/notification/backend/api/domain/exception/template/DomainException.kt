package com.dev.notification.backend.api.domain.exception.template

import com.dev.notification.backend.api.domain.exception.DomainExceptionHandler

class DomainException : DomainExceptionHandler {
    constructor(message: String, parameter: String) : super(message, parameter)
    constructor(message: String) : super(message)
}
