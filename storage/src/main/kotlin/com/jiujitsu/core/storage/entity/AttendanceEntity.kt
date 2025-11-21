package com.jiujitsu.core.storage.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "attendance")
class AttendanceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val userId: Long,
    val date: LocalDateTime,
)