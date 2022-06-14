package com.renlipu.photoalbum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.renlipu.photoalbum.databinding.ItemAlbumBinding
import com.renlipu.photoalbum.model.AlbumWithUserName

/**
 * @program: Photo Album
 *
 * @description: Adapter for album.
 *
 * @author: Lipu
 *
 * @create: 2022-06-10 19:31
 **/
class AlbumAdapter(private val clickListener: (Int) -> Unit) :
    ListAdapter<AlbumWithUserName, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.binding.apply {
            tvAlbumName.text = album.title
            tvAuthor.text = album.userName
            root.setOnClickListener {
                clickListener(album.id)
            }
        }
    }

    /**
     * ViewHolder for album items.
     */
    class AlbumViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

}

object AlbumDiffCallback : DiffUtil.ItemCallback<AlbumWithUserName>() {
    override fun areItemsTheSame(oldItem: AlbumWithUserName, newItem: AlbumWithUserName): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AlbumWithUserName,
        newItem: AlbumWithUserName
    ): Boolean {
        return oldItem == newItem
    }

}