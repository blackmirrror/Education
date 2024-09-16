package ru.blackmirrror.rxjavaexamples.features.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.blackmirrror.rxjavaexamples.R
import ru.blackmirrror.rxjavaexamples.domain.models.Film

class MoviesAdapter :
    ListAdapter<Film, MoviesAdapter.MoviesViewHolder>(MovieItemCallback()) {

    var onMovieItemClickListener: ((Int) -> Unit)? = null

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        val image = itemView.findViewById<ImageView>(R.id.iv_item_image)
        val title = itemView.findViewById<TextView>(R.id.tv_item_title)
        val genreAndYear = itemView.findViewById<TextView>(R.id.tv_item_genre_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder) {
            title.text = movie.nameRu
            genreAndYear.text = "${movie.genres.first()}, ${movie.year.toString()}"

            item.setOnClickListener {
                onMovieItemClickListener?.invoke(position)
            }
            try {
                Glide.with(holder.image)
                    .asBitmap()
                    .load(movie.posterUrlPreview)
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.image)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}