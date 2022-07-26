package com.musa.approvalsys.db.entities.workpermit

import com.musa.approvalsys.db.enums.WPType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "work_permit_types")
class WorkPermitTypes(
    @Id var code: String,
    var name: String,
    @Enumerated(EnumType.STRING)
    var type: WPType
)
