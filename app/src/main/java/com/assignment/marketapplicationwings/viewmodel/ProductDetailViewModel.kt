package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.response.product.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {
    val productData = arrayListOf<Content>()
}