package kr.jiujitsu.manage.member.api

import kr.jiujitsu.manage.member.application.command.MemberRegisterCommand
import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.application.exception.CodeAlreadyExistException
import kr.jiujitsu.manage.member.application.exception.CodeValidFailException
import kr.jiujitsu.manage.member.application.MemberService
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
    fun register(request: MemberRegisterCommand): MemberResult {
        return memberService.register(request)
    }

    @GetMapping("/{id}")
    fun getMemberDetail(@PathVariable id: Long): MemberResult? {
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