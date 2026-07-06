package kr.jiujitsu.manage.common.advice

import kr.jiujitsu.manage.common.dto.ApiResponse
import kr.jiujitsu.manage.common.dto.ErrorResponse
import kr.jiujitsu.manage.member.application.exception.CodeValidFailException
import kr.jiujitsu.manage.member.application.exception.MemberNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MemberNotFoundException::class)
    fun handleMemberNotFound(exception: MemberNotFoundException): ApiResponse<ErrorResponse> =
        ApiResponse(
            status = HttpStatus.NOT_FOUND,
            code = 404,
            body = ErrorResponse(exception.message ?: "Member Not Found"),
        )

    @ExceptionHandler(CodeValidFailException::class)
    fun handleCodeValidFail(exception: CodeValidFailException): ApiResponse<ErrorResponse> =
        ApiResponse(
            status = HttpStatus.BAD_REQUEST,
            code = 400,
            body = ErrorResponse(exception.message ?: "Invalid code"),
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ApiResponse<ErrorResponse> {
        val errors =
            exception.bindingResult
                .fieldErrors
                .map { "${it.field}: ${it.defaultMessage ?: "invalid value"}" }

        return ApiResponse(
            status = HttpStatus.BAD_REQUEST,
            code = 400,
            body = ErrorResponse("요청 값이 올바르지 않습니다.", errors),
        )
    }

    @ExceptionHandler(
        MissingServletRequestParameterException::class,
        MethodArgumentTypeMismatchException::class,
    )
    fun handleBadRequest(exception: Exception): ApiResponse<ErrorResponse> =
        ApiResponse(
            status = HttpStatus.BAD_REQUEST,
            code = 400,
            body = ErrorResponse(exception.message ?: "Bad Request"),
        )
}
