package kr.jiujitsu.manage.attendance.application.command

import java.time.LocalDate


data class GetDailyAttendanceCommand(
    val date: LocalDate = LocalDate.now(),
)
