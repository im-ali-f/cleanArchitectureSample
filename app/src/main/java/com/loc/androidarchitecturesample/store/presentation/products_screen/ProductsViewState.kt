package com.loc.androidarchitecturesample.store.presentation.products_screen

import com.loc.androidarchitecturesample.store.domain.model.Product
import retrofit2.Response

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)