package com.renlipu.photoalbum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.renlipu.photoalbum.R
import com.renlipu.photoalbum.databinding.ItemPhotoBinding
import com.renlipu.photoalbum.model.Photo
import com.squareup.picasso.Picasso

/**
 * @program: Photo Photo
 *
 * @description: Adapter for photo.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:31
 **/
class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PhotoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        Picasso.get()
            .load(photo.url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivPhoto)
    }

    /**
     * ViewHolder for photo items.
     */
    class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

}

object PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}