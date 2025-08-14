package com.example.jokenpo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jokenpo.databinding.ActivityResultadoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav2: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawer = binding.root
        navDrawer = binding.navView2
        bottomNav2 = binding.bottomNav2

        // Define a toolbar como ActionBar
        setSupportActionBar(binding.toolbar3)

        setupToolBar()
        setupDrawer()
        setupBottomNavegation()
    }

    private fun setupToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_)
    }

    private fun setupDrawer() {
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.inicio -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.jogador1 -> {
                    startActivity(Intent(this, MainActivity2::class.java))
                    true
                }
                R.id.resultado -> {

                    true
                }
                else -> false
            }
        }
    }

    // Botão Home abre o Drawer
    override fun onSupportNavigateUp(): Boolean {
        drawer.openDrawer(GravityCompat.START)
        return true
    }

    private fun setupBottomNavegation() {
        bottomNav2.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.jogador1 -> {
                    startActivity(Intent(this, MainActivity2::class.java))
                    true
                }
                R.id.resultado -> {
                    // Já está na tela de resultado, não reabrir
                    true
                }
                else -> false
            }
        }
        // Marca o item atual
        bottomNav2.selectedItemId = R.id.resultado
    }
}
