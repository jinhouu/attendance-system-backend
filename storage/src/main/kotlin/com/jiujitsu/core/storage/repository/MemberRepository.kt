package com.jiujitsu.core.storage.repository

import com.jiujitsu.core.storage.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): MemberEntity?
}