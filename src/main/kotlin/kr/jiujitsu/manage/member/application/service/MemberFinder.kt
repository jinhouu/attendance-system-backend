package kr.jiujitsu.manage.member.application.service

import kr.jiujitsu.manage.member.application.exception.MemberNotFoundException
import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class MemberFinder(
    private val memberRepository: MemberRepository,
) {
    fun find(id: Long): MemberResult =
        memberRepository
            .findById(id)
            .getOrNull()
            ?.let { MemberResult.fromEntity(it) }
            ?: throw MemberNotFoundException()

    fun findAll(): List<MemberResult> = memberRepository.findAll().map { MemberResult.fromEntity(it) }

    fun codeIsDuplicated(code: String): Boolean = memberRepository.existsByCode(code)

    fun codeIsDuplicated(
        code: String,
        exceptId: Long,
    ): Boolean = memberRepository.existsByCodeAndIdNot(code, exceptId)

    fun findByCode(code: String): MemberResult? = memberRepository.findByCode(code)?.let { MemberResult.fromEntity(it) }
}
