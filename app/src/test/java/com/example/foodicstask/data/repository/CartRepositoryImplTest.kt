package com.example.foodicstask.data.repository

import com.example.foodicstask.data.data_sources.local.CartLocalDataSource
import com.example.foodicstask.data.fake_data.fakeCartItemEntityList
import com.example.foodicstask.data.mapper.cart.toCartItemDomainModel
import com.example.foodicstask.data.mapper.cart.toCartItemEntity
import com.example.foodicstask.domain.fake_data.fakeCartItemDomainModelList
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CartRepositoryImplTest {

    private lateinit var cartLocalDataSource: CartLocalDataSource
    private lateinit var cartRepository: CartRepositoryImpl

    @Before
    fun setup() {
        cartLocalDataSource = mockk()
        cartRepository = CartRepositoryImpl(cartLocalDataSource)
    }

    /**
     * Test Case: Get All Cart Items - Success
     * Given: Local data source returns a list of cart items.
     * When: `getAllCartItems` is called.
     * Then: It should return a list of CartItemDomainModels.
     */
    @Test
    fun `getAllCartItems, when called, then should return cart items list`() = runTest {
        // Given
        val cartItemEntityList = fakeCartItemEntityList
        val cartItemDomainModelList = cartItemEntityList.map { it.toCartItemDomainModel() }

        every { cartLocalDataSource.getAllCartItems() } returns flowOf(cartItemEntityList)

        // When
        val result = cartRepository.getAllCartItems().first()

        // Then
        assertEquals(cartItemDomainModelList.size, result.size)
        assertEquals(cartItemDomainModelList, result)
        coVerify(exactly = 1) { cartLocalDataSource.getAllCartItems() }
    }

    /**
     * Test Case: Insert or Update Cart Item - Success
     * Given: A cart item domain model.
     * When: `insertOrUpdateCartItem` is called.
     * Then: It should call insertOrUpdateCartItem on local data source.
     */
    @Test
    fun `insertOrUpdateCartItem, when called, then should insert or update cart item in local data source`() = runTest {
        // Given
        val cartItemDomainModel = fakeCartItemDomainModelList.first()

        coEvery { cartLocalDataSource.insertOrUpdateCartItem(any()) } just Runs

        // When
        cartRepository.insertOrUpdateCartItem(cartItemDomainModel)

        // Then
        coVerify(exactly = 1) { cartLocalDataSource.insertOrUpdateCartItem(cartItemDomainModel.toCartItemEntity()) }
    }

    /**
     * Test Case: Clear Cart - Success
     * Given: `clearCart` is called.
     * When: `clearCart` is called.
     * Then: It should call `clearCart` on local data source.
     */
    @Test
    fun `clearCart, when called, then should clear cart in local data source`() = runTest {
        // Given
        coEvery { cartLocalDataSource.clearCart() } just Runs

        // When
        cartRepository.clearCart()

        // Then
        coVerify(exactly = 1) { cartLocalDataSource.clearCart() }
    }
}
