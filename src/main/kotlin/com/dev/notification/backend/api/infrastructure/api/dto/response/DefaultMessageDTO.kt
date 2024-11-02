package com.dev.notification.backend.api.infrastructure.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

class DefaultMessageDTO(
    @JsonProperty("message")
    val message: String
)
