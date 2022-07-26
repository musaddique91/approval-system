package com.musa.approvalsys.dto.workpermit

data class ApproversDTO(
    var userIds: List<Long>,
    var order: Int,
    var step: String,
)
