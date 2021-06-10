package com.manish.sahu.ui.adapters

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.manish.sahu.databinding.ImageListItemBinding
import com.manish.sahu.model.Photos
import com.manish.sahu.ui.home.DetailsActivity
import com.manish.sahu.utils.startNewActivityClear


/**
 * Created by Manish Sahu on 09-Jun-21.
 * Email: sahum652@gmail.com
 */

class PhotosAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var setPhotoList1 = mutableListOf<Photos>()


    fun setPhotoList(setPhotoList1: List<Photos>) {
        this.setPhotoList1 = setPhotoList1.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ImageListItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val photos = setPhotoList1[position]
        holder.binding.title.text = photos.title

        Glide.with(holder.itemView.context).load(photos.thumbnailUrl).into(holder.binding.imageview)
        /*val url = GlideUrl(
            photos.url, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(holder.itemView.context))
                .build()
        )
        Glide.with(holder.itemView.context)
            .load(url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    isFirstResource: Boolean

                ): Boolean {
                    Log.d("AAAAAAAAAAAA", "onLoadFailed: "+e.toString())
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("AAAAAAAAAAAA", "onLoadFailed: ")
                    return true
                }
            })
            .into(holder.binding.imageview)*/

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra("id", photos.id)
            intent.putExtra("album_id", photos.albumId)
            intent.putExtra("title", photos.title)
            intent.putExtra("url", photos.url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return setPhotoList1.size
    }
}

class MainViewHolder(val binding: ImageListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}