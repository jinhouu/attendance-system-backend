package kr.jiujitsu.manage.member.application.command

import kr.jiujitsu.manage.common.enums.BeltGrade

data class MemberRegisterCommand (
    val code: String,
    val name: String,
    val belt: BeltGrade = BeltGrade.WHITE,
    val grau: Int = 0,
    val phone: String,
)