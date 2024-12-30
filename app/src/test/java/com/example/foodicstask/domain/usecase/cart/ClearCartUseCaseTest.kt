package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.repository.CartRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Before
import io.mockk.just


class ClearCartUseCaseTest {

    private lateinit var cartRepository: CartRepository
    private lateinit var clearCartUseCase: ClearCartUseCase

    @Before
    fun setup() {
        cartRepository = mockk()
        clearCartUseCase = ClearCartUseCase(cartRepository)
    }

    /**
     * Test Case: Success Scenario
     * Given: `clearCart()` is called from the repository.
     * When: The use case invokes the method.
     * Then: The repository should clear all items without any errors.
     */
    @Test
    fun `when clearing cart, then repository should clear all items`() = runTest {
        coEvery { cartRepository.clearCart() } just Runs
        // When
        clearCartUseCase()

        // Then
        coVerify { cartRepository.clearCart() }
    }

    /**
     * Test Case: Exception Scenario
     * Given: `clearCart()` is called from the repository.
     * When: The repository throws a `NetworkExceptionDomainModel`.
     * Then: The use case should propagate the same `NetworkExceptionDomainModel`.
     */
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `given repository throws exception, when clearing cart, then should propagate exception`() = runTest {
        // Given
        coEvery { cartRepository.clearCart() } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

        // When
        clearCartUseCase()
    }
}
