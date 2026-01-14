package kr.jiujitsu.manage.member.api

import kr.jiujitsu.manage.common.dto.ApiResponse
import kr.jiujitsu.manage.member.api.request.MemberRegisterRequest
import kr.jiujitsu.manage.member.api.request.MemberUpdateRequest
import kr.jiujitsu.manage.member.application.MemberService
import kr.jiujitsu.manage.member.application.result.MemberResult
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping
    fun register(
        @RequestBody @Validated request: MemberRegisterRequest
    ): ApiResponse<MemberResult> {

        return ApiResponse (
            status = HttpStatus.CREATED,
            code = 201,
            body = memberService.register(request.toCommand())
        )
    }

    @GetMapping("/{id}")
    fun getMemberDetail(
        @PathVariable id: Long,
    ): ApiResponse<MemberResult> {

        return ApiResponse (
            status = HttpStatus.CREATED,
            code = 201,
            body = memberService.getMemberById(id)
        )
    }

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody @Validated request: MemberUpdateRequest
    ): ApiResponse<MemberResult> {
        return ApiResponse (
            status = HttpStatus.CREATED,
            code = 201,
            body = memberService.update(request.toCommand(id)),
        )
    }
}
