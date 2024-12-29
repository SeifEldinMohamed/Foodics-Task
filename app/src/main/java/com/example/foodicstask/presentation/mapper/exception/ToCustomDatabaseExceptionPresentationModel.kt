package com.example.foodicstask.presentation.mapper.exception


import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import com.example.foodicstask.presentation.model.CustomDatabaseExceptionUiModel

fun CustomDatabaseExceptionDomainModel.toCustomDatabaseExceptionUiModel(): CustomDatabaseExceptionUiModel {
    return when (this) {
        is CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseCorruptExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseDiskIOExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseAccessPermExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseReadOnlyExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseDatatypeMismatchExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseMisuseExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseOperationExceptionDomainModel -> CustomDatabaseExceptionUiModel.DatabaseError
        
        is CustomDatabaseExceptionDomainModel.UnknownExceptionDomainModel -> CustomDatabaseExceptionUiModel.Unknown
    }
}