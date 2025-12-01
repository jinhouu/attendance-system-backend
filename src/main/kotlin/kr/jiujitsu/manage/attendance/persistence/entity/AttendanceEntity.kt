package kr.jiujitsu.manage.attendance.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "attendance")
class AttendanceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val memberId: Long,

    @field:Column(name = "at_year")
    val year: Int,

    @field:Column(name = "at_month")
    val month: Int,

    @field:Column(name = "at_date")
    val day: Int,

    @field:Column(name = "at_time")
    val time: String,

    @field:Column(name = "at_timestamp")
    val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun register(memberId: Long, datetime: LocalDateTime): AttendanceEntity {
            return AttendanceEntity(memberId = memberId, year = datetime.year, month = datetime.month.value, day = datetime.dayOfMonth, time = datetime.toLocalTime().toString())
        }
    }
}