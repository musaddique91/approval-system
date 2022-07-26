package com.musa.approvalsys.db.repositories.user

import com.musa.approvalsys.db.entities.user.Auth
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<Auth, Long> {
    fun findByUsername(username: String): Auth?
    fun findByUuidToken(token: String): Auth?
}
