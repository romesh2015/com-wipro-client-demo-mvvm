package com.wipro.assignment.mvvm.about.model
import com.google.gson.annotations.SerializedName
// This is data class is used store the data in objects.
data class AboutApiResponse(
    @SerializedName("title") val title: String,
    @SerializedName("rows") val rows: List<AboutList>)
data class AboutList(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageHref") val imageHref: String?
)
