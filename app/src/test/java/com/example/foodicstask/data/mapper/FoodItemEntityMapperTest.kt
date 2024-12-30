package com.example.foodicstask.data.mapper

import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.FoodItemDataModel
import com.example.foodicstask.data.fake_data.fakeFoodItemEntityList
import com.example.foodicstask.data.fake_data.fakeFoodItemListDataModel
import com.example.foodicstask.data.mapper.food.toFoodItemEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class FoodItemEntityMapperTest(
    private val inputData: FoodItemDataModel,
    private val expectedOutput: FoodItemEntity
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputDataModel = {0}, ExpectedEntity = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(fakeFoodItemListDataModel[0], fakeFoodItemEntityList[0]),
                arrayOf(fakeFoodItemListDataModel[1], fakeFoodItemEntityList[1])
            )
        }
    }

    /**
     * Test Case: toFoodItemEntity Mapping
     * Given: A FoodItemDataModel input data.
     * When: Converting the input to FoodItemEntity using `toFoodItemEntity()`.
     * Then: It should correctly map and return the expected FoodItemEntity.
     */
    @Test
    fun `toFoodItemEntity, given FoodItemDataModel input, when mapping to entity, then should return expected FoodItemEntity`() {
        // When
        val result = inputData.toFoodItemEntity()

        // Then
        assertEquals(expectedOutput, result)
    }
}