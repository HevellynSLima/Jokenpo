package com.example.jokenpo

import android.content.Context
import android.os.Bundle
import android.util.Log
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

    override fun onResume() {
        super.onResume()

        Log.d("lifeCycle", "onResume")

    }

    override fun onStop() {
        super.onStop()

        Log.d("lifeCycle", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("lifeCycle", "onDestroy")


    }

    override fun onDetach() {
        super.onDetach()

        Log.d("lifeCycle", "onDetach")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("lifeCycle", "onAttach")
    }


}