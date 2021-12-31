package com.example.miniapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Photo(val img:Int, val name: String)

class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    var photoName: TextView
    var photoImg : ImageView

    init {
        photoName = v.findViewById(R.id.textView)
        photoImg = v.findViewById(R.id.imageView)
    }


}

class PhotoAdapter(val photoList: List<Photo>, val clickListenner: ClickListener) : RecyclerView.Adapter<PhotoViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.photoName.text = photoList[position].name
        holder.photoImg.setImageResource(photoList[position].img)

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