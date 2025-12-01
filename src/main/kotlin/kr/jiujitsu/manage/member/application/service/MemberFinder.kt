package kr.jiujitsu.manage.member.application.service

import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class MemberFinder(
    private val memberRepository: MemberRepository
) {
    fun find(id: Long): MemberResult? {
        return memberRepository.findById(id).getOrNull()
            ?. let { MemberResult.fromEntity(it) }
    }

    fun codeIsDuplicated(code: String): Boolean {
        return memberRepository.existsByCode(code)
    }

    fun findByCode(code: String): MemberResult? {
        return memberRepository.findByCode(code)?.let { MemberResult.fromEntity(it) }
    }
}