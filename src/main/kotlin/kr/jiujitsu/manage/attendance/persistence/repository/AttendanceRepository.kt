package kr.jiujitsu.manage.attendance.persistence.repository

import kr.jiujitsu.manage.attendance.persistence.entity.AttendanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AttendanceRepository: JpaRepository<AttendanceEntity, Long> {
    fun findByYearAndMonthAndDayOrderByTimeAsc(year: Int, month: Int, day: Int): List<AttendanceEntity>
}