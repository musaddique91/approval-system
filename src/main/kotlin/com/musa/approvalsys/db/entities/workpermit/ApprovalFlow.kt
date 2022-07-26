package com.musa.approvalsys.db.entities.workpermit

import com.quickget.backend.models.order.AuditableAttributes
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "approval_flow")
class ApprovalFlow(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var name: String,
    var workPermitCode: String
) : AuditableAttributes()
