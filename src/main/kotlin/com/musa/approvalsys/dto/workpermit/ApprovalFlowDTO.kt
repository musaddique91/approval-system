package com.musa.approvalsys.dto.workpermit

import java.time.LocalDateTime
import java.util.Date

data class ApprovalFlowDTO(
    var id: Long?,
    var workPermitCode: String,
    var workPermitName: String?,
    var flowName: String,
    var approvers: List<ApproversDTO>?,
    var createdDate: LocalDateTime?,
    var createdUser: String?
)
