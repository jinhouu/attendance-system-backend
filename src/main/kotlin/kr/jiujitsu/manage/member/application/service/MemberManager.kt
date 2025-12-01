package kr.jiujitsu.manage.member.application.service

import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.persistence.entity.MemberEntity
import kr.jiujitsu.manage.common.enums.BeltGrade
import kr.jiujitsu.manage.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Component


@Component
class MemberManager (
    private val memberRepository: MemberRepository,
) {
    fun register(name: String, code: String, phone: String, belt: BeltGrade, grau: Int): MemberResult {
        val memberEntity = MemberEntity.register(name, code, phone, belt, grau)
        return MemberResult.fromEntity (memberRepository.save(memberEntity))
    }
}