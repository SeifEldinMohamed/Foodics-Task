package com.example.foodicstask.data.repository

import com.example.foodicstask.data.data_sources.local.FoodLocalDataSource
import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import com.example.foodicstask.data.fake_data.fakeCategoryEntityList
import com.example.foodicstask.data.fake_data.fakeCategoryListDataModel
import com.example.foodicstask.data.fake_data.fakeFoodItemEntityList
import com.example.foodicstask.data.fake_data.fakeFoodItemListDataModel
import com.example.foodicstask.data.mapper.category.toCategoryDomainModel
import com.example.foodicstask.data.mapper.food.toFoodItemDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomDatabaseExceptionDomainModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FoodRepositoryImplTest {

    private lateinit var foodRemoteDataSource: FoodRemoteDataSource
    private lateinit var foodLocalDataSource: FoodLocalDataSource
    private lateinit var foodRepository: FoodRepositoryImpl

    @Before
    fun setup() {
        foodRemoteDataSource = mockk()
        foodLocalDataSource = mockk()
        foodRepository = FoodRepositoryImpl(foodRemoteDataSource, foodLocalDataSource)
    }

    /**
     * Test Case: Fetch Food List - Success
     * Given: Remote data source returns food items and local data source returns cached items.
     * When: `fetchFoodList` is called.
     * Then: It should return the list of food items with the correct mapping.
     */
    @Test
    fun `fetchFoodList, when called and data already cached, then should return food list from local`() =
        runTest {
            // Given
            val cachedFoodItems = fakeFoodItemEntityList
            val expected = cachedFoodItems.map { it.toFoodItemDomainModel() }

            coEvery { foodLocalDataSource.getAllFoodItems() } returns cachedFoodItems

            // When
            val result = foodRepository.fetchFoodList()

            // Then
            assertEquals(expected, result)
            coVerify(exactly = 0) { foodRemoteDataSource.fetchFoodList() }
            coVerify(exactly = 0) { foodLocalDataSource.insertFoodItems(any()) }
        }

    /**
     * Test Case: Fetch FoodList - Success
     * Given: Remote data source returns foods then should fetch food list from remote then cache it .
     * When: `fetchFoodList` is called.
     * Then: It should return the  cached data with the correct mapping.
     */
    @Test
    fun `fetchFoodList, when called and no data cached, then should fetch food list from remote then cache it and return cached data`() =
        runTest {
            // Given
            // val categoriesFromApi = fakeCategoryListDataModel
            val foodItemListFromApi = fakeFoodItemListDataModel

            coEvery { foodLocalDataSource.getAllFoodItems() } returns emptyList()
            coEvery { foodRemoteDataSource.fetchFoodList() } returns foodItemListFromApi
            coEvery { foodLocalDataSource.insertFoodItems(any()) } just Runs

            // When
            foodRepository.fetchFoodList()

            // Then
            coVerify(exactly = 2) { foodLocalDataSource.getAllFoodItems() }
            coVerify(exactly = 1) { foodRemoteDataSource.fetchFoodList() }
            coVerify(exactly = 1) { foodLocalDataSource.insertFoodItems(any()) }
        }

    /**
     * Test Case: Fetch Category List - Success
     * Given: Remote data source returns categories and local data source returns cached categories.
     * When: `fetchCategoryList` is called.
     * Then: It should return the list of categories with the correct mapping.
     */
    @Test
    fun `fetchCategoryList, when called and no data cached, then should fetch food list from remote then cache it and return cached data`() =
        runTest {
            // Given
            val categoriesFromApi = fakeCategoryListDataModel

            coEvery { foodLocalDataSource.getAllCategories() } returns emptyList()
            coEvery { foodRemoteDataSource.fetchCategoryList() } returns categoriesFromApi
            coEvery { foodLocalDataSource.insertCategories(any()) } just Runs

            // When
            foodRepository.fetchCategoryList()

            // Then
            coVerify(exactly = 2) { foodLocalDataSource.getAllCategories() }
            coVerify(exactly = 1) { foodRemoteDataSource.fetchCategoryList() }
            coVerify(exactly = 1) { foodLocalDataSource.insertCategories(any()) }
        }

    /**
     * Test Case: Fetch Category List - Success
     * Given: call fetchCategoryList and data already cached.
     * When: `fetchCategoryList` is called.
     * Then: It  should return category list from local with the correct mapping.
     */
    @Test
    fun `fetchCategoryList, when called and data already cached, then should return category list from local`() =
        runTest {
            // Given
            val cachedCategoryItems = fakeCategoryEntityList
            val expected = cachedCategoryItems.map { it.toCategoryDomainModel() }

            coEvery { foodLocalDataSource.getAllCategories() } returns cachedCategoryItems

            // When
            val result = foodRepository.fetchCategoryList()

            // Then
            assertEquals(expected, result)
            coVerify(exactly = 0) { foodRemoteDataSource.fetchCategoryList() }
            coVerify(exactly = 0) { foodLocalDataSource.insertCategories(any()) }
        }

    /**
     * Test Case: Search Food List by Name - Success
     * Given: A query and the local data source returns filtered food items.
     * When: `searchFoodListByName` is called.
     * Then: It should return the filtered list of food items with the correct mapping.
     */
    @Test
    fun `searchFoodListByName, given query, when called, then should return filtered food list`() =
        runTest {
            // Given
            val query = "Pizza"
            val searchedFoodList = fakeFoodItemEntityList
            val expected = searchedFoodList.map { it.toFoodItemDomainModel() }

            coEvery { foodLocalDataSource.searchFoodsByName(query) } returns searchedFoodList

            // When
            val result = foodRepository.searchFoodListByName(query)

            // Then
            assertEquals(expected, result)
            coVerify(exactly = 1) { foodLocalDataSource.searchFoodsByName(query) }
        }

    /**
     * Test Case: Search Food List by Name - Exception
     * Given: The local data source throws an exception.
     * When: `searchFoodListByName` is called.
     * Then: It should propagate the exception.
     */
    @Test(expected = CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel::class)
    fun `searchFoodListByName, when local source throws exception, then should propagate exception`() =
        runTest {
            // Given
            val query = "Pizza"
            val exception = CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel

            coEvery { foodLocalDataSource.searchFoodsByName(query) } throws exception

            // When
            foodRepository.searchFoodListByName(query)
        }
}
