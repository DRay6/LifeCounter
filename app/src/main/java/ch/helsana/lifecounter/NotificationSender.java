package ch.helsana.lifecounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationSender extends BroadcastReceiver {
    public NotificationSender() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, SendNotification.class);
        context.startService(intent1);
    }
}
