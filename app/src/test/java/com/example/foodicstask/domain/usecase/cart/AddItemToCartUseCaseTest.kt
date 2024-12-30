package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.fake_data.fakeCartItemDomainModelList
import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import com.example.foodicstask.domain.repository.CartRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Before

class AddItemToCartUseCaseTest {

    private lateinit var cartRepository: CartRepository
    private lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @Before
    fun setup() {
        cartRepository = mockk()
        addItemToCartUseCase = AddItemToCartUseCase(cartRepository)
    }

    /**
     * Test Case: Success Scenario
     * Given: `insertOrUpdateCartItem()` is called from the repository.
     * When: A cart item is passed to the use case.
     * Then: The repository should insert or update the cart item without any errors.
     */
    @Test
    fun `given cart item, when adding to cart, then repository should insert or update cart item`() = runTest {
        // Given
        val cartItem = fakeCartItemDomainModelList.first()
        coEvery { cartRepository.insertOrUpdateCartItem(cartItem) } just Runs

        // When
        addItemToCartUseCase(cartItem)

        // Then
        coVerify { cartRepository.insertOrUpdateCartItem(cartItem) }
    }

    /**
     * Test Case: Exception Scenario
     * Given: `insertOrUpdateCartItem()` is called from the repository.
     * When: The repository throws a `DatabaseFullExceptionDomainModel`.
     * Then: The use case should propagate the same `DatabaseFullExceptionDomainModel`.
     */
    @Test(expected = CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel::class)
    fun `given repository throws exception, when adding to cart, then should propagate exception`() = runTest {
        // Given
        val cartItem = fakeCartItemDomainModelList.first()
        coEvery { cartRepository.insertOrUpdateCartItem(cartItem) } throws CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel

        // When
        addItemToCartUseCase(cartItem)
    }
}
