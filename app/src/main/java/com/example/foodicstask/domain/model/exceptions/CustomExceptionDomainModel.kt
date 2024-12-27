package com.example.foodicstask.domain.model.exceptions

sealed class CustomExceptionDomainModel : Exception() {
    data class Api(val apiException: CustomApiExceptionDomainModel) : CustomExceptionDomainModel()
    data class Database(val databaseException: CustomDatabaseExceptionDomainModel) : CustomExceptionDomainModel()
}