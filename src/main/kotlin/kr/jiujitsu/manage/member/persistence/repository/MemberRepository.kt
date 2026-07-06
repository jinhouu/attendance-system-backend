package kr.jiujitsu.manage.member.persistence.repository

import kr.jiujitsu.manage.member.persistence.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun existsByCode(code: String): Boolean

    fun existsByCodeAndIdNot(
        code: String,
        id: Long,
    ): Boolean

    fun findByCode(code: String): MemberEntity?

    fun countByRegistrationDateGreaterThanEqual(registrationDate: LocalDate): Long

    fun countByExpirationDateBetween(
        start: LocalDate,
        end: LocalDate,
    ): Long
}
