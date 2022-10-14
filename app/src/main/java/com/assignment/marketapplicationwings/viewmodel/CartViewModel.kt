package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.repository.CartRepository
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.table.CartTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    application: Application,
    val dataStorePreference: DataStorePreference,
    val cartRepository: CartRepository
) : BaseViewModel(application) {
    var cartState: LiveData<List<CartTable>>? = null

    init {
        getCart()
    }

    fun getCart() {
        viewModelScope.launch {
            dataStorePreference.getUsername().collect {
                cartState = cartRepository.getAllCart(it)
            }
        }
    }
}