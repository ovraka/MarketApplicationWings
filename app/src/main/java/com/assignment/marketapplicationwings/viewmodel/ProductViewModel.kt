package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
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
    var insertCart: LiveData<Boolean>? = null

    init {
        viewModelScope.launch {
            dataStorePreference.getToken().collect{
                tokenData.value = it
            }
        }
    }

    fun getAllProduct(token: String) {
        viewModelScope.launch {
            productUseCase.invoke(token).collect {
                productData.postValue(it)
            }
        }
    }

    fun fetchProduct(id: Int, username: String) {
        insertCart = cartRepository.isProductOnCart(id, username)
    }

    fun insertToCart(username: String, content: Content) {
        viewModelScope.launch {
            cartRepository.insertProduct(
                CartTable(
                    content.productCode,
                    content.name,
                    content.price,
                    content.currency,
                    content.discount,
                    content.dimension,
                    content.unit,
                    username,
                    "Pending"
                )
            )
        }
    }
}