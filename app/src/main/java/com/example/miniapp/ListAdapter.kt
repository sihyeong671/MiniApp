package com.example.miniapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.ContactsDataListBinding


class ListAdapter (private var list: MutableList<TestData>): RecyclerView.Adapter<ListAdapter.ListItemViewHolder> () {

// onBindViewHolder의 역할을 대신한다, View와 데이터를 연결시키는 함수
    inner class ListItemViewHolder(private val binding: ContactsDataListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TestData, position: Int) {
            Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====") //로그 출력
            Log.d("ListAdapter", data.getData1()+" "+data.getData2()+" "+data.getData3())
            binding.data1Text.text = data.getData1()
            binding.data2Text.text = data.getData2()
            binding.data3Text.text = data.getData3()
        }
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성 -> binding
    // ViewHolder에 쓰일 Layout을 inflate하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {

        val binding = ContactsDataListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
        //size를 측정.
    }

    // ViewHolder의 bind 메소드를 호출한다. - 데이터 묶는 함수가 실행.
    override fun onBindViewHolder(holder: ListAdapter.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====") // log를 남긴다.
        holder.bind(list[position], position)
        //ViewHolder의 bind method로 데이터를 넘긴다. 몇 번째 셀에 어떤 데이터를 넣을 지 관리한다.
    }


}
