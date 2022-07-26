package com.musa.approvalsys.db.repositories.user

import com.musa.approvalsys.db.entities.user.ResourceAssignment
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceAssignmentRepository : JpaRepository<ResourceAssignment, Long> {
    fun deleteByRoleId(roleId: Long): Long
    fun findByRoleId(roleId: Long): List<ResourceAssignment>
}
