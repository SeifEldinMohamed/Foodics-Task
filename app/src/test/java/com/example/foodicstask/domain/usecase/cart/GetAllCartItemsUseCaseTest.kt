package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.fake_data.fakeCartItemDomainModelList
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.repository.CartRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllCartItemsUseCaseTest {

    private lateinit var cartRepository: CartRepository
    private lateinit var getAllCartItemsUseCase: GetAllCartItemsUseCase

    @Before
    fun setup() {
        cartRepository = mockk()
        getAllCartItemsUseCase = GetAllCartItemsUseCase(cartRepository)
    }

    /**
     * Test Case: Success Scenario
     * Given: `getAllCartItems()` is called from the repository.
     * When: The repository returns a flow of cart items domain model.
     * Then: The use case should return the same flow of cart items domain model from the repository.
     */
    @Test
    fun `given repository call, when getting all cart items, then should return list of cart items`() = runTest {
        // Given
        val expected = fakeCartItemDomainModelList
        every { cartRepository.getAllCartItems() } returns flowOf(expected)

        // When
        val result = getAllCartItemsUseCase()

        // Then
        assertEquals(expected, result.first())
    }

    /**
     * Test Case: Exception Scenario
     * Given: `getAllCartItems()` is called from the repository.
     * When: The repository throws a `NetworkExceptionDomainModel`.
     * Then: The use case should propagate the same `NetworkExceptionDomainModel`.
     */
    @Test
    fun `given repository throws exception, when getting all cart items, then should propagate exception`() = runTest {
        // Given
        val exception = CustomApiExceptionDomainModel.NetworkExceptionDomainModel
        every { cartRepository.getAllCartItems() } returns flow { throw exception }

        // When
        try {
            getAllCartItemsUseCase()
        } catch (e: Exception) {
            val actualException = e as CustomApiExceptionDomainModel.NetworkExceptionDomainModel
            // Then
            assertEquals(exception, actualException)
        }
    }
}
