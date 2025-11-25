package com.jiujitsu.core.service

import com.jiujitsu.core.application.MemberFinder
import com.jiujitsu.core.application.MemberManager
import com.jiujitsu.core.dto.MemberRegisterRequest
import com.jiujitsu.core.dto.MemberResponse
import com.jiujitsu.core.enum.BeltGrade
import com.jiujitsu.core.exception.CodeValidFailException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberServiceTest {

    val memberManager = mockk<MemberManager>()
    val memberFinder = mockk<MemberFinder>()
    val service = MemberService(memberManager, memberFinder)


    @Test
    fun `회원등록 성공`() {
        val member = MemberRegisterRequest(
            "1234",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )
        every { memberFinder.codeIsDuplicated(any()) } returns false
        every { memberManager.register(any(), any(), any(), any(), any()) } returns MemberResponse(name=member.name, code=member.code, phone=member.phone)

        val response = service.register(member)

        assertEquals(member.name, response.name)
        assertEquals(member.code, response.code)
        assertEquals(member.phone, response.phone)
    }

    @Test
    fun `code 길이 부적합`() {

        val member1 = MemberRegisterRequest(
            "",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )

        val member2 = MemberRegisterRequest(
            "124124",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )

        val member3 = MemberRegisterRequest(
            "123",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )
        every { memberFinder.codeIsDuplicated(any()) } returns false

        assertThrows<CodeValidFailException> { service.register(member1) }
        assertThrows<CodeValidFailException> { service.register(member2) }
        assertThrows<CodeValidFailException> { service.register(member3) }
    }


    @Test
    fun `code 숫자 외 문자열`() {
        val memberRequestSuccess = MemberRegisterRequest(
            "한글",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )
        every { memberFinder.codeIsDuplicated(memberRequestSuccess.code) } returns false

        assertThrows<CodeValidFailException> { service.register(memberRequestSuccess) }
    }


    @Test
    fun `code 중복`() {
        val memberRequestSuccess = MemberRegisterRequest(
            "한글",
            "code",
            BeltGrade.BLUE,
            1,
            "phone"
        )
        every { memberFinder.codeIsDuplicated(memberRequestSuccess.code) } returns true

        assertThrows<CodeValidFailException> { service.register(memberRequestSuccess) }
    }

}
