package com.example.islamicapp.Activities

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islamicapp.Fragements.Hadeeth_Fragement
import com.example.islamicapp.Fragements.Quraan_Fragement
import com.example.islamicapp.Fragements.Seb7a_Fragement
import com.example.islamicapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    val Quraan_Fragement = Quraan_Fragement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportFragmentManager.beginTransaction()
            .replace(R.id.Main_Fragement, Quraan_Fragement)
            .commit()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            if(it.itemId == R.id.Quran_Frag){
                pushFragement(Quraan_Fragement())
            }
            else if(it.itemId == R.id.Hadeeth_Frag){
                pushFragement(Hadeeth_Fragement())
            }
            else if(it.itemId == R.id.Tasbeh_Frag){
                pushFragement(Seb7a_Fragement())
            }

            return@setOnItemSelectedListener true
        }



    }

    private fun pushFragement(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Main_Fragement, fragment)
            .commit()
    }

}