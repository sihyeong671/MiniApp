package com.example.miniapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Photo(val img:Int)

class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v) {

    var photoImg : ImageView

    init {
        photoImg = v.findViewById(R.id.imageView)
    }


}

class PhotoAdapter(private val photoList: ArrayList<Uri>, private val clickListener: ClickListener, val context: Context) : RecyclerView.Adapter<PhotoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = photoList[position]
//        Glide.with(context).load(item).override(100, 100).into(holder.photoImg)
        Glide.with(context).load(item).into(holder.photoImg)


        holder.photoImg.setBackgroundResource(R.drawable.round_img)
        holder.photoImg.clipToOutline = true

        holder.itemView.setOnClickListener{
            clickListener.onItemClick(photoList[position])
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    interface ClickListener {
        fun onItemClick(photoModel: Uri)
    }

}