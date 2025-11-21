package com.jiujitsu.core.service

import com.jiujitsu.core.application.MemberFinder
import com.jiujitsu.core.application.MemberManager
import com.jiujitsu.core.dto.MemberRegisterRequest
import com.jiujitsu.core.dto.MemberResponse
import com.jiujitsu.core.exception.CodeValidFailException
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberManager: MemberManager,
    private val memberFinder: MemberFinder,
) {
    fun register(request: MemberRegisterRequest): MemberResponse {
        validateCode(request.code)
        return memberManager.register(request.name, request.code, request.phone, request.belt, request.grau)
    }

    fun validateCode(code: String) {
        require(code.isNotEmpty() ) { CodeValidFailException("code is invalid") }
        require(code.length == 4 ) { CodeValidFailException("code length is not 4") }
        require(code.matches(Regex("[0-9]+")) ) { CodeValidFailException("code contains not number") }
        require(!memberFinder.codeIsDuplicated(code) ) { CodeValidFailException("code is duplicated") }
    }

    fun getMemberById(id: Long): MemberResponse? {
        return memberFinder.find(id)
    }

}