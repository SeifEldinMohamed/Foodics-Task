package com.example.foodicstask.presentation.mapper

import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel


fun CustomApiExceptionDomainModel.toCustomApiExceptionUiModel(): CustomApiExceptionUiModel {
    return when (this) {
        is CustomApiExceptionDomainModel.NetworkExceptionDomainModel -> CustomApiExceptionUiModel.Network
        is CustomApiExceptionDomainModel.TimeoutExceptionDomainModel -> CustomApiExceptionUiModel.Timeout
        is CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel -> CustomApiExceptionUiModel.ServiceUnreachable
        is CustomApiExceptionDomainModel.InternalServerErrorExceptionDomainModel -> CustomApiExceptionUiModel.ServerError
        is CustomApiExceptionDomainModel.BadRequestExceptionDomainModel -> CustomApiExceptionUiModel.BadRequest
        is CustomApiExceptionDomainModel.UnauthorizedExceptionDomainModel -> CustomApiExceptionUiModel.Unauthorized
        is CustomApiExceptionDomainModel.NotFoundExceptionDomainModel -> CustomApiExceptionUiModel.NotFound
        else -> {
            CustomApiExceptionUiModel.Unknown
        }
    }
}