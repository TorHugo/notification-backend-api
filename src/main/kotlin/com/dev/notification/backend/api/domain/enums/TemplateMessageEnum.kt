package com.dev.notification.backend.api.domain.enums

enum class TemplateMessageEnum(val subject: String, val template: String) {
    CONFIRMED_ACCOUNT("Hey, confirme a sua conta :)", "Olá, `name`, tudo bem?\nUtilize o código abaixo para confirmar a sua conta!\n\nCódigo: `hashcode`\nExpiração: `expiration-date`!"),
    WELCOME_ACCOUNT("Bem-vindo ao NotificationApp", "Olá `name`,\nObrigado por se juntar ao NotificationApp! Estamos felizes em tê-lo conosco.\nVocê agora pode começar a agendar suas notificações e aproveitar nossa plataforma. Se precisar de qualquer ajuda, estamos aqui para isso.\nBoa sorte e aproveite a plataforma!\n\nAtenciosamente,\nArruda, Victor Hugo\nEquipe NotificationApp.")
}
