package com.musa.approvalsys.db.repositories.workpermit

import com.musa.approvalsys.db.entities.workpermit.WorkPermitTypes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface WorkPermitTypeRepository : JpaRepository<WorkPermitTypes, String> {
    @Query("select name from WorkPermitTypes where code= :code")
    fun getNameByCode(code: String): String
}
