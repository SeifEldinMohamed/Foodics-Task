package com.example.foodicstask.data.mapper.exceptions

import android.util.Log
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.ServiceUnavailable
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import java.io.IOException


fun Throwable.toCustomApiExceptionDomainModel(): CustomExceptionDomainModel {
    return when (this) {

        is ClientRequestException -> {
            // Represents client-side errors, such as invalid requests (e.g., incorrect parameters, missing headers) (4xx error)
            Log.d("API Exceptions", "ClientRequestException: ${this.response.status.description}")
            when (this.response.status) {
                BadRequest -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.BadRequestExceptionDomainModel)
                Unauthorized -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.UnauthorizedExceptionDomainModel)
                NotFound -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.NotFoundExceptionDomainModel)
                else -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.ClientExceptionDomainModel)
            }
        }

        is ServerResponseException -> {
            // Represents server-side errors, such as internal server errors, service unavailable (5xx error)
            Log.d("API Exceptions", "ServerResponseException: ${this.response.status.description}")
            when (this.response.status) {
                InternalServerError -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.InternalServerErrorExceptionDomainModel)
                ServiceUnavailable -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel)
                else -> CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.ServerExceptionDomainModel)
            }
        }

        is RedirectResponseException -> {
            // Represents issues with handling HTTP redirects. (3xx error)
            Log.d(
                "API Exceptions",
                "RedirectResponseException: ${this.response.status.description}"
            )
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.RedirectExceptionDomainModel)
        }

        is HttpRequestTimeoutException -> {
            // Represents cases where the request timed out before a response was received.
            Log.d("API Exceptions", "HttpRequestTimeoutException: ${this.localizedMessage}")
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.TimeoutExceptionDomainModel)
        }

        is NoTransformationFoundException -> {
            // Transformation/parsing issue (e.g., response type mismatch)
            Log.d("API Exceptions", "NoTransformationFoundException: ${this.localizedMessage}")
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.ParsingExceptionDomainModel)
        }

        is IOException -> {
            // Networking error (e.g., no internet connection, network timeout)
            Log.d("API Exceptions", "IOException: ${this.localizedMessage}")
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.NetworkExceptionDomainModel)
        }

        else -> {
            Log.d("API Exceptions", "UnknownException: ${this.localizedMessage}")
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.UnknownExceptionDomainModel)
        }
    }
}