package com.example.miniapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PhotoFragment : Fragment(){


    companion object{
        const val TAG : String = "로그"

        fun newInstance(): PhotoFragment{
            return PhotoFragment()
        }
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "PhotoFragment - onCreate called")

    }


    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "PhotoFragment - onAttach() called")
    }


    //뷰 생성
    // fragment와 레이아웃 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "PhotoFragment - onCreateView() called")

        return inflater.inflate(R.layout.fragment_photo, container, false)
    }
}