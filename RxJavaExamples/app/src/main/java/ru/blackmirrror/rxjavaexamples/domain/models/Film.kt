package ru.blackmirrror.rxjavaexamples.domain.models

data class Film (

    var filmId           : Int,
    var nameRu           : String?      = null,
    var nameEn           : String?      = null,
    var year             : Int?         = null,
    var countries        : List<String> = arrayListOf(),
    var genres           : List<String> = arrayListOf(),
    var posterUrl        : String?      = null,
    var posterUrlPreview : String?      = null

)