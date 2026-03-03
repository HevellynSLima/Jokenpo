package com.example.jokenpo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jokenpo.databinding.FragmentJogadasBinding
import com.google.android.material.snackbar.Snackbar

class JogadasFragment : Fragment() {

    private lateinit var binding: FragmentJogadasBinding
    private lateinit var listener: JogadorListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as JogadorListener   // ❤️ Activity recebe a jogada
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentJogadasBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar2)

        setupToolbar(activity)
        setupDrawer()
        setupBottomNavigation()
        setupSpinner(savedInstanceState)

        return binding.root
    }

    private fun setupSpinner(savedInstanceState: Bundle?) {
        val opcoes = arrayOf("Pedra", "Papel", "Tesoura")

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            opcoes
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter


        val jogadaSalva = savedInstanceState?.getString("jogada")
        if (jogadaSalva != null) {
            val index = opcoes.indexOf(jogadaSalva)
            if (index >= 0) {
                binding.spinner.setSelection(index)
                listener.onPlaySelected(jogadaSalva)   // 💥 NECESSÁRIO
            }
        }


        var firstFire = true
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                if (firstFire) { firstFire = false; return }

                val jogada = opcoes[position]

                // >>> ENVIA PARA A ACTIVITY <<<
                listener.onPlaySelected(jogada)

                Snackbar.make(binding.root, "Você escolheu: $jogada", Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val jogadaAtual = binding.spinner.selectedItem.toString()
        outState.putString("jogada", jogadaAtual)
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.resultadoFragment2 -> {
                    // sem args → resultado pega da Activity
                    findNavController().navigate(R.id.resultadoFragment2)
                    true
                }
                R.id.jogadasFragment2 -> true
                else -> false
            }
        }
    }

    private fun setupDrawer() {
        binding.toolbar2.setNavigationOnClickListener {
            binding.root.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener { item ->
            binding.root.closeDrawers()
            when (item.itemId) {

                R.id.inicio -> {
                    findNavController().popBackStack()
                    true
                }

                R.id.resultadoFragment2 -> {
                    findNavController().navigate(R.id.resultadoFragment2)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupToolbar(activity: AppCompatActivity) {
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_)
        }
    }
}