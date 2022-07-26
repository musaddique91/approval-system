package com.musa.approvalsys.db.repositories.workpermit

import com.musa.approvalsys.db.entities.workpermit.Approvers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface ApproverRepository : JpaRepository<Approvers, Long> {
    @Transactional
    @Modifying
    @Query("delete from Approvers where approvalFlowId= :approvalFlowId")
    fun deleteByApprovalFlowId(approvalFlowId: Long)

    fun findByApprovalFlowId(approvalFlowId: Long): List<Approvers>
}
