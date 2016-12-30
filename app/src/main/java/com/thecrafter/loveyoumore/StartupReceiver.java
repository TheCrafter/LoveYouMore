package com.thecrafter.loveyoumore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // start your service here
        context.startService(new Intent(context, NotificationService.class));
    }

}
