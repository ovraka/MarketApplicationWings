package com.assignment.common.response.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("productCode")
    val productCode: String,
    @SerializedName("unit")
    val unit: String
): Parcelable