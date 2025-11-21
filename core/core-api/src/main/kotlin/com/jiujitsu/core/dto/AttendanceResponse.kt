package com.jiujitsu.core.dto

data class AttendanceResponse(
    val attendanceTime: String,
    val memberInfo: MemberResponse,
)
