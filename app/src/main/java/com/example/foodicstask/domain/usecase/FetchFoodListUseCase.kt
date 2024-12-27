package com.example.foodicstask.domain.usecase

import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class FetchFoodListUseCase(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(): List<FoodItemDomainModel> {
        return foodRepository.fetchFoodList()
    }
}