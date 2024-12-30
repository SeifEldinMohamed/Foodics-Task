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

class FilteredFoodListByCategoryUseCaseTest {

    private lateinit var foodRepository: FoodRepository
    private lateinit var filteredFoodListByCategoryUseCase: FilteredFoodListByCategoryUseCase

    @Before
    fun setup() {
        foodRepository = mockk()
        filteredFoodListByCategoryUseCase = FilteredFoodListByCategoryUseCase(foodRepository)
    }

    /**
     * Test Case: Success Scenario
     * Given: `filteredFoodListByCategory()` is called from the repository.
     * When: The repository returns a list of food items domain model.
     * Then: The use case should return the same list of food items domain model from the repository.
     */
    @Test
    fun `given successful repository call, when filtering food list by category, then should return list of food items`() =
        runTest {
            // Given
            val selectedCategoryId = 1
            val expected = fakeFoodItemsDomainModelList
            coEvery { foodRepository.filteredFoodListByCategory(selectedCategoryId) } returns expected

            // When
            val result = filteredFoodListByCategoryUseCase(selectedCategoryId)

            // Then
            assertEquals(expected, result)
        }

    /**
     * Test Case: Exception Scenario
     * Given: `filteredFoodListByCategory()` is called from the repository.
     * When: The repository throws an exception.
     * Then: The use case should propagate the same exception.
     */
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `given repository throws exception, when filtering food list by category, then should propagate exception`() =
        runTest {
            // Given
            val selectedCategoryId = 1
            coEvery { foodRepository.filteredFoodListByCategory(selectedCategoryId) } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // When
            filteredFoodListByCategoryUseCase(selectedCategoryId)
        }
}
