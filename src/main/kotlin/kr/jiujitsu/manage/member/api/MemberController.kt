package kr.jiujitsu.manage.member.api

import kr.jiujitsu.manage.common.dto.ApiResponse
import kr.jiujitsu.manage.member.api.request.MemberRegisterRequest
import kr.jiujitsu.manage.member.api.request.MemberUpdateRequest
import kr.jiujitsu.manage.member.application.MemberService
import kr.jiujitsu.manage.member.application.result.MemberResult
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping
    fun register(
        @RequestBody @Validated request: MemberRegisterRequest,
    ): ApiResponse<MemberResult> =
        ApiResponse(
            status = HttpStatus.CREATED,
            code = 201,
            body = memberService.register(request.toCommand()),
        )

    @GetMapping("/{id}")
    fun getMemberDetail(
        @PathVariable id: Long,
    ): ApiResponse<MemberResult> =
        ApiResponse(
            status = HttpStatus.OK,
            code = 200,
            body = memberService.getMemberById(id),
        )

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody @Validated request: MemberUpdateRequest,
    ): ApiResponse<MemberResult> =
        ApiResponse(
            status = HttpStatus.OK,
            code = 200,
            body = memberService.update(request.toCommand(id)),
        )
}
