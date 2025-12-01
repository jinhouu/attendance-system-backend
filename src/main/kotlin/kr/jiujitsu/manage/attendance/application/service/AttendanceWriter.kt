package kr.jiujitsu.manage.attendance.application.service

import kr.jiujitsu.manage.attendance.persistence.entity.AttendanceEntity
import kr.jiujitsu.manage.attendance.persistence.repository.AttendanceRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AttendanceWriter (
    private val attendanceRepository: AttendanceRepository,
) {
    fun register(memberId: Long, datetime: LocalDateTime) {
        val entity = AttendanceEntity.Companion.register(memberId = memberId, datetime = datetime)
        attendanceRepository.save(entity)
    }
}