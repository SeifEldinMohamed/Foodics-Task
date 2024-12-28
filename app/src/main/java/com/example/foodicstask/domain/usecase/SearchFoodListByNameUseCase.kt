package com.example.foodicstask.domain.usecase

import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class SearchFoodListByNameUseCase(
    private val foodRepository: FoodRepository,
) {
    suspend operator fun invoke(name:String): List<FoodItemDomainModel> {
        return foodRepository.searchFoodListByName(name)
    }
}