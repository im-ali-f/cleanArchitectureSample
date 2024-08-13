package com.loc.androidarchitecturesample.store.presentation.products_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.androidarchitecturesample.store.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            Log.d("TAG", "getProducts: started")
            _state.update { it.copy(isLoading = true) }
            productsRepository.getProducts()
                .onRight { products ->


                    Log.d("TAG", "getProducts: pVM called")
                    _state.update {
                        it.copy(products = products.body()!!)
                    }
                }.onLeft { error ->
                    Log.d("TAG", "getProducts: pVM called but failed")
                    _state.update {
                        it.copy(
                            error = error.error.message
                        )
                    }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}