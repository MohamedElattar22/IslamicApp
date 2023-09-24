package com.example.islamicapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Adapters.suraContentAdapter
import com.example.islamicapp.R

class suraDetailsActivity : AppCompatActivity() {
    var suraName : String ?= null
    var suraPos : Int ?= null
    lateinit var suraTitleText : TextView
    lateinit var backBtn : ImageView
    lateinit var suraRv : RecyclerView
    lateinit var Adapter : suraContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_details)
        initView()


    }
    private fun initView(){
        suraName =  intent.getStringExtra("sura_name")
        suraPos =  intent.getIntExtra("sura_position" , 1)
        suraTitleText = findViewById(R.id.suraTitle)
        backBtn = findViewById(R.id.backbtn)
        suraTitleText.text = suraName
        backBtn.setOnClickListener {
            finish()
        }
        suraRv = findViewById(R.id.suraContentRv)

        Adapter = suraContentAdapter(null)
        readFromFileAssets()
        suraRv.adapter = Adapter

    }
    private fun readFromFileAssets(){
        val fileContent = assets.open("$suraPos.txt").bufferedReader().use {
            it.readText()
        }
      val data =  fileContent.split("/n")
        Adapter.updateData(data)
    }
}