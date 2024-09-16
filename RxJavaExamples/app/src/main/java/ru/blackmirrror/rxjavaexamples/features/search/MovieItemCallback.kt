package ru.blackmirrror.rxjavaexamples.features.search

import androidx.recyclerview.widget.DiffUtil
import ru.blackmirrror.rxjavaexamples.domain.models.Film

class MovieItemCallback: DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(
        oldItem: Film,
        newItem: Film
    ): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(
        oldItem: Film,
        newItem: Film
    ): Boolean {
        return oldItem == newItem
    }

}