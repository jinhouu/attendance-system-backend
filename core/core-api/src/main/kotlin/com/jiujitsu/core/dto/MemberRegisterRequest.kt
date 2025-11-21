package com.jiujitsu.core.dto

import com.jiujitsu.core.enum.BeltColor

data class MemberRegisterRequest (
    val code: String,
    val name: String,
    val belt: BeltColor = BeltColor.WHITE,
    val grau: Int = 0,
    val phone: String,
)