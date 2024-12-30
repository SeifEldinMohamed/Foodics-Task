package com.example.foodicstask.data.mapper.cart

import com.example.foodicstask.data.data_sources.local.room.entities.CartItemEntity
import com.example.foodicstask.data.fake_data.fakeCartItemEntityList
import com.example.foodicstask.domain.fake_data.fakeCartItemDomainModelList
import com.example.foodicstask.domain.model.CartItemDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CartItemMapperTest(
    private val inputCartItemEntity: CartItemEntity,
    private val expectedCartItemDomainModel: CartItemDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputCartItemEntity = {0}, ExpectedCartItemDomainModel = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(fakeCartItemEntityList[0], fakeCartItemDomainModelList[0]),
                arrayOf(fakeCartItemEntityList[1], fakeCartItemDomainModelList[1])
            )
        }
    }

    /**
     * Test Case: toCartItemDomainModel Mapping
     * Given: A CartItemEntity input data.
     * When: Converting the input to CartItemDomainModel using `toCartItemDomainModel()`.
     * Then: It should correctly map and return the expected CartItemDomainModel.
     */
    @Test
    fun `toCartItemDomainModel, given CartItemEntity input, when mapping to domain model, then should return expected CartItemDomainModel`() {
        // When
        val result = inputCartItemEntity.toCartItemDomainModel()

        // Then
        assertEquals(expectedCartItemDomainModel, result)
    }

    /**
     * Test Case: toCartItemEntity Mapping
     * Given: A CartItemDomainModel input data.
     * When: Converting the input to CartItemEntity using `toCartItemEntity()`.
     * Then: It should correctly map and return the expected CartItemEntity.
     */
    @Test
    fun `toCartItemEntity, given CartItemDomainModel input, when mapping to entity, then should return expected CartItemEntity`() {
        // When
        val result = expectedCartItemDomainModel.toCartItemEntity()

        // Then
        assertEquals(inputCartItemEntity, result)
    }
}

