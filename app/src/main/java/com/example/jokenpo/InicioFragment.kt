package com.example.jokenpo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jokenpo.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentInicioBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button.setOnClickListener {
            val action = InicioFragmentDirections.actionInicioFragmentToJogo()
            findNavController().navigate(action)
        }

        return binding.root
    }


}