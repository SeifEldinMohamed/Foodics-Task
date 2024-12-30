package com.example.foodicstask.data.mapper.exception

import com.example.foodicstask.data.mapper.exceptions.toCustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.ServiceUnavailable
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.mockk.every
import io.mockk.mockk
import kotlinx.io.IOException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ToCustomApiExceptionDomainModelTest(
    private val inputException: Throwable,
    private val expectedOutput: CustomExceptionDomainModel,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputException = {0}, ExpectedOutput = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    getClientResponseException(BadRequest),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.BadRequestExceptionDomainModel)
                ),
                arrayOf(
                    getClientResponseException(Unauthorized),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.UnauthorizedExceptionDomainModel)
                ),
                arrayOf(
                    getClientResponseException(NotFound),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.NotFoundExceptionDomainModel)
                ),
                arrayOf(
                    getServerResponseException(InternalServerError),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.InternalServerErrorExceptionDomainModel)
                ),

                arrayOf(
                    getServerResponseException(ServiceUnavailable),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel)
                ),
                arrayOf(
                    RedirectResponseException(
                        response = mockk(relaxed = true) {
                            every { status } returns NotFound
                        },
                        cachedResponseText = ""
                    ),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.RedirectExceptionDomainModel)
                ),
                arrayOf(
                    HttpRequestTimeoutException(HttpRequestBuilder()),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.TimeoutExceptionDomainModel)
                ),
                arrayOf(
                    IOException(),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.NetworkExceptionDomainModel)
                ),
                arrayOf(
                    Throwable(),
                    CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.UnknownExceptionDomainModel)
                )
            )
        }
    }

    /**
     * Test Case: toCustomApiExceptionDomainModel Mapping
     * Given: An exception input.
     * When: Converting the input using `toCustomApiExceptionDomainModel()`.
     * Then: It should correctly map and return the expected CustomExceptionDomainModel.
     */
    @Test
    fun `toCustomApiExceptionDomainModel, given exception input, when mapping, then should return expected CustomExceptionDomainModel`() {
        // When
        val result = inputException.toCustomApiExceptionDomainModel()

        // Then
        assertEquals(expectedOutput, result)
    }
}

private fun getClientResponseException(httpStatusCode: HttpStatusCode): ClientRequestException {
    return ClientRequestException(
        response = mockk(relaxed = true) {
            every { status } returns httpStatusCode
        },
        cachedResponseText = ""
    )
}

private fun getServerResponseException(httpStatusCode: HttpStatusCode): ServerResponseException {
    return ServerResponseException(
        response = mockk(relaxed = true) {
            every { status } returns httpStatusCode
        },
        cachedResponseText = ""
    )
}
