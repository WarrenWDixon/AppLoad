package com.udacity

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // TODO: Step 2.0 add style
    val cicadaImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cicada
    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(cicadaImage)
        .bigLargeIcon(null)

    // Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.download_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_baseline_cloud_download_24)
        .setContentTitle(applicationContext
            .getString(R.string.notification_title))
        .setContentText(messageBody)
        .setStyle(bigPicStyle)

    // Deliver the notification
    notify(NOTIFICATION_ID, builder.build())
}

