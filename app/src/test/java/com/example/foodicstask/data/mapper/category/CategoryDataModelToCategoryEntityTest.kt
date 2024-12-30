package com.example.foodicstask.data.mapper.category

import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.CategoryDataModel
import com.example.foodicstask.data.fake_data.fakeCategoryEntityList
import com.example.foodicstask.data.fake_data.fakeCategoryListDataModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CategoryDataModelToCategoryEntityTest(
    private val inputCategoryDataModel: CategoryDataModel,
    private val expectedCategoryEntity: CategoryEntity,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputCategoryDataModel = {0}, ExpectedCategoryEntity = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                // Replace with actual mock data for CategoryDataModel and CategoryEntity
                arrayOf(fakeCategoryListDataModel[0], fakeCategoryEntityList[0]),
                arrayOf(fakeCategoryListDataModel[1], fakeCategoryEntityList[1])
            )
        }
    }

    /**
     * Test Case: toCategoryEntity Mapping
     * Given: A CategoryDataModel input data.
     * When: Converting the input to CategoryEntity using `toCategoryEntity()`.
     * Then: It should correctly map and return the expected CategoryEntity.
     */
    @Test
    fun `toCategoryEntity, given CategoryDataModel input, when mapping to entity, then should return expected CategoryEntity`() {
        // When
        val result = inputCategoryDataModel.toCategoryEntity()

        // Then
        assertEquals(expectedCategoryEntity, result)
    }
}
