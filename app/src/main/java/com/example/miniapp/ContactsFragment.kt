package com.example.miniapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.frag_contacts, container, false)

    }
}

