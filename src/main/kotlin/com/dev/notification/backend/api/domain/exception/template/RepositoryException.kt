package com.dev.notification.backend.api.domain.exception.template

import com.dev.notification.backend.api.domain.exception.DomainExceptionHandler

class RepositoryException(message: String, parameter: String) : DomainExceptionHandler(message, parameter)
