package com.jiujitsu.core.vo

import com.jiujitsu.core.enum.BeltGrade
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class BeltRank(
    @Enumerated(EnumType.STRING)
    val belt: BeltGrade,
    val grau: Int
)
