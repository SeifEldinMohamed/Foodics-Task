package com.example.foodicstask.domain.model.exceptions

sealed class CustomDatabaseExceptionDomainModel: Exception() {
    data object DatabaseConstraintExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseConstraintExceptionDomainModel
    }

    data object DatabaseCorruptExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseCorruptExceptionDomainModel
    }

    data object DatabaseDiskIOExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseDiskIOExceptionDomainModel
    }

    data object DatabaseFullExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseFullExceptionDomainModel
    }

    data object DatabaseAccessPermExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseAccessPermExceptionDomainModel
    }

    data object DatabaseReadOnlyExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseReadOnlyExceptionDomainModel
    }

    data object DatabaseDatatypeMismatchExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseDatatypeMismatchExceptionDomainModel
    }

    data object DatabaseMisuseExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseMisuseExceptionDomainModel
    }

    data object DatabaseOperationExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = DatabaseOperationExceptionDomainModel
    }

    data object UnknownExceptionDomainModel : CustomDatabaseExceptionDomainModel() {
        private fun readResolve(): Any = UnknownExceptionDomainModel
    }
}