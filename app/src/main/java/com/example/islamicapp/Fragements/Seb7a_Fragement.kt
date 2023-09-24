package com.example.islamicapp.Fragements

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.islamicapp.Data.Seb7a_content
import com.example.islamicapp.R

class Seb7a_Fragement : Fragment() {
    var mMediaPlayer: MediaPlayer? = null
    var temp = 1
    var temp1 = 1
    var clickNum = 0
    lateinit var progressIndicator : ProgressBar
    lateinit var progressbtn : CardView
    lateinit var tasbee7Tv : TextView
    lateinit var tasbee7Cnt : TextView
    lateinit var resetBtn : ImageView
    lateinit var soundPlayBtn : ImageView
    var tasbe7_content : List<Seb7a_content> ?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.seb7a__fragement,container,false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.circularProgressIndicator)
        progressbtn = view.findViewById(R.id.progressbtn)
        tasbee7Tv = view.findViewById(R.id.Tasbeeh_Content)
        tasbee7Cnt = view.findViewById(R.id.tasbee7_cnt)
        resetBtn = view.findViewById(R.id.resetbtn)
        soundPlayBtn = view.findViewById(R.id.soundPlay)



        resetBtn.setOnClickListener {
            temp = 1
            progressIndicator.setProgress(0)
            tasbee7Cnt.text = temp.toString()
        }
        progressbtn.setOnClickListener {
            progressIndicator.incrementProgressBy(3)
            temp++
            tasbee7Cnt.text = temp.toString()
            checkTasbe7(temp)
            replaceTasbeehContent(temp1)
        }

    }
    private fun checkTasbe7(temp : Int){
        if (temp == 33){
            this.temp = 0
            progressIndicator.setProgress(0)
            temp1++
        }

    }
    private fun replaceTasbeehContent(temp1 : Int){
        if (temp1 == 1){
           tasbee7Tv.text = Seb7a_content().tasbeh1


        }
        else if(temp1 == 2){
            tasbee7Tv.text = Seb7a_content().tasbeh2


        }
        else if(temp1 == 3 ){
            tasbee7Tv.text = Seb7a_content().tasbeh3

        }
        else{
            this .temp1 = 1
        }

    }
    fun playSound(id : Int) {
        if (mMediaPlayer == null) {
            mMediaPlayer =MediaPlayer.create(requireActivity(),id)
//            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        }
        else mMediaPlayer!!.start()
    }
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

}