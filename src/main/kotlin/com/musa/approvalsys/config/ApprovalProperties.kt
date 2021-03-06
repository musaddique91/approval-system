package com.musa.approvalsys.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ApprovalProperties {
    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String
    @Value("\${jwt.token.validity_inhours}")
    var jwtTokenValidity: Long = 5L
    @Value("\${mail.from}")
    lateinit var mailFrom: String
    @Value("\${security.allowed.urls}")
    lateinit var securityAllowedURL: List<String>
}
