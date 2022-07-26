package com.musa.approvalsys.db.repositories.user

import com.musa.approvalsys.db.entities.user.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findOneByName(name: String): Role?
}
