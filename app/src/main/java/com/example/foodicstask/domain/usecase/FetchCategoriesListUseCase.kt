package com.example.foodicstask.domain.usecase

import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class FetchCategoriesListUseCase(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(): List<CategoryDomainModel> {
        return foodRepository.fetchCategoryList()
    }
}