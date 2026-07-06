package kr.jiujitsu.manage.member.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.jiujitsu.manage.common.entity.BaseEntity
import kr.jiujitsu.manage.common.enums.Belt
import kr.jiujitsu.manage.common.vo.BeltRank
import java.time.LocalDate

@Entity
@Table(name = "member")
class MemberEntity(
    val name: String,
    var code: String,
    var phone: String,
    @Enumerated(EnumType.STRING)
    var belt: BeltRank,
    var registrationDate: LocalDate,
    var expirationDate: LocalDate,
) : BaseEntity() {
    companion object {
        fun register(
            name: String,
            code: String,
            phone: String,
            belt: Belt,
            grau: Int,
        ): MemberEntity =
            MemberEntity(
                name = name,
                code = code,
                phone = phone,
                belt = BeltRank(belt, grau),
                registrationDate = LocalDate.now(),
                expirationDate = LocalDate.now(),
            )
    }

    fun update(
        code: String,
        phone: String,
        belt: Belt,
        grau: Int,
    ) {
        this.code = code
        this.phone = phone
        this.belt = BeltRank(belt, grau)
    }
}
