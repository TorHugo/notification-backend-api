package com.dev.notification.backend.api.infrastructure.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserSuccessfullyDTO(
    @JsonProperty("identifier")
    val identifier: String
)
