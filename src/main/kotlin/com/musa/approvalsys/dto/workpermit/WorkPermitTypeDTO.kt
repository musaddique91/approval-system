package com.musa.approvalsys.dto.workpermit

import com.musa.approvalsys.db.enums.WPType

data class WorkPermitTypeDTO(
    var code: String,
    var name: String,
    var type: WPType
)
