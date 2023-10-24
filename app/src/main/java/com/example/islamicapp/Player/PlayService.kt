package com.example.islamicapp.Player

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat

import com.example.islamy_project.MyApplication
import com.example.islamy_project.R


class PlayService : Service() {
    //2
    val binder = MyBinder()
    var mediaPlayer = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        //2
        return binder
    }

    //1
    inner class MyBinder : Binder() {
        fun getService(): PlayService {
            return this@PlayService
        }
    }

    //3
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val urlToPlay = intent?.getStringExtra("url")
        val name = intent?.getStringExtra("name")
        val action = intent?.getStringExtra("action")

        if (urlToPlay != null && name != null)
            startMediaPlayer(urlToPlay, name)

        if (action != null) {
            Log.e("action", action)
            if (action.equals("play")) {
                playPauseMediaPlayer()
            } else if (action.equals("stop")) {
                stopMediaPlayer()
            }
        }
        return START_NOT_STICKY

    }


    var name: String = ""
    fun startMediaPlayer(urlToPlay: String, name: String) {
        pauseMediaPlayer()
        this.name = name
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            it.start()
        }
//        createNotificationForMediaPlayer(name)

    }
    private fun playPauseMediaPlayer() {
        Log.e("action", "playPause")
        if (mediaPlayer.isPlaying) {

            mediaPlayer.pause()
        } else if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
//        updateNotification();
        //    createNotificationForMediaPlayer("stopped");

    }
    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        stopForeground(true)
        stopSelf()
    }




    //fun start >>
    fun pauseMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            //  mediaPlayer.reset()
        }
    }
    //the sacend noti >start media
//    private fun createNotificationForMediaPlayer(name: String) {
//        val defaultView = RemoteViews(packageName, R.layout.notificationview);
//        defaultView.setTextViewText(R.id.title, "Islami App Radio")
//        defaultView.setTextViewText(R.id.desc, name)
//        defaultView.setImageViewResource(
//            R.id.play,
//            if (mediaPlayer.isPlaying) R.drawable.ic_stop else R.drawable.play
//        )
//
//        defaultView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())
//        defaultView.setOnClickPendingIntent(R.id.stop, getStopButtonPendingIntent())
//
//        var builder =
//            NotificationCompat.Builder(this,  MyApplication.RADIO_PLAYER_NOTIFCATION_CHANNEL)
//                //"اكتب هنا اسلامي لحد م نعمل الابلكيشن"
//                .setSmallIcon(R.drawable.ic_notofiacyion)
//                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                .setCustomContentView(defaultView)
//                .setPriority(NotificationCompat.PRIORITY_LOW)
//                .setSound(null)
//
//        startForeground(20, builder.build())
//
//    }







    //the first notfiaction at func >>
//    private fun updateNotification() {
//        val defaultView = RemoteViews(packageName, R.layout.notificationview);
//        defaultView.setTextViewText(R.id.title, "Islami App Radio")
//        defaultView.setTextViewText(R.id.desc, name)
//        defaultView.setImageViewResource(
//            R.id.play,
//            if (mediaPlayer.isPlaying) R.drawable.ic_stop else R.drawable.play
//        )
//        defaultView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())
//        defaultView.setOnClickPendingIntent(R.id.stop, getStopButtonPendingIntent())
//
//        var builder =
//            NotificationCompat.Builder(this, MyApplication.RADIO_PLAYER_NOTIFCATION_CHANNEL)
//                .setSmallIcon(R.drawable.ic_notofiacyion)
//                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                .setCustomContentView(defaultView)
//                .setPriority(NotificationCompat.PRIORITY_LOW)
//                .setDefaults(0)
//                .setSound(null)
//        val notificationManager: NotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(20, builder.build())
//    }


    val PLAY_ACTION = "play"
    val STOP_ACTION = "stop"
    private fun getPlayButtonPendingIntent(): PendingIntent {
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("action", PLAY_ACTION)
        val pendingIntent = PendingIntent.getService(
            this,
            12, intent, PendingIntent.FLAG_IMMUTABLE
        );
        return pendingIntent;

    }

    private fun getStopButtonPendingIntent(): PendingIntent {
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("action", STOP_ACTION)
        val pendingIntent = PendingIntent.getService(
            this,
            0, intent, PendingIntent.FLAG_IMMUTABLE
        );
        return pendingIntent;

    }





}