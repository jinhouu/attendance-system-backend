package kr.jiujitsu.manage.dashboard.api

import kr.jiujitsu.manage.common.dto.ApiResponse
import kr.jiujitsu.manage.dashboard.application.DashboardService
import kr.jiujitsu.manage.dashboard.application.result.DashboardStatsResult
import kr.jiujitsu.manage.dashboard.application.result.RecentMemberResult
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dashboard")
class DashboardController(
    private val dashboardService: DashboardService,
) {
    @GetMapping("/stats")
    fun getStats(): ApiResponse<DashboardStatsResult> =
        ApiResponse(
            status = HttpStatus.OK,
            code = 200,
            body = dashboardService.getStats(),
        )

    @GetMapping("/recent-members")
    fun getRecentMembers(): ApiResponse<List<RecentMemberResult>> =
        ApiResponse(
            status = HttpStatus.OK,
            code = 200,
            body = dashboardService.getRecentMembers(),
        )
}
