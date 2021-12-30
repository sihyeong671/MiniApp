package com.example.miniapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Photo(val img:Int, val name: String)

class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v) {}

class CustomAdapter(val photoList: ArrayList<Photo>) : RecyclerView.Adapter<PhotoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return photoList.size
    }

}