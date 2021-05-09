package com.taufik.livedatawithretrofit.model


import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("restaurant")
    val restaurant: Restaurant
)