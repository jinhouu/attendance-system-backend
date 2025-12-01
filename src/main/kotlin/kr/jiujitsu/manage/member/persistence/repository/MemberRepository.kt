package kr.jiujitsu.manage.member.persistence.repository

import kr.jiujitsu.manage.member.persistence.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): MemberEntity?
}