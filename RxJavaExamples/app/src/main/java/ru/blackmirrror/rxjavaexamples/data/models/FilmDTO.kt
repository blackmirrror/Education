package ru.blackmirrror.rxjavaexamples.data.models

import com.google.gson.annotations.SerializedName

data class FilmDTO (

    @SerializedName("filmId"           ) var filmId           : Int,
    @SerializedName("nameRu"           ) var nameRu           : String?       = null,
    @SerializedName("nameEn"           ) var nameEn           : String?       = null,
    @SerializedName("type"             ) var type             : String?       = null,
    @SerializedName("year"             ) var year             : String?       = null,
    @SerializedName("description"      ) var description      : String?       = null,
    @SerializedName("filmLength"       ) var filmLength       : String?       = null,
    @SerializedName("countries"        ) var countries        : List<CountryDTO> = arrayListOf(),
    @SerializedName("genres"           ) var genres           : List<GenreDTO>   = arrayListOf(),
    @SerializedName("rating"           ) var rating           : String?       = null,
    @SerializedName("ratingVoteCount"  ) var ratingVoteCount  : Int?          = null,
    @SerializedName("posterUrl"        ) var posterUrl        : String?       = null,
    @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?       = null

)