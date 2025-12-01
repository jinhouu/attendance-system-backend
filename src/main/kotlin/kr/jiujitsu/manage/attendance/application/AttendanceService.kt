package kr.jiujitsu.manage.attendance.application

import kr.jiujitsu.manage.attendance.application.command.GetDailyAttendanceCommand
import kr.jiujitsu.manage.attendance.application.result.AttendanceResult
import kr.jiujitsu.manage.attendance.persistence.entity.AttendanceEntity
import kr.jiujitsu.manage.attendance.persistence.repository.AttendanceRepository
import kr.jiujitsu.manage.member.application.exception.MemberNotFoundException
import kr.jiujitsu.manage.member.application.result.MemberResult
import kr.jiujitsu.manage.member.application.service.MemberFinder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AttendanceService (
    private val memberFinder: MemberFinder,
    private val attendanceRepository: AttendanceRepository,
) {
    fun register(code: String): AttendanceResult {
        val member: MemberResult = memberFinder.findByCode(code)?: throw MemberNotFoundException()

        val attendanceTime = LocalDateTime.now()

        val attendance = attendanceRepository.save(
            AttendanceEntity(
                memberId = member.id!!,
                year = attendanceTime.year,
                month = attendanceTime.month.value,
                day = attendanceTime.dayOfMonth,
                time = (attendanceTime.hour.toString() + ":" + attendanceTime.minute),
                timestamp = attendanceTime
            )
        )

        return AttendanceResult(
            attendance.id!!,
            attendance.timestamp.toString(),
            attendance.time,
            member
        )
    }

    fun getDaily(command: GetDailyAttendanceCommand) : List<AttendanceResult> {
        val date = command.date

        return attendanceRepository.findByYearAndMonthAndDayOrderByTimeAsc(date.year, date.month.value, date.dayOfMonth)
            .map { AttendanceResult(it.id!!, it.timestamp.toString(), it.time, memberFinder.find(it.memberId)!!) }
    }
}