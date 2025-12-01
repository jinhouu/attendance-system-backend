package kr.jiujitsu.manage.member.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.jiujitsu.manage.common.entity.BaseEntity

@Entity
@Table(name = "registration_history")
class RegistrationHistoryEntity(
    val memberId: Long,
    val extendDate: String,
): BaseEntity() {}
