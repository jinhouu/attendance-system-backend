package kr.jiujitsu.manage.member.application.result

import kr.jiujitsu.manage.common.vo.BeltRank
import kr.jiujitsu.manage.member.persistence.entity.MemberEntity
import java.time.LocalDate

class MemberResult(

    val id: Long? = null,

    val name: String,

    val code: String,

    val phone: String,

    val belt: BeltRank,

    val registrationDate: LocalDate = LocalDate.now(),

    val expirationDate: LocalDate = LocalDate.now()

) {

    companion object {

        fun fromEntity(entity: MemberEntity): MemberResult {

            return MemberResult(

                entity.id,

                entity.name,

                entity.code,

                entity.phone,

                entity.belt,

                entity.registrationDate,

                entity.expirationDate,

            )

        }

    }

}