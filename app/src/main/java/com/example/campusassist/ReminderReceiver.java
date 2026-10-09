package com.example.campusassist;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "CampusAssistChannel";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager =
                (NotificationManager)
                        context.getSystemService(
                                Context.NOTIFICATION_SERVICE
                        );

        // Create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Campus Assist Notifications",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );

            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        context,
                        CHANNEL_ID
                )
                        .setSmallIcon(
                                android.R.drawable.ic_dialog_info
                        )
                        .setContentTitle(
                                "Campus Assist Reminder"
                        )
                        .setContentText(
                                "Check your college notices and complaints."
                        )
                        .setAutoCancel(true);

        notificationManager.notify(
                1001,
                builder.build()
        );
    }
}