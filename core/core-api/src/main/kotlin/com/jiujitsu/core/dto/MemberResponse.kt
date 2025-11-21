package com.jiujitsu.core.dto

import com.jiujitsu.core.storage.entity.MemberEntity
import java.time.LocalDate

class MemberResponse(
    val id: Long? = null,
    val name: String,
    val code: String,
    val phone: String,
    val registrationDate: LocalDate = LocalDate.now(),
    val expirationDate: LocalDate = LocalDate.now()
) {
    companion object {
        fun fromEntity(entity: MemberEntity): MemberResponse {
            return MemberResponse (
                entity.id,
                entity.name,
                entity.code,
                entity.phone,
                entity.registrationDate,
                entity.expirationDate,
            )
        }
    }
}