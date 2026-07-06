package kr.jiujitsu.manage.member.application

import jakarta.transaction.Transactional
import kr.jiujitsu.manage.member.application.command.MemberRegisterCommand
import kr.jiujitsu.manage.member.application.command.MemberUpdateCommand
import kr.jiujitsu.manage.member.application.exception.CodeValidFailException
import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.application.service.MemberFinder
import kr.jiujitsu.manage.member.application.service.MemberManager
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberManager: MemberManager,
    private val memberFinder: MemberFinder,
) {
    fun register(command: MemberRegisterCommand): MemberResult {
        if (memberFinder.codeIsDuplicated(command.code)) {
            throw CodeValidFailException("코드가 중복되었습니다.")
        }

        return memberManager.register(command.name, command.code, command.phone, command.belt, command.grau)
    }

    fun getMemberById(id: Long): MemberResult = memberFinder.find(id)

    @Transactional
    fun update(command: MemberUpdateCommand): MemberResult {
        if (memberFinder.codeIsDuplicated(command.code, command.id)) {
            throw CodeValidFailException("코드가 중복되었습니다.")
        }

        return memberManager.update(command)
    }
}
