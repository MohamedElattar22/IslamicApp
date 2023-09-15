package com.example.islamicapp.Fragements

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Activities.suraDetailsActivity
import com.example.islamicapp.Adapters.QuraanAdapter
import com.example.islamicapp.Adapters.onSurahItemClick
import com.example.islamicapp.Data.Constants
import com.example.islamicapp.Data.SurahData
import com.example.islamicapp.R

class Quraan_Fragement : Fragment() {
    lateinit var rvSorah : RecyclerView
    lateinit var suraNameadApter: QuraanAdapter
    lateinit var recentSurah : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_quraan__fragement, container ,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSorah = view.findViewById(R.id.sorahRv)
        recentSurah = view.findViewById(R.id.recentSura)
        suraNameadApter = QuraanAdapter(Constants.ArSuras.mapIndexed {position , name ->
            SurahData(name , position+1)
            }
        )
        rvSorah.adapter=suraNameadApter
        suraNameadApter.onSurahItemClick = object : onSurahItemClick {
            @SuppressLint("SetTextI18n")
            override fun onSuraClick(suraName: SurahData) {
                recentSurah.text = " سورة ${suraName.surahName}"
                val intent = Intent(requireActivity() , suraDetailsActivity::class.java)
                intent.putExtra("sura_name" , suraName.surahName)
                intent.putExtra("sura_position" , suraName.surahNumber)

                startActivity(intent)


            }

        }
    }

}



