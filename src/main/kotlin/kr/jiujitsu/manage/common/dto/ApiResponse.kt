package kr.jiujitsu.manage.common.dto

import org.springframework.http.HttpStatus

data class ApiResponse<T> (
    val status: HttpStatus,
    val code: Int,
    val body: T?,
)