package kr.jiujitsu.manage.member.application.command

import kr.jiujitsu.manage.common.enums.Belt

data class MemberUpdateCommand(
    val id: Long,
    val code: String,
    val belt: Belt,
    val grau: Int,
    val phone: String,
)
