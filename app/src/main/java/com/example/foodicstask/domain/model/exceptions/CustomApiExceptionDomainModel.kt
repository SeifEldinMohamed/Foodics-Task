package com.example.foodicstask.domain.model.exceptions

sealed class CustomApiExceptionDomainModel : Exception() {

    data object TimeoutExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = TimeoutExceptionDomainModel
    }

    data object NetworkExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = NetworkExceptionDomainModel
    }

    data object RedirectExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = RedirectExceptionDomainModel
    }

    data object ServerExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ServerExceptionDomainModel
    }

    data object InternalServerErrorExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }

    data object ServiceUnavailableExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }

    data object ParsingExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }


    data object ClientExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }

    data object BadRequestExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = BadRequestExceptionDomainModel
    }

    data object UnauthorizedExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }

    data object NotFoundExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = ParsingExceptionDomainModel
    }


    data object UnknownExceptionDomainModel : CustomApiExceptionDomainModel() {
        private fun readResolve(): Any = UnknownExceptionDomainModel
    }

}