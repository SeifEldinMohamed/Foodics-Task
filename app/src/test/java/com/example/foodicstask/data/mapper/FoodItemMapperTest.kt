package com.example.foodicstask.data.mapper

import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.data.fake_data.fakeFoodItemEntityList
import com.example.foodicstask.data.mapper.food.toFoodItemDomainModel
import com.example.foodicstask.domain.fake_data.fakeFoodItemsDomainModelList
import com.example.foodicstask.domain.model.FoodItemDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class FoodItemMapperTest(
    private val inputData: FoodItemEntity,
    private val expectedOutput: FoodItemDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputEntity = {0}, ExpectedDomainModel = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(fakeFoodItemEntityList[0], fakeFoodItemsDomainModelList[0]),
                arrayOf(fakeFoodItemEntityList[1], fakeFoodItemsDomainModelList[1])
            )
        }
    }

    /**
     * Test Case: toFoodItemDomainModel Mapping
     * Given: A FoodItemEntity input data.
     * When: Converting the input to FoodItemDomainModel using `toFoodItemDomainModel()`.
     * Then: It should correctly map and return the expected FoodItemDomainModel.
     */
    @Test
    fun `toFoodItemDomainModel, given FoodItemEntity input, when mapping to domain model, then should return expected FoodItemDomainModel`() {
        // When
        val result = inputData.toFoodItemDomainModel()

        // Then
        assertEquals(expectedOutput, result)
    }
}