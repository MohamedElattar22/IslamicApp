package com.example.islamy_project.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.islamicapp.Fragements.OnItemClickListener
import com.example.islamicapp.Fragements.RadioAdapter
import com.example.islamicapp.Player.PlayService

import com.example.islamy_project.api.model.ApiManger
import com.example.islamy_project.api.model.RadioResponse
import com.example.islamy_project.api.model.RadioResponseItem
import com.example.islamy_project.databinding.FragmentRadioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RadioFragment : Fragment() {
    val adapter = RadioAdapter()
    lateinit var viewBinding: FragmentRadioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRadioBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.radioRv.adapter = adapter

        adapter.onItemClickPlay = object : OnItemClickListener {
            override fun onItemClick(position: Int, item: RadioResponseItem) {
                startRadioService(item)
            }

        }
        adapter.onItemClickStop = object : OnItemClickListener {
            override fun onItemClick(position: Int, item: RadioResponseItem) {
                stopPlayerService()
            }

        }

        //call api
        getChannelsFromApi()

    }

    override fun onStart() {
        super.onStart()
        startService();
        bindService()
    }

    private fun startService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
    }

    fun bindService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun startRadioService(item: RadioResponseItem) {
        if (bound && item.radioUrl != null && item.name != null)
            service.startMediaPlayer(item.radioUrl  ,item.name);
    }

    fun stopPlayerService() {
        if (bound)
            service.pauseMediaPlayer()
    }

    lateinit var service: PlayService;
    var bound = false;

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, mBinder: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = mBinder as PlayService.MyBinder
            service = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    private fun getChannelsFromApi() {
        Log.e("getapi","getapi")

        //show loading dialog
        ApiManger.getWebService()
            .getRadio()
            .enqueue(object : Callback<RadioResponse> {
                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                    //hideloadingDialog
                    Toast.makeText(activity, t.localizedMessage ?: "error", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    Log.e("resonse",response.body()?.radios.toString())

                    val channels = response.body()?.radios;
                    adapter.changeData(channels ?: listOf())
                }
            }
            )
    }
}
         