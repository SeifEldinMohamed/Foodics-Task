package com.example.foodicstask.domain.usecase

import com.example.foodicstask.data.repository.FoodRepositoryImpl
import com.example.foodicstask.domain.fake_data.fakeFoodItemsDomainModelList
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchFoodListUseCaseTest {

    private lateinit var foodRepositoryImp: FoodRepositoryImpl
    private lateinit var fetchFoodListUseCase: FetchFoodListUseCase

    @Before
    fun setup() {
        foodRepositoryImp = mockk()
        fetchFoodListUseCase = FetchFoodListUseCase(foodRepositoryImp)
    }

    /**
     * Test Case: Success Scenario
     * Given: `fetchFoodList()` is called from the repository.
     * When: The repository returns a list of foodDomainModel.
     * Then: The use case should return the same list of foodDomainModel from the repository.
     */
    @Test
    fun `given successful repository call, when fetching foodList, then should return list of foodDomainModel`() {
        runTest {
            // Given
            val expected = fakeFoodItemsDomainModelList
            coEvery { foodRepositoryImp.fetchFoodList() } returns expected

            // When
            val result = fetchFoodListUseCase()

            // Then
            assertEquals(expected, result)
        }
    }

    /**
         * Test Case: Network Exception Scenario
         * Given: `fetchFoodList()` is called from the repository.
         * When: The repository throws a `NetworkExceptionDomainModel`.
         * Then: The use case should propagate the same `NetworkExceptionDomainModel`.
     */
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `given repository throws NetworkExceptionDomainModel, when fetching foodList, then should throw the same exception`() {
        runTest {
            // Given
            coEvery { foodRepositoryImp.fetchFoodList() } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // When
            fetchFoodListUseCase()
        }
    }

    /**
     * Test Case: Database Full Exception Scenario
     * Given: `fetchFoodList()` is called from the repository.
     * When: The repository throws a `DatabaseFullExceptionDomainModel`.
     * Then: The use case should propagate the same `DatabaseFullExceptionDomainModel`.
     */
    @Test(expected = CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel::class)
    fun `given repository throws DatabaseFullExceptionDomainModel, when fetching foodList, then should throw the same exception`() {
        runTest {
            // Given
            coEvery { foodRepositoryImp.fetchFoodList() } throws CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel

            // When
            fetchFoodListUseCase()
        }
    }
}