package com.example.foodicstask.data.mapper.category

import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.fake_data.fakeCategoryEntityList
import com.example.foodicstask.domain.fake_data.fakeCategoryDomainModelList
import com.example.foodicstask.domain.model.CategoryDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CategoryEntityToCategoryDomainModelTest(
    private val inputCategoryEntity: CategoryEntity,
    private val expectedCategoryDomainModel: CategoryDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputCategoryEntity = {0}, ExpectedCategoryDomainModel = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                // Replace with actual mock data for CategoryEntity and CategoryDomainModel
                arrayOf(fakeCategoryEntityList[0], fakeCategoryDomainModelList[0]),
                arrayOf(fakeCategoryEntityList[1], fakeCategoryDomainModelList[1])
            )
        }
    }

    /**
     * Test Case: toCategoryDomainModel Mapping
     * Given: A CategoryEntity input data.
     * When: Converting the input to CategoryDomainModel using `toCategoryDomainModel()`.
     * Then: It should correctly map and return the expected CategoryDomainModel.
     */
    @Test
    fun `toCategoryDomainModel, given CategoryEntity input, when mapping to domain model, then should return expected CategoryDomainModel`() {
        // When
        val result = inputCategoryEntity.toCategoryDomainModel()

        // Then
        assertEquals(expectedCategoryDomainModel, result)
    }
}
