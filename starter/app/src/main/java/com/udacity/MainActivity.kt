package com.udacity

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action
    private var repository: Repository = Repository.NONE
    private enum class Repository(val url: String) {
        NONE(""),
        BUMPTECH("https://github.com/bumptech/glide"),
        UDACITY("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter"),
        RETROFIT("https://github.com/square/retrofit")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("WWD", "in onCreate main activity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        custom_button.setOnClickListener {
            download()
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("WWD", "in receiver on receive")
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }

    private fun download() {
        Log.d("WWD", "in download")
        if (repository == Repository.NONE) {
            Toast.makeText(this, "PLEASE SELECT A URL ABOVE", Toast.LENGTH_LONG).show()
            return;
        }
        var downloadUrl = repository.url + "archive/master.zip"
        Log.d("WWD", "the url is " + downloadUrl)
        val request =
            DownloadManager.Request(Uri.parse(downloadUrl))
                .setTitle(getString(R.string.app_name))
                .setDescription(getString(R.string.app_description))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
    }

    companion object {
        private const val URL =
            "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip"
        private const val CHANNEL_ID = "channelId"
    }

    fun onRadioButtonClicked(view: View) {
        Log.d("WWD", "radio button clicked")
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_glide ->
                    if (checked) {
                        repository = Repository.BUMPTECH
                    }
                R.id.radio_udacity ->
                    if (checked) {
                        repository = Repository.UDACITY
                    }
                R.id.radio_retrofit ->
                    if (checked) {
                        repository = Repository.RETROFIT
                    }
                else ->
                    repository = Repository.NONE
            }
        }
    }

}
