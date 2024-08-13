package com.loc.androidarchitecturesample.store.data.repository

import arrow.core.Either
import com.loc.androidarchitecturesample.store.data.maper.toGeneralError
import com.loc.androidarchitecturesample.store.data.remote.ProductsApi
import com.loc.androidarchitecturesample.store.domain.model.NetworkError
import com.loc.androidarchitecturesample.store.domain.model.Product
import com.loc.androidarchitecturesample.store.domain.repository.ProductsRepository
import retrofit2.Response
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsRepository {
    override suspend fun getProducts(): Either<NetworkError, Response<List<Product>>> {
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toGeneralError() }
    }
}