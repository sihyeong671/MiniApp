package com.example.miniapp

import android.content.Intent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.miniapp.databinding.DialogGetfishBinding
import java.util.*


class FishingDialog : DialogFragment() {
    private var _binding: DialogGetfishBinding? = null
    private val binding get() = _binding!!

    var FishList = listOf(R.drawable.fish, R.drawable.fish2, R.drawable.fish3, R.drawable.fish4) //물고기 이미지 리스트

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogGetfishBinding.inflate(inflater, container, false)

        val view = binding.root

        val random = Random()
        val num = random.nextInt(4) //랜덤 물고기

        binding.fish.setImageResource(FishList.get(num)) //랜덤 물고기를 넣어준다.

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialBtnRe.setOnClickListener {
            dismiss()
        }
        binding.dialBtnOut.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

