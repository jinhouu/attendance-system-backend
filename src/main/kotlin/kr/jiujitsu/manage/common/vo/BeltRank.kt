package kr.jiujitsu.manage.common.vo

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import kr.jiujitsu.manage.common.enums.BeltGrade

@Embeddable
data class BeltRank(
    @Enumerated(EnumType.STRING)
    val belt: BeltGrade,
    val grau: Int
)
