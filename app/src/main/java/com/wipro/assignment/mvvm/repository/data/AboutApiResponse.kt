package com.wipro.assignment.mvvm.repository.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class AboutApiResponse(
    @SerializedName("title") val title: String,
    @SerializedName("rows") var rows: List<AboutList>)

@Entity(tableName = "AboutList")
data class AboutList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageHref") val imageHref: String?
)
