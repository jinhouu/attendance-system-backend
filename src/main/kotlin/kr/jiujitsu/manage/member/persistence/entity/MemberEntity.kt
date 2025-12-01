package kr.jiujitsu.manage.member.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kr.jiujitsu.manage.common.entity.BaseEntity
import kr.jiujitsu.manage.common.enums.BeltGrade
import kr.jiujitsu.manage.common.vo.BeltRank
import java.time.LocalDate

@Entity
@Table(name = "member")
class MemberEntity (
    override val id: Long? = null,
    val name: String,
    val code: String,
    val phone: String,

    @Enumerated(EnumType.STRING)
    val belt: BeltRank,

    val registrationDate: LocalDate,
    val expirationDate: LocalDate,
): BaseEntity(id) {
    companion object {
        fun register(name: String, code: String, phone: String, belt: BeltGrade, grau: Int): MemberEntity {
            return MemberEntity(
                name = name,
                code = code,
                phone = phone,
                belt = BeltRank(belt, grau),
                registrationDate = LocalDate.now(),
                expirationDate = LocalDate.now(),
            )
        }
    }
}