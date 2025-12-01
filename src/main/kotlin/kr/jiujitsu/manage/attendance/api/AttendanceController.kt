package kr.jiujitsu.manage.attendance.api

import kr.jiujitsu.manage.attendance.application.result.AttendanceResult
import kr.jiujitsu.manage.attendance.application.AttendanceService
import kr.jiujitsu.manage.attendance.application.command.GetDailyAttendanceCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/attendances")
class AttendanceController (
    private val attendanceService: AttendanceService
) {

    @GetMapping("/daily")
    fun getDaily(@RequestBody command: GetDailyAttendanceCommand): List<AttendanceResult> {
        return attendanceService.getDaily(command)
    }


    @PostMapping
    fun attendance(code: String): AttendanceResult {
        return attendanceService.register(code)
    }
}