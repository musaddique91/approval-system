package com.musa.approvalsys.db.repositories.workpermit

import com.musa.approvalsys.db.entities.workpermit.WorkPermits
import org.springframework.data.jpa.repository.JpaRepository

interface WorkPermitRepository : JpaRepository<WorkPermits, Long>
