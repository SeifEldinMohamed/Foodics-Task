package com.example.foodicstask.domain.usecase

import com.example.foodicstask.domain.fake_data.fakeFoodItemsDomainModelList
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.repository.FoodRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchFoodListByNameUseCaseTest {

    private lateinit var foodRepository: FoodRepository
    private lateinit var searchFoodListByNameUseCase: SearchFoodListByNameUseCase

    @Before
    fun setup() {
        foodRepository = mockk()
        searchFoodListByNameUseCase = SearchFoodListByNameUseCase(foodRepository)
    }

    /**
     * Test Case: Success Scenario
     * Given: `searchFoodListByName()` is called from the repository.
     * When: The repository returns a list of food items domain model.
     * Then: The use case should return the same list of food items domain model from the repository.
     */
    @Test
    fun `given successful repository call, when searching food by name, then should return list of food items`() =
        runTest {
            // Given
            val query = "Pizza"
            val expected = fakeFoodItemsDomainModelList
            coEvery { foodRepository.searchFoodListByName(query) } returns expected

            // When
            val result = searchFoodListByNameUseCase(query)

            // Then
            assertEquals(expected, result)
        }

    /**
     * Test Case: Exception Scenario
     * Given: `searchFoodListByName()` is called from the repository.
     * When: The repository throws an exception.
     * Then: The use case should propagate the same exception.
     */
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `given repository throws exception, when searching food by name, then should propagate exception`() =
        runTest {
            // Given
            val query = "Pizza"
            coEvery { foodRepository.searchFoodListByName(query) } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // When
            searchFoodListByNameUseCase(query)
        }
}
