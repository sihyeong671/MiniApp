package com.example.miniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class PhotoFragment : Fragment() {


    val photoList = arrayListOf(
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이"),
        Photo(R.drawable.cat, "고양이")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.activity_photo, container, false)
        val a = rootView.findViewById<View>(R.id.recyclerView)
        val photoView = CustomAdapter(photoList)

        return rootView
    }


}