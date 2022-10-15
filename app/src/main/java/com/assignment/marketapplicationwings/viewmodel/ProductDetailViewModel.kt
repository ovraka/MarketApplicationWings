package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.repository.CartRepository
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.response.product.Content
import com.assignment.common.table.CartTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    application: Application,
    val cartRepository: CartRepository,
    val dataStorePreference: DataStorePreference
) :
    BaseViewModel(application) {

    val userData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            userData.postValue(dataStorePreference.getUserString())
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
}