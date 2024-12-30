package com.example.foodicstask.data.mapper.exception

import android.database.sqlite.SQLiteAccessPermException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteDatatypeMismatchException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteFullException
import android.database.sqlite.SQLiteMisuseException
import android.database.sqlite.SQLiteReadOnlyDatabaseException
import com.example.foodicstask.data.mapper.exceptions.toCustomDatabaseExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ToCustomDatabaseExceptionDomainModelTest(
    private val inputException: Exception,
    private val expectedOutput: CustomDatabaseExceptionDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputException = {0}, ExpectedOutput = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    SQLiteConstraintException(),
                    CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel
                ),
                arrayOf(
                    SQLiteDatabaseCorruptException(),
                    CustomDatabaseExceptionDomainModel.DatabaseCorruptExceptionDomainModel
                ),
                arrayOf(
                    SQLiteDiskIOException(),
                    CustomDatabaseExceptionDomainModel.DatabaseDiskIOExceptionDomainModel
                ),
                arrayOf(
                    SQLiteFullException(),
                    CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel
                ),
                arrayOf(
                    SQLiteAccessPermException(),
                    CustomDatabaseExceptionDomainModel.DatabaseAccessPermExceptionDomainModel
                ),
                arrayOf(
                    SQLiteReadOnlyDatabaseException(),
                    CustomDatabaseExceptionDomainModel.DatabaseReadOnlyExceptionDomainModel
                ),
                arrayOf(
                    SQLiteDatatypeMismatchException(),
                    CustomDatabaseExceptionDomainModel.DatabaseDatatypeMismatchExceptionDomainModel
                ),
                arrayOf(
                    SQLiteMisuseException(),
                    CustomDatabaseExceptionDomainModel.DatabaseMisuseExceptionDomainModel
                ),
                arrayOf(
                    Exception(),
                    CustomDatabaseExceptionDomainModel.UnknownExceptionDomainModel
                )
            )
        }
    }

    /**
     * Test Case: toCustomDatabaseExceptionDomainModel Mapping
     * Given: A database exception input.
     * When: Converting the input using `toCustomDatabaseExceptionDomainModel()`.
     * Then: It should correctly map and return the expected CustomDatabaseExceptionDomainModel.
     */
    @Test
    fun `toCustomDatabaseExceptionDomainModel, given database exception input, when mapping, then should return expected CustomDatabaseExceptionDomainModel`() {
        // When
        val result = inputException.toCustomDatabaseExceptionDomainModel()

        // Then
        assertEquals(expectedOutput, result)
    }
}
