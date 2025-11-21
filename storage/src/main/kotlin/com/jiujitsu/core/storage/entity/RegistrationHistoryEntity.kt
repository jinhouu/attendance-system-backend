package com.jiujitsu.core.storage.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "registration_history")
class RegistrationHistoryEntity(
    val memberId: Long,
    val extendDate: String,
): BaseEntity() {}
