package com.jiujitsu.core.application

import com.jiujitsu.core.dto.MemberResponse

import com.jiujitsu.core.enum.BeltGrade

import com.jiujitsu.core.storage.entity.MemberEntity

import com.jiujitsu.core.storage.repository.MemberRepository

import org.springframework.stereotype.Component



@Component

class MemberManager (

    private val memberRepository: MemberRepository,

) {

    fun register(name: String, code: String, phone: String, belt: BeltGrade, grau: Int): MemberResponse {

        val memberEntity = MemberEntity.register(name, code, phone, belt, grau)

        return MemberResponse.fromEntity (memberRepository.save(memberEntity))

    }

}