package com.jiujitsu.core.vo

import com.jiujitsu.core.enum.BeltColor
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class BeltRank(
    @Enumerated(EnumType.STRING)
    val color: BeltColor,
    val grau: Int
)
