package kr.jiujitsu.manage.common.dto

data class ErrorResponse(
    val message: String,
    val errors: List<String> = emptyList(),
)
