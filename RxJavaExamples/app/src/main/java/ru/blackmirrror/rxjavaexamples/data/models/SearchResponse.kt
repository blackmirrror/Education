package ru.blackmirrror.rxjavaexamples.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse (

    @SerializedName("keyword"                ) var keyword                : String?    = null,
    @SerializedName("pagesCount"             ) var pagesCount             : Int?       = null,
    @SerializedName("searchFilmsCountResult" ) var searchFilmsCountResult : Int?       = null,
    @SerializedName("films"                  ) var films                  : List<FilmDTO> = arrayListOf()

)