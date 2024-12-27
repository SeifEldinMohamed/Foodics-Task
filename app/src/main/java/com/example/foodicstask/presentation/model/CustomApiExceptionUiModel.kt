package com.example.foodicstask.presentation.model

sealed class CustomApiExceptionUiModel {
    data object Network : CustomApiExceptionUiModel()
    data object Timeout : CustomApiExceptionUiModel()
    data object ServiceUnreachable : CustomApiExceptionUiModel()
    data object ServerError : CustomApiExceptionUiModel()
    data object BadRequest : CustomApiExceptionUiModel()
    data object Unauthorized : CustomApiExceptionUiModel()
    data object NotFound : CustomApiExceptionUiModel()
    data object Unknown : CustomApiExceptionUiModel()
}