package kr.jiujitsu.manage.attendance.api

import kr.jiujitsu.manage.attendance.application.AttendanceService
import kr.jiujitsu.manage.attendance.application.command.GetDailyAttendanceCommand
import kr.jiujitsu.manage.attendance.application.result.AttendanceResult
import kr.jiujitsu.manage.common.dto.ApiResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/attendances")
class AttendanceController(
    private val attendanceService: AttendanceService,
) {
    @GetMapping("/daily")
    fun getDaily(
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        date: LocalDate?,
    ): ApiResponse<List<AttendanceResult>> =
        ApiResponse(
            status = HttpStatus.OK,
            code = 200,
            body = attendanceService.getDaily(GetDailyAttendanceCommand(date ?: LocalDate.now())),
        )

    @PostMapping
    fun attendance(
        @RequestParam code: String,
    ): ApiResponse<AttendanceResult> =
        ApiResponse(
            status = HttpStatus.CREATED,
            code = 201,
            body = attendanceService.register(code),
        )
}
