package com.example.jokenpo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jokenpo.databinding.FragmentResultadoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class ResultadoFragment : Fragment() {

    private lateinit var binding: FragmentResultadoBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav2: BottomNavigationView

    private var escolhaRobo: String? = null
    private var resultado: String? = null

    private val engine = JokenpoEngine()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentResultadoBinding.inflate(inflater, container, false)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar3)

        drawer = binding.root
        navDrawer = binding.navView2
        bottomNav2 = binding.bottomNav2

        setupToolBar(activity)
        setupDrawer()
        setupBottomNavegation()

        Log.d("lifeCycle", "Resultado onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("lifeCycle", "Resultado onViewCreated")

        // 🎯 PEGAMOS A JOGADA DIRETO DA ACTIVITY
        val activity = requireActivity() as MainActivity
        val escolhaUsuario = activity.currentPlay

        if (savedInstanceState != null) {

            escolhaRobo = savedInstanceState.getString("robo")
            resultado = savedInstanceState.getString("resultado")

        } else {

            val (res, jogadaRobo) = engine.calcularResultado(escolhaUsuario)
            escolhaRobo = jogadaRobo

            resultado = when (res) {
                JokenpoEngine.Resultado.VITORIA -> "Você venceu!"
                JokenpoEngine.Resultado.DERROTA -> "Você perdeu!"
                JokenpoEngine.Resultado.EMPATE -> "Empate!"
            }
        }

        binding.textView3.text = "Resultado"
        binding.textView2.text =
            "Você: $escolhaUsuario\nRobô: $escolhaRobo\n$resultado"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("robo", escolhaRobo)
        outState.putString("resultado", resultado)
    }

    private fun setupDrawer() {
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.inicio -> {
                    findNavController().popBackStack(R.id.inicioFragment, false)
                    true
                }
                R.id.jogadasFragment2 -> {
                    findNavController().navigate(
                        ResultadoFragmentDirections.actionResultadoFragment2ToJogadasFragment2()
                    )
                    true
                }
                R.id.resultadoFragment2 -> true
                else -> false
            }
        }
    }

    private fun setupBottomNavegation() {
        bottomNav2.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.jogadasFragment2 -> {
                    findNavController().navigate(
                        ResultadoFragmentDirections.actionResultadoFragment2ToJogadasFragment2()
                    )
                    true
                }
                R.id.resultadoFragment2 -> true
                else -> false
            }
        }
        bottomNav2.selectedItemId = R.id.resultadoFragment2
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
}