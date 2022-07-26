package com.musa.approvalsys.services.workpermit

import com.musa.approvalsys.db.repositories.workpermit.ApprovalFlowRepository
import com.musa.approvalsys.db.repositories.workpermit.WorkPermitRepository
import com.musa.approvalsys.dto.workpermit.WorkPermitDTO
import com.musa.approvalsys.mapper.WorkPermitMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WorkPermitService(
    private val workPermitRepository: WorkPermitRepository,
    private val approvalFlowRepository: ApprovalFlowRepository,
    private val workPermitMapper: WorkPermitMapper
) {

    @Transactional
    fun saveWorkPermit(workPermitDTO: WorkPermitDTO): WorkPermitDTO {
        workPermitDTO.id = 0
        return workPermitRepository.save(
            workPermitMapper.mapDTOToEntity(workPermitDTO)
        ).let {
            val dto = workPermitMapper.mapEntityToDTO(it)
            dto.approvalFlowName = approvalFlowRepository.getNameById(it.approvalFlowId)
            dto
        }
    }

    fun getAllWorkPermits(): List<WorkPermitDTO> {
        return workPermitRepository.findAll().map { wp ->
            val dto = workPermitMapper.mapEntityToDTO(wp)
            dto.approvalFlowName = approvalFlowRepository.getNameById(wp.approvalFlowId)
            dto
        }
    }
}