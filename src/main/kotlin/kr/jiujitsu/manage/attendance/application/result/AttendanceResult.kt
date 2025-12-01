package kr.jiujitsu.manage.attendance.application.result

import jakarta.persistence.Embedded
import kr.jiujitsu.manage.member.application.result.MemberResult

data class AttendanceResult(
    val id: Long,
    val timestamp: String,
    val time: String,

    @Embedded
    val memberInfo: MemberResult,
)