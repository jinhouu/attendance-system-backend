package com.jiujitsu.core.application

import com.jiujitsu.core.dto.MemberResponse
import com.jiujitsu.core.storage.repository.MemberRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class MemberFinder(
    private val memberRepository: MemberRepository
) {
    fun find(id: Long): MemberResponse? {
        return memberRepository.findById(id).getOrNull()
            ?. let { MemberResponse.fromEntity(it) }
    }

    fun codeIsDuplicated(code: String): Boolean {
        return memberRepository.existsByCode(code)
    }

    fun findByCode(code: String): MemberResponse? {
        return memberRepository.findByCode(code)?.let { MemberResponse.fromEntity(it) }
    }
}