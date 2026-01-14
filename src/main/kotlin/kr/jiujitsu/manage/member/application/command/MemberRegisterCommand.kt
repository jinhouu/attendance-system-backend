package kr.jiujitsu.manage.member.application.command

import kr.jiujitsu.manage.common.enums.Belt

data class MemberRegisterCommand(
    val code: String,
    val name: String,
    val belt: Belt = Belt.WHITE,
    val grau: Int = 0,
    val phone: String,
)
