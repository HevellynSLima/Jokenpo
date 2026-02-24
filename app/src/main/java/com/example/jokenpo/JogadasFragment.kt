package com.example.jokenpo

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentJogadasBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar2)

        setupToolBar(activity)
        setupDrawer()
        setupBottomNavegation()
        setupSpinner(savedInstanceState)

        return binding.root
    }

    private fun setupSpinner(savedInstanceState: Bundle?) {
        val opcoes = arrayOf("Pedra", "Papel", "Tesoura")

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            opcoes
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.spinner.adapter = adapter

        // ✅ RESTAURAR ESTADO — AGORA NO LUGAR CERTO
        val jogadaSalva = savedInstanceState?.getString("jogada")
        if (jogadaSalva != null) {
            val index = opcoes.indexOf(jogadaSalva)
            if (index >= 0) binding.spinner.setSelection(index)
        }

        // Listener normal
        var firstFire = true
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (firstFire) { firstFire = false; return }

                val jogada = opcoes[position]
                Snackbar.make(binding.root, "Você escolheu: $jogada",
                    Snackbar.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val jogadaAtual = binding.spinner.selectedItem.toString()
        outState.putString("jogada", jogadaAtual)
    }

    private fun setupBottomNavegation() {
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.resultadoFragment2 -> {
                    val escolha = binding.spinner.selectedItem.toString()
                    val action =
                        JogadasFragmentDirections.actionJogadasFragment2ToResultadoFragment2(escolha)
                    findNavController().navigate(action)
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

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            binding.root.closeDrawers()
            when (menuItem.itemId) {
                R.id.inicio -> {
                    findNavController().popBackStack()
                    true
                }
                R.id.resultadoFragment2 -> {
                    val escolha = binding.spinner.selectedItem.toString()
                    val action =
                        JogadasFragmentDirections.actionJogadasFragment2ToResultadoFragment2(escolha)
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupToolBar(activity: AppCompatActivity) {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_)
    }
}