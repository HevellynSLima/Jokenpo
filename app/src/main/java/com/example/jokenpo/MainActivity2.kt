package com.example.jokenpo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jokenpo.databinding.ActivityJogadasBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout

    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var binding: ActivityJogadasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJogadasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)
        drawer = binding.root
        bottomNav = binding.bottomNav
        navDrawer = binding.navView

        setupToolBar()
        setupDrawer()
        setupBottomNavegation()



        val opcoes = listOf("Selecione uma jogada", "Pedra", "Papel", "Tesoura")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opcoes
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.spinner.adapter = adapter

        var firstFire = true
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                if (firstFire) { firstFire = false; return }
                val jogada = opcoes[position]
                Snackbar.make(binding.root, "Você escolheu: $jogada", Snackbar.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) { /* opcional */ }
        }
    }



    private fun setupToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_)

    }


    override fun onSupportNavigateUp(): Boolean {
        drawer.openDrawer(GravityCompat.START)
        return true
    }


    private fun setupDrawer() {
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.inicio -> {
                    onBackPressedDispatcher.onBackPressed()
                    true
                }
                R.id.jogador1 -> {

                    true
                }
                R.id.resultado -> {
                    val Activity_resultadoIntent = Intent(this, MainActivity3::class.java)
                    startActivity(Activity_resultadoIntent)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }



    private fun setupBottomNavegation(){
        bottomNav.setOnItemSelectedListener { menuItem -> when(menuItem.itemId){
            R.id.jogador1 -> {
                val Activity_resultadoIntent = Intent(this, MainActivity2::class.java)
                startActivity(Activity_resultadoIntent)
                true
            }
            R.id.resultado -> {
                val Activity_resultadoIntent = Intent(this, MainActivity3::class.java)
                startActivity(Activity_resultadoIntent)
                true
            }
            else -> {
                false
            }
        }
        }
}
}


