package com.quickget.backend.models.order

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AuditableAttributes {
    @CreatedDate
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        protected set

    @CreatedBy
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    var modifiedBy: String? = null
        protected set
}
