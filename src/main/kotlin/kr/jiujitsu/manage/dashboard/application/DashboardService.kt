package kr.jiujitsu.manage.dashboard.application

import kr.jiujitsu.manage.attendance.persistence.repository.AttendanceRepository
import kr.jiujitsu.manage.dashboard.application.result.DashboardStatsResult
import kr.jiujitsu.manage.dashboard.application.result.RecentMemberResult
import kr.jiujitsu.manage.member.application.service.MemberFinder
import kr.jiujitsu.manage.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DashboardService(
    private val attendanceRepository: AttendanceRepository,
    private val memberRepository: MemberRepository,
    private val memberFinder: MemberFinder,
) {
    fun getStats(): DashboardStatsResult {
        val today = LocalDate.now()

        return DashboardStatsResult(
            todayAttendance = attendanceRepository.countByYearAndMonthAndDay(today.year, today.month.value, today.dayOfMonth),
            totalMembers = memberRepository.count(),
            newMembersThisWeek = memberRepository.countByRegistrationDateGreaterThanEqual(today.minusDays(6)),
            expiringMembers = memberRepository.countByExpirationDateBetween(today, today.plusDays(7)),
        )
    }

    fun getRecentMembers(): List<RecentMemberResult> {
        val today = LocalDate.now()

        return attendanceRepository
            .findTop5ByYearAndMonthAndDayOrderByTimestampDesc(today.year, today.month.value, today.dayOfMonth)
            .map {
                val member = memberFinder.find(it.memberId)

                RecentMemberResult(
                    name = member.name,
                    belt =
                        member.belt.belt.name
                            .lowercase(),
                    time = it.time,
                )
            }
    }
}
