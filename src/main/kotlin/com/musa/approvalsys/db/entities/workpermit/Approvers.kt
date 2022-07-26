package com.musa.approvalsys.db.entities.workpermit

import com.musa.approvalsys.db.convertor.LongListConvertor
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "approvers")
class Approvers(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Convert(converter = LongListConvertor::class)
    var userIds: List<Long>,
    var order: Int,
    var step: String,
    var approvalFlowId: Long,
)
