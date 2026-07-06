package kr.jiujitsu.manage.dashboard.application.result

data class DashboardStatsResult(
    val todayAttendance: Long,
    val totalMembers: Long,
    val newMembersThisWeek: Long,
    val expiringMembers: Long,
)
