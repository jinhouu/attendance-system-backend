package com.jiujitsu.core.api

import com.jiujitsu.core.dto.AttendanceResponse
import com.jiujitsu.core.service.AttendanceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/attendances")
class AttendanceController (
    private val attendanceService: AttendanceService
) {
    @PostMapping
    fun attendance(code: String): AttendanceResponse {
        return attendanceService.register(code)
    }
}