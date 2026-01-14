package kr.jiujitsu.manage.attendance.api.request

import java.time.LocalDate

data class GetAttendanceRequest(
    val date: LocalDate = LocalDate.now(),
)
