package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.repository.CartRepository
import com.assignment.api_service.use_case.ProductUseCase
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.response.product.Content
import com.assignment.common.response.product.ProductResponse
import com.assignment.common.table.CartTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val productUseCase: ProductUseCase,
    private val dataStorePreference: DataStorePreference,
    private val cartRepository: CartRepository
) : BaseViewModel(application) {
    val productData = MutableLiveData<AppResponse<ProductResponse>>()
    val tokenData = MutableLiveData<String>()
    val userData = MutableLiveData<String>()

    init {
        getTokenData()
    }

    fun getTokenData() {
        viewModelScope.launch {
            tokenData.postValue(dataStorePreference.getTokenString())
            userData.postValue(dataStorePreference.getUserString())
        }
    }
    fun getAllProduct(token: String) {
        if (productData.value == null) {
            viewModelScope.launch {
                productUseCase.invoke(token).collect {
                    productData.postValue(it)
                }
            }
        } else {
            productData.postValue(productData.value)
        }
    }

    fun insertIntoCart(user: String, content: Content) {
        cartRepository.insertCart(
            CartTable(
                id = 0,
                productCode = content.productCode,
                name = content.name,
                price = content.price,
                currency = content.currency,
                discount = content.discount,
                dimension = content.dimension,
                unit = content.unit,
                user = user,
                quantity = 1
            )
        )
    }

//    fun fetchProduct(id: Int, username: String) {
//        insertCart = cartRepository.isProductOnCart(id, username)
//    }

//    fun insertToCart(username: String, content: Content) {
//        viewModelScope.launch {
//            cartRepository.insertProduct(
//                CartTable(
//                    content.productCode,
//                    content.name,
//                    content.price,
//                    content.currency,
//                    content.discount,
//                    content.dimension,
//                    content.unit,
//                    username,
//                    "Pending"
//                )
//            )
//        }
//    }
}