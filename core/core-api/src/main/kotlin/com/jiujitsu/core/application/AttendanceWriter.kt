package com.jiujitsu.core.application

import com.jiujitsu.core.storage.entity.AttendanceEntity
import com.jiujitsu.core.storage.repository.AttendanceRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AttendanceWriter (
    private val attendanceRepository: AttendanceRepository,
) {
    fun register(memberId: Long, datetime: LocalDateTime) {
        val entity = AttendanceEntity(userId = memberId, date = datetime)
        attendanceRepository.save(entity)
    }
}
