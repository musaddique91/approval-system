package com.musa.approvalsys.mapper

import com.musa.approvalsys.db.entities.workpermit.WorkPermits
import com.musa.approvalsys.dto.workpermit.WorkPermitDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface WorkPermitMapper {
    fun mapDTOToEntity(workPermitDTO: WorkPermitDTO): WorkPermits
    @Mapping(target = "approvalFlowName", ignore = true)
    fun mapEntityToDTO(workPermitEntity: WorkPermits): WorkPermitDTO
}