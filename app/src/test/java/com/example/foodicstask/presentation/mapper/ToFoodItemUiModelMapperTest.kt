package com.example.foodicstask.presentation.mapper

import com.example.foodicstask.domain.fake_data.fakeFoodItemsDomainModelList
import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.presentation.fake_data.fakeFoodItemUiModelList
import com.example.foodicstask.presentation.mapper.food.toFoodItemUIModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ToMovieUiModelMapperTest(
    private val inputData: FoodItemDomainModel,
    private val expectedOutput: FoodItemUiModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputModel = {0}, Expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(fakeFoodItemsDomainModelList[0], fakeFoodItemUiModelList[0]),
                arrayOf(fakeFoodItemsDomainModelList[1], fakeFoodItemUiModelList[1])
            )
        }
    }

    /**
     * Test Case: toFoodItemUiModel Mapping
     * Given: A FoodItemDomainModel input data.
     * When: Converting the input to FoodItemUiModel using `toFoodItemUiModel()`.
     * Then: It should correctly map and return the expected FoodItemUiModel.
     */
    @Test
    fun `toFoodItemUiModel, given FoodItemUiModel input, when mapping to ui model, then should return expected FoodItemUiModel`() {
        // When
        val result = inputData.toFoodItemUIModel()

        // Then
        assertEquals(expectedOutput, result)
    }
}