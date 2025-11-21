package com.jiujitsu.core.api

import com.jiujitsu.core.dto.MemberRegisterRequest
import com.jiujitsu.core.dto.MemberResponse
import com.jiujitsu.core.exception.CodeAlreadyExistException
import com.jiujitsu.core.exception.CodeValidFailException
import com.jiujitsu.core.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    fun register(request: MemberRegisterRequest): MemberResponse {
        return memberService.register(request)
    }

    @GetMapping("/{id}")
    fun getMemberDetail(@PathVariable id: Long): MemberResponse? {
        return memberService.getMemberById(id)
    }

    @GetMapping("/validate_code/{code}")
    fun validateCode(@PathVariable code: String): Boolean {
        try {
            memberService.validateCode(code)
            return true;
        } catch ( e: Exception ){
            when (e) {
                is CodeValidFailException,
                is CodeAlreadyExistException -> { return false }
                else -> throw e;
            }
        }
    }
}