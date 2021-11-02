package com.example.webengagesdkintegration.sample.activity;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.webengagesdkintegration.R;
import com.webengage.sdk.android.PendingIntentFactory;
import com.webengage.sdk.android.actions.render.CallToAction;
import com.webengage.sdk.android.actions.render.PushNotificationData;
import com.webengage.sdk.android.callbacks.CustomPushRender;
import com.webengage.sdk.android.callbacks.CustomPushRerender;
import com.webengage.sdk.android.utils.WebEngageConstant;

import java.util.List;

public class MyPushRenderer implements CustomPushRender, CustomPushRerender {
    private static final String MY_CHANNEL_ID =  "100 ";

    @Override
    public boolean onRender(Context context, PushNotificationData pushNotificationData) {
        if (pushNotificationData == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // create notification channel
        }

        // Big text
        if (pushNotificationData.getStyle() == WebEngageConstant.STYLE.BIG_TEXT) {
            PendingIntent deletePendingIntent = PendingIntentFactory.constructPushDeletePendingIntent(context, pushNotificationData);
            PendingIntent contentPendingIntent = PendingIntentFactory.constructPushClickPendingIntent(context, pushNotificationData, pushNotificationData.getPrimeCallToAction(), true);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MY_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(pushNotificationData.getTitle())
                    .setContentText(pushNotificationData.getContentText())
                    .setContentIntent(contentPendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(pushNotificationData.getBigTextStyleData().getBigContentTitle())
                            .bigText(pushNotificationData.getBigTextStyleData().getBigText()))
                    .setDeleteIntent(deletePendingIntent);

            // actions
            List<CallToAction> actionsList = pushNotificationData.getActions();
            if (actionsList != null) {
                for (CallToAction callToAction : actionsList) {
                    PendingIntent ctaPendingIntent = PendingIntentFactory.constructPushClickPendingIntent(context, pushNotificationData, callToAction, true);
                    builder.addAction(0, callToAction.getText(), ctaPendingIntent);
                }
            }

            Notification notification = builder.build();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(pushNotificationData.getVariationId().hashCode(), notification);
            Log.d(TAG, "Rendered push notification from application: big text");
            return true;
        }

        return false;
    }


    @Override
    public boolean onRerender(Context context, PushNotificationData pushNotificationData, Bundle extras) {
        return false;
    }
}
