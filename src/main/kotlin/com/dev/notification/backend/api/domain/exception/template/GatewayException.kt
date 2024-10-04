package com.dev.notification.backend.api.domain.exception.template

import com.dev.notification.backend.api.domain.exception.DomainExceptionHandler

class GatewayException(message: String, parameter: String) : DomainExceptionHandler(message, parameter)
