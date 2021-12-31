package com.example.miniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PhotoFragment : Fragment(), PhotoAdapter.ClickListener {


    private lateinit var adapter: PhotoAdapter
    private val photoList: ArrayList<Photo> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate((R.layout.fragment_photo), container, false)
        buildDisplayPhoto()
        initRecycleView(view)
        return view
    }

    private fun initRecycleView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.photoView)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        adapter = PhotoAdapter(photoList, this)
        recyclerView.adapter = adapter

    }


    private  fun buildDisplayPhoto() {
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
            photoList.add(Photo(R.drawable.cat, "고양이"))
    }

    override fun onItemClick(photoModel: Photo) {
        Toast.makeText(activity, "사진", Toast.LENGTH_SHORT ).show()
    }


}