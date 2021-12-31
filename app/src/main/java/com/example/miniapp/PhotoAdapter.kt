package com.example.miniapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class Photo(val img:Int)

class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v) {

    var photoImg : ImageView

    init {
        photoImg = v.findViewById(R.id.imageView)
    }


}

class PhotoAdapter(private val photoList: List<Photo>, private val clickListenner: ClickListener) : RecyclerView.Adapter<PhotoViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        holder.photoImg.setImageResource(photoList[position].img)


        holder.photoImg.setBackgroundResource(R.drawable.round_img)
        holder.photoImg.clipToOutline = true
        holder.itemView.setOnClickListener{
            clickListenner.onItemClick(photoList[position])
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    interface ClickListener {
        fun onItemClick(photoModel: Photo)
    }

}