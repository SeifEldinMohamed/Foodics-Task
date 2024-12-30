package com.example.foodicstask.presentation.mapper

import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import com.example.foodicstask.presentation.mapper.exception.toCustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.model.CustomDatabaseExceptionUiModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CustomDatabaseExceptionDomainModelToCustomDatabaseExceptionUiModelTest(
    private val inputDomainModel: CustomDatabaseExceptionDomainModel,
    private val expectedUiModel: CustomDatabaseExceptionUiModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputDomainModel = {0}, ExpectedUiModel = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseCorruptExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseDiskIOExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseAccessPermExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseReadOnlyExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseDatatypeMismatchExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseMisuseExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.DatabaseOperationExceptionDomainModel, CustomDatabaseExceptionUiModel.DatabaseError),
                arrayOf(CustomDatabaseExceptionDomainModel.UnknownExceptionDomainModel, CustomDatabaseExceptionUiModel.Unknown)
            )
        }
    }

    /**
     * Test Case: toCustomDatabaseExceptionUiModel Mapping
     * Given: A CustomDatabaseExceptionDomainModel input data.
     * When: Converting the input to CustomDatabaseExceptionUiModel using `toCustomDatabaseExceptionUiModel()`.
     * Then: It should correctly map and return the expected CustomDatabaseExceptionUiModel.
     */
    @Test
    fun `toCustomDatabaseExceptionUiModel, given CustomDatabaseExceptionDomainModel input, when mapping to UI model, then should return expected CustomDatabaseExceptionUiModel`() {
        // When
        val result = inputDomainModel.toCustomDatabaseExceptionUiModel()

        // Then
        assertEquals(expectedUiModel, result)
    }
}
