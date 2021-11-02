package com.example.webengagesdkintegration.sample;
import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.webengagesdkintegration.R;
import com.example.webengagesdkintegration.sample.activity.MyPushRenderer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.webengage.sdk.android.PushChannelConfiguration;
import com.webengage.sdk.android.WebEngage;
import com.webengage.sdk.android.WebEngageConfig;
import com.webengage.sdk.android.WebEngageActivityLifeCycleCallbacks;

public class MyApplication extends Application {
    private static final String YOUR_WEBENGAGE_LICENSE_CODE ="~aa1326d6" ;
    @Override
    public void onCreate() {
        super.onCreate();
        initWebEngage();
        FirebaseApp.initializeApp(this);


//fetch firebase from firebase console through google serivies
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                try {
                    String token = task.getResult();
                    Log.i("TOKEN", "FCM Instance Id Token: " + token);
                    WebEngage.get().setRegistrationID(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //custom notification icon
        WebEngageConfig webEngageConfig1 = new WebEngageConfig.Builder()
                .setPushSmallIcon(R.drawable.inotification_icon)
                .setPushLargeIcon(R.drawable.inotification_icon)
                .setPushAccentColor(Color.parseColor("#ff0000"))
                .build();
        registerActivityLifecycleCallbacks(new WebEngageActivityLifeCycleCallbacks(this, webEngageConfig1));




        PushChannelConfiguration pushChannelConfiguration = new PushChannelConfiguration.Builder()
                .setNotificationChannelName("Offers")
                .setNotificationChannelDescription("Product offer weekly updates")
                .setNotificationChannelImportance(3)
                .setNotificationChannelVibration(true)
                .setNotificationChannelLightColor(Color.parseColor("#ff0000"))
                .setNotificationChannelShowBadge(true)
                .build();


        WebEngageConfig webEngageConfig2 = new WebEngageConfig.Builder()
                .setDefaultPushChannelConfiguration(pushChannelConfiguration)
                .build();


        registerActivityLifecycleCallbacks(new WebEngageActivityLifeCycleCallbacks(this, webEngageConfig2));


    }

    private void initWebEngage() {

        WebEngageConfig webEngageConfig = new WebEngageConfig.Builder()
                .setWebEngageKey(YOUR_WEBENGAGE_LICENSE_CODE)
                .setDebugMode(true) // only in development mode
                .build();
        registerActivityLifecycleCallbacks(new WebEngageActivityLifeCycleCallbacks(this, webEngageConfig));


        // Register for custom push render callbacks

        MyPushRenderer myPushRenderer = new MyPushRenderer();
        WebEngage.registerCustomPushRenderCallback(myPushRenderer);
        WebEngage.registerCustomPushRerenderCallback(myPushRenderer);
    }


}
