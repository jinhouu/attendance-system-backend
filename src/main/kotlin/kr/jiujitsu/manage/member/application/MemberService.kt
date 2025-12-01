package kr.jiujitsu.manage.member.application

import kr.jiujitsu.manage.member.application.exception.CodeValidFailException
import kr.jiujitsu.manage.member.application.service.MemberFinder
import kr.jiujitsu.manage.member.application.service.MemberManager
import kr.jiujitsu.manage.member.application.command.MemberRegisterCommand
import kr.jiujitsu.manage.member.application.result.MemberResult
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberManager: MemberManager,
    private val memberFinder: MemberFinder,
) {
    fun register(request: MemberRegisterCommand): MemberResult {
        validateCode(request.code)
        return memberManager.register(request.name, request.code, request.phone, request.belt, request.grau)
    }

    fun validateCode(code: String) {
        require(code.isNotEmpty() ) { throw CodeValidFailException("code is invalid") }
        require(code.length == 4 ) { throw CodeValidFailException("code length is not 4") }
        require(code.matches(Regex("[0-9]+")) ) { throw CodeValidFailException("code contains not number") }
        require(!memberFinder.codeIsDuplicated(code) ) { throw CodeValidFailException("code is duplicated") }
    }

    fun getMemberById(id: Long): MemberResult? {
        return memberFinder.find(id)
    }

}