package com.musa.approvalsys.dto.workpermit

import com.musa.approvalsys.db.enums.WPStatus
import java.time.LocalDateTime

data class WorkPermitDTO(
    var id: Long?,
    var approvalFlowId: Long,
    var approvalFlowName: String,
    var status: WPStatus,
    var schedule: String?,
    var vendorMail: String,
    var modifiedBy: String?,
    var updatedAt: LocalDateTime?
)
