package com.example.annapurna.App;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.example.annapurna.Utils;

public class Notification extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        notificationChannel();
    }
    private void notificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(Utils.CHANNEL_ID, Utils.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(Utils.CHANNEL_DESC);
            NotificationManager managerCompat = getSystemService(NotificationManager.class);
            managerCompat.createNotificationChannel(channel);

        }
    }
}
