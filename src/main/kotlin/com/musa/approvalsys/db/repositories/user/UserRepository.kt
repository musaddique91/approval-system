package com.musa.approvalsys.db.repositories.user

import com.musa.approvalsys.db.entities.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    fun countByRoleId(roleId: Long): Long
}
