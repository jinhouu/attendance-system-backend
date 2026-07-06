package kr.jiujitsu.manage.member.api.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import kr.jiujitsu.manage.common.enums.Belt
import kr.jiujitsu.manage.member.application.command.MemberRegisterCommand

data class MemberRegisterRequest(
    @field:Pattern(regexp = "^\\d{4}$", message = "회원번호는 4글자여야 합니다.")
    val code: String,
    @field:NotBlank("이름을 입력하세요")
    val name: String,
    val belt: Belt,
    @field:Min(value = 0)
    @field:Max(value = 4)
    val grau: Int,
    @field:Pattern(
        regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",
        message = "올바른 핸드폰 번호 형식이 아닙니다. (하이픈 제외 숫자만 입력)",
    )
    val phone: String = "",
) {
    fun toCommand(): MemberRegisterCommand =
        MemberRegisterCommand(
            code = code,
            name = name,
            belt = belt,
            grau = grau,
            phone = phone,
        )
}
