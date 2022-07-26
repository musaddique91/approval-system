package com.musa.approvalsys.controller

import com.musa.approvalsys.dto.workpermit.ApprovalFlowDTO
import com.musa.approvalsys.dto.workpermit.WorkPermitTypeDTO
import com.musa.approvalsys.services.workpermit.ApprovalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("wp/api")
class WorkPermitController(
    private val approvalService: ApprovalService
) {

    @GetMapping("workPermits")
    fun workPermits(): ResponseEntity<List<WorkPermitTypeDTO>> {
        return ok(approvalService.getWorkPermitTypes())
    }

    @GetMapping("approvalFlow/{id}")
    fun approvalFlowsById(@PathVariable("id") id: Long): ResponseEntity<ApprovalFlowDTO> {
        return ok(approvalService.getApprovalFlow(id))
    }

    @GetMapping("approvalFlows")
    fun getApprovalFlows(): ResponseEntity<List<ApprovalFlowDTO>> {
        return ok(approvalService.getApprovalFlows())
    }

    @PostMapping("approvalFlows")
    fun saveApprovalFlows(@RequestBody flow: ApprovalFlowDTO): ResponseEntity<ApprovalFlowDTO> {
        return ResponseEntity(approvalService.saveApprovalFlow(flow), HttpStatus.CREATED)
    }

    @PutMapping("approvalFlows/{id}")
    fun updateApprovalFlows(@PathVariable("id") id: Long, @RequestBody flow: ApprovalFlowDTO): ResponseEntity<Boolean> {
        return ResponseEntity(approvalService.updateApprovalFlow(flow), HttpStatus.ACCEPTED)
    }

    @DeleteMapping("approvalFlows/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Boolean> {
        return ResponseEntity(approvalService.deleteFlow(id), HttpStatus.OK)
    }
}