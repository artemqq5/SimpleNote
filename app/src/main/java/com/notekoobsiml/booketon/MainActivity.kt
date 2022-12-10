package com.notekoobsiml.booketon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.notekoobsiml.booketon.databinding.FragmentMainViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var currentTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navigationController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController

        val toast = Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT)

        when (navigationController.currentDestination?.label) {
            "fragment_main_view" -> {
                if (currentTime + 2000L < System.currentTimeMillis()) {
                    currentTime = System.currentTimeMillis()
                    toast.show()
                } else {
                    super.onBackPressed()
                }
            }

            "fragment_create_card", "fragment_edit_card" -> {
                super.onBackPressed()
            }
        }


    }
}