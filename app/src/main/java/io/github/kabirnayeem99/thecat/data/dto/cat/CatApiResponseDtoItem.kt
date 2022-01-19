package io.github.kabirnayeem99.thecat.data.dto.cat


import com.google.gson.annotations.SerializedName

data class CatApiResponseDtoItem(
    @SerializedName("breeds")
    var breeds: List<Any>,
    @SerializedName("height")
    var height: Int, // 230
    @SerializedName("id")
    var id: String, // Eoopj_bsQ
    @SerializedName("url")
    var url: String, // https://cdn2.thecatapi.com/images/Eoopj_bsQ.jpg
    @SerializedName("width")
    var width: Int // 220
)