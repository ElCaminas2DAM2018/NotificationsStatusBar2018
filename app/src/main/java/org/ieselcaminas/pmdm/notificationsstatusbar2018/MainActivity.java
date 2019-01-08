package org.ieselcaminas.pmdm.notificationsstatusbar2018;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int MY_ID_NOTIF = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(this, 1234, notIntent, 0);

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel("channel1", "Web site checker", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("My description");
            notificationManager.createNotificationChannel(mChannel);
        }

        int icon = android.R.drawable.stat_sys_warning;
        CharSequence textState = "Attention";
        CharSequence textContent = "Website not available";
        long time = System.currentTimeMillis();
        Notification notification = new NotificationCompat.Builder(this, "channel1")
                .setSmallIcon(icon)
                .setContentTitle(textState)
                .setContentText(textContent)
                .setWhen(time)
                .setContentIntent(contIntent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.DEFAULT_SOUND;
        notification.flags |= Notification.DEFAULT_VIBRATE;
        notification.flags |= Notification.DEFAULT_LIGHTS;

        notificationManager.notify(MY_ID_NOTIF, notification);
    }
}
