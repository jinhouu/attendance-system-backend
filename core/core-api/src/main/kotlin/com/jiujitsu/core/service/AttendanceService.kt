package com.jiujitsu.core.service

import com.jiujitsu.core.application.AttendanceWriter
import com.jiujitsu.core.application.MemberFinder
import com.jiujitsu.core.dto.AttendanceResponse
import com.jiujitsu.core.dto.MemberResponse
import com.jiujitsu.core.exception.MemberNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AttendanceService (
    private val memberFinder: MemberFinder,
    private val attendanceWriter: AttendanceWriter
) {
    fun register(code: String): AttendanceResponse {
        val member: MemberResponse = (memberFinder.findByCode(code)?: { throw MemberNotFoundException() }) as MemberResponse
        attendanceWriter.register(member.id!!, LocalDateTime.now())
        return AttendanceResponse(
            LocalDateTime.now().toString(),
            member
        )
    }
}