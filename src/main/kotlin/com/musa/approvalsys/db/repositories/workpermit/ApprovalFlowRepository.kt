package com.musa.approvalsys.db.repositories.workpermit

import com.musa.approvalsys.db.entities.workpermit.ApprovalFlow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ApprovalFlowRepository : JpaRepository<ApprovalFlow, Long> {
    fun findOneByName(name: String): ApprovalFlow?
    @Query("select name from ApprovalFlow where id= :id")
    fun getNameById(id: Long): String
}
