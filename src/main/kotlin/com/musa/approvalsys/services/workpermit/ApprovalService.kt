package com.musa.approvalsys.services.workpermit

import com.musa.approvalsys.db.entities.workpermit.ApprovalFlow
import com.musa.approvalsys.db.entities.workpermit.Approvers
import com.musa.approvalsys.db.repositories.workpermit.ApprovalFlowRepository
import com.musa.approvalsys.db.repositories.workpermit.ApproverRepository
import com.musa.approvalsys.db.repositories.workpermit.WorkPermitTypeRepository
import com.musa.approvalsys.dto.workpermit.ApprovalFlowDTO
import com.musa.approvalsys.dto.workpermit.ApproversDTO
import com.musa.approvalsys.dto.workpermit.WorkPermitTypeDTO
import com.musa.approvalsys.exceptions.AppSysErrorCodes
import com.musa.approvalsys.exceptions.AppSysException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApprovalService(
    private val workPermitTypeRepository: WorkPermitTypeRepository,
    private val approvalFlowRepository: ApprovalFlowRepository,
    private val approverRepository: ApproverRepository
) {
    fun getWorkPermitTypes(): List<WorkPermitTypeDTO> {
        return workPermitTypeRepository.findAll().map { WorkPermitTypeDTO(it.code, it.name, it.type) }.sortedBy { it.name }
    }

    fun getApprovalFlow(id: Long): ApprovalFlowDTO {
        return approvalFlowRepository.findByIdOrNull(id)?.let { appFlow ->
            val approvers = approverRepository.findByApprovalFlowId(appFlow.id)
            ApprovalFlowDTO(
                appFlow.id,
                appFlow.workPermitCode,
                workPermitTypeRepository.getNameByCode(appFlow.workPermitCode),
                appFlow.name,
                approvers.map { approver -> ApproversDTO(approver.userIds, approver.order, approver.step) },
                appFlow.updatedAt,
                appFlow.modifiedBy
            )
        } ?: throw AppSysException(
            AppSysErrorCodes.INVALID_ID.code,
            AppSysErrorCodes.INVALID_ID.message
        )
    }

    fun getApprovalFlows(): List<ApprovalFlowDTO> {
        return approvalFlowRepository.findAll().map { appFlow ->
            val approvers = approverRepository.findByApprovalFlowId(appFlow.id)
            ApprovalFlowDTO(
                appFlow.id,
                appFlow.workPermitCode,
                workPermitTypeRepository.getNameByCode(appFlow.workPermitCode),
                appFlow.name,
                approvers.map { approver -> ApproversDTO(approver.userIds, approver.order, approver.step) },
                appFlow.updatedAt,
                appFlow.modifiedBy
            )
        }
    }

    @Transactional
    fun saveApprovalFlow(approvalFlowDTO: ApprovalFlowDTO): ApprovalFlowDTO {
        approvalFlowRepository.findOneByName(approvalFlowDTO.flowName)?.let {
            throw AppSysException(AppSysErrorCodes.FLOW_ALREADY_EXIST.code, AppSysErrorCodes.FLOW_ALREADY_EXIST.message)
        }
        val approvalFlowEntity =
            approvalFlowRepository.save(ApprovalFlow(0, approvalFlowDTO.flowName, approvalFlowDTO.workPermitCode))
        val approve =
            approvalFlowDTO.approvers?.map { Approvers(0, it.userIds, it.order, it.step, approvalFlowEntity.id) }
        val approverDTO = approve?.let { approver ->
            approverRepository.saveAll(approver).map { ApproversDTO(it.userIds, it.order, it.step) }
        }
        return ApprovalFlowDTO(
            approvalFlowEntity.id,
            approvalFlowEntity.workPermitCode,
            null,
            approvalFlowEntity.name,
            approverDTO,
            approvalFlowEntity.updatedAt,
            approvalFlowEntity.modifiedBy
        )
    }

    @Transactional
    fun updateApprovalFlow(approvalFlowDTO: ApprovalFlowDTO): Boolean {
        approvalFlowRepository.findByIdOrNull(approvalFlowDTO.id)?.let { appFlow ->
            approvalFlowRepository.findOneByName(approvalFlowDTO.flowName)?.let {
                if (appFlow.id != approvalFlowDTO.id!!) {
                    throw AppSysException(
                        AppSysErrorCodes.FLOW_ALREADY_EXIST.code,
                        AppSysErrorCodes.FLOW_ALREADY_EXIST.message
                    )
                }
            }
            appFlow.name = approvalFlowDTO.flowName
            appFlow.workPermitCode = approvalFlowDTO.workPermitCode
            approvalFlowRepository.save(appFlow)

            approverRepository.deleteByApprovalFlowId(appFlow.id)
            val approve =
                approvalFlowDTO.approvers?.map { Approvers(0, it.userIds, it.order, it.step, appFlow.id) }
            approve?.let {
                approverRepository.saveAll(it)
            }
        } ?: throw AppSysException(
            AppSysErrorCodes.INVALID_ID.code,
            AppSysErrorCodes.INVALID_ID.message
        )
        return true
    }

    @Transactional
    fun deleteFlow(id: Long): Boolean {
        approverRepository.deleteByApprovalFlowId(id)
        approvalFlowRepository.deleteById(id)
        return true
    }
}