package com.example.service_sample.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.example.service_sample.MainActivity;
import com.example.service_sample.R;


public class MyService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    Vibrator vibe ;
    long[] pattern = new long[8];

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        
        pattern[0] = 1000; // Wait one second
        pattern[1] = 950;  // Vibrate for most a second
        pattern[2] = 50;   // A pause long enough to feel distinction
        pattern[3] = 950;  // Repeat 3 more times
        pattern[4] = 50;
        pattern[5] = 950;
        pattern[6] = 50;
        pattern[7] = 950;
        vibe.vibrate(pattern,1);

        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    @Override
    public void onDestroy() {
        vibe.cancel();
        super.onDestroy();

    }
}
