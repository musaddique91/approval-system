package com.musa.approvalsys.db.entities.workpermit

import com.musa.approvalsys.db.enums.WPStatus
import com.quickget.backend.models.order.AuditableAttributes
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "work_permits")
class WorkPermits(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var approvalFlowId: Long,
    @Enumerated(EnumType.STRING)
    var status: WPStatus,
    var schedule: String?,
    var vendorMail: String,
) : AuditableAttributes()
