package ru.blackmirrror.rxjavaexamples.domain.models

import ru.blackmirrror.rxjavaexamples.data.models.CountryDTO
import ru.blackmirrror.rxjavaexamples.data.models.DiscountCardDTO
import ru.blackmirrror.rxjavaexamples.data.models.FilmDTO
import ru.blackmirrror.rxjavaexamples.data.models.GenreDTO

fun FilmDTO.toFilm(): Film {
    return Film(
        filmId,
        nameRu,
        nameEn,
        year?.toInt(),
        countries.map { it.toCountry() },
        genres.map { it.toGenre() },
        posterUrl,
        posterUrlPreview
    )
}

fun CountryDTO.toCountry(): String = country

fun GenreDTO.toGenre(): String = genre

fun DiscountCardDTO.toDiscountCard(): DiscountCard {
    return DiscountCard(
        id,
        shop
    )
}