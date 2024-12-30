package com.example.foodicstask.domain.usecase

import com.example.foodicstask.data.repository.FoodRepositoryImpl
import com.example.foodicstask.domain.fake_data.fakeCategoriesDomainModelList
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchCategoriesUseCaseTest {

    private lateinit var foodRepositoryImp: FoodRepositoryImpl
    private lateinit var fetchCategoriesListUseCase: FetchCategoriesListUseCase

    @Before
    fun setup() {
        foodRepositoryImp = mockk()
        fetchCategoriesListUseCase = FetchCategoriesListUseCase(foodRepositoryImp)
    }

    /**
     * Test Case: Success Scenario
     * Given: `fetchCategoryList()` is called from the repository.
     * When: The repository returns a list of categories domain model.
     * Then: The use case should return the same list of categories domain model from the repository.
     */
    @Test
    fun `given successful repository call, when fetching popular movies, then should return repositoryDetailsDomainModel`() {
        runTest {
            // Given
            val expected = fakeCategoriesDomainModelList
            coEvery { foodRepositoryImp.fetchCategoryList() } returns expected

            // When
            val result = fetchCategoriesListUseCase()

            // Then
            assertEquals(expected, result)
        }
    }

    /**
         * Test Case: Network Exception Scenario
         * Given: `fetchCategoryList()` is called from the repository.
         * When: The repository throws a `NetworkExceptionDomainModel`.
         * Then: The use case should propagate the same `NetworkExceptionDomainModel`.
     */
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `given repository throws NetworkExceptionDomainModel, when fetching popular movies, then should throw the same exception`() {
        runTest {
            // Given
            coEvery { foodRepositoryImp.fetchCategoryList() } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // When
            fetchCategoriesListUseCase()
        }
    }

    /**
     * Test Case: Database Full Exception Scenario
     * Given: `fetchCategoryList()` is called from the repository.
     * When: The repository throws a `DatabaseFullExceptionDomainModel`.
     * Then: The use case should propagate the same `DatabaseFullExceptionDomainModel`.
     */
    @Test(expected = CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel::class)
    fun `given repository throws DatabaseFullExceptionDomainModel, when fetching popular movies, then should throw the same exception`() {
        runTest {
            // Given
            coEvery { foodRepositoryImp.fetchCategoryList() } throws CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel

            // When
            fetchCategoriesListUseCase()
        }
    }
}