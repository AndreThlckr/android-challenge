package com.zygotecnologia.zygotv.tmdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HighlightedShowItemBinding
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class HighlightedShowAdapter : RecyclerView.Adapter<HighlightedShowAdapter.ViewHolder>() {

    private var highlightedShows: List<Show> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HighlightedShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(highlightedShows[position])
    }

    override fun getItemCount() = highlightedShows.size

    fun updateHighlightedShow(show: Show) {
        val firstPosition = 0
        val wasEmpty = highlightedShows.isEmpty()

        highlightedShows = listOf(show)

        if (wasEmpty) notifyItemInserted(firstPosition)
        else notifyItemChanged(firstPosition)
    }

    class ViewHolder(
        private val binding: HighlightedShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(show: Show) {
            binding.showTitle.text = show.name

            val imageUrl = show.backdropPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            Glide.with(itemView)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_placeholder)
                        .transform(MultiTransformation(CenterCrop(), RoundedCorners(16)))
                )
                .into(binding.showBackDrop)
        }
    }
}