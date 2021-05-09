package com.taufik.livedatawithretrofit.model

import com.google.gson.annotations.SerializedName

data class PostReviewResponse (
    @SerializedName("customerReviews")
    val customerReviews: List<CustomerReview>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)