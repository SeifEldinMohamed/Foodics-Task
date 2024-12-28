package com.example.foodicstask.domain.usecase

import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class FilteredFoodListByCategoryUseCase(
    private val foodRepository: FoodRepository,
) {
    suspend operator fun invoke(selectedCategoryId: Int): List<FoodItemDomainModel> {
        return foodRepository.filteredFoodListByCategory(selectedCategoryId)
    }
}