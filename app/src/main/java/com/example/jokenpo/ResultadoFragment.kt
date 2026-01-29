package com.example.jokenpo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jokenpo.databinding.FragmentResultadoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class ResultadoFragment : Fragment() {

    private val args: ResultadoFragmentArgs by navArgs()
    private lateinit var binding: FragmentResultadoBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav2: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultadoBinding.inflate(inflater, container, false)
        val view = binding.root

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar3)


        drawer = binding.root
        navDrawer = binding.navView2
        bottomNav2 = binding.bottomNav2


        setupToolBar(activity)
        setupDrawer()
        setupBottomNavegation()


        return binding.root
    }

    private fun setupToolBar(activity: AppCompatActivity) {
        activity.setSupportActionBar(binding.toolbar3)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_)
        }


        binding.toolbar3.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    private fun setupDrawer() {
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.inicio -> {

                    findNavController().popBackStack(R.id.inicioFragment,false)

                    true
                }
                R.id.jogadasFragment2 -> {
                    val action = ResultadoFragmentDirections.actionResultadoFragment2ToJogadasFragment2()
                    findNavController().navigate(action)
                    true
                }
                R.id.resultadoFragment2 -> {

                    true
                }
                else -> false
            }
        }
    }

    private fun setupBottomNavegation() {
        bottomNav2.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.jogadasFragment2 -> {
                   val action = ResultadoFragmentDirections.actionResultadoFragment2ToJogadasFragment2()
                    findNavController().navigate(action)
                    true
                }
                R.id.resultadoFragment2 -> {
                    // Já está na tela de resultado, não reabrir

                    true
                }
                else -> false
            }
        }
        // Marca o item atual
        bottomNav2.selectedItemId = R.id.resultadoFragment2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val escolhaUsuario = args.jogadasUsuario
        val escolhaRobo = listOf("Pedra", "Papel", "Tesoura").random()

        val resultado = quandoVence(escolhaUsuario, escolhaRobo)

        binding.textView3.text = "Resultado"
        binding.textView2.text = "Você: $escolhaUsuario\nRobô: $escolhaRobo\n O Jogador XXX ganhou"
    }

    private fun quandoVence(usuario: String, robo: String): String {
        return when {
            usuario == robo -> "Empatou! "
            (usuario == "Pedra" && robo == "Tesoura") ||
                    (usuario == "Papel" && robo == "Pedra") ||
                    (usuario == "Tesoura" && robo == "Papel") -> "Você Venceu! "
            else -> "Você Perdeu! "
        }
    }
}

