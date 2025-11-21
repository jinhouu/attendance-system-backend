package com.jiujitsu.core.storage.repository

import com.jiujitsu.core.storage.entity.AttendanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AttendanceRepository: JpaRepository<AttendanceEntity, Long> {
}