package kr.jiujitsu.manage.dashboard.application.result

data class RecentMemberResult(
    val name: String,
    val belt: String,
    val time: String,
    val avatar: String = "",
)
