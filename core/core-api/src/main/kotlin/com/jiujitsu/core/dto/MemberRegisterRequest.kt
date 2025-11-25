package com.jiujitsu.core.dto

import com.jiujitsu.core.enum.BeltGrade

data class MemberRegisterRequest (
    val code: String,
    val name: String,
    val belt: BeltGrade = BeltGrade.WHITE,
    val grau: Int = 0,
    val phone: String,
)