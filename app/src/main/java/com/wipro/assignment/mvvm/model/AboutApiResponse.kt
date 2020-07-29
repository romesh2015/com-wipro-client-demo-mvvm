package com.wipro.assignment.mvvm.model
import com.google.gson.annotations.SerializedName
data class AboutApiResponse(
    @SerializedName("title") val title: String,
    @SerializedName("rows") var rows: List<AboutList>)
data class AboutList(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageHref") val imageHref: String?
)
