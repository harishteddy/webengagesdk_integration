package com.example.webengagesdkintegration.sample.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import com.example.webengagesdkintegration.R;
import com.example.webengagesdkintegration.sample.utils.Keys;
import com.example.webengagesdkintegration.sample.utils.SharedPreferenceHelper;
import com.webengage.sdk.android.Analytics;
import com.webengage.sdk.android.WebEngage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    Analytics weAnalytics = WebEngage.get().analytics();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        requestPermissions();
        weAnalytics.track("Product - Page Viewed");

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fcm_token: {
                /*String fcmToken = SmartechHelper.getDevicePushToken(this);
                if (!TextUtils.isEmpty(fcmToken)) {
                    Utility.copyToClipboard(this, "FCM Token", fcmToken);
                    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                }*/
            }
            break;

            case R.id.tv_guid: {
                /*String guid = SmartechHelper.getDeviceUniqueId(this);
                if (!TextUtils.isEmpty(guid)) {
                    Utility.copyToClipboard(this, "Smartech GUID", guid);
                    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                }*/
            }
            break;

            case R.id.tv_add_to_cart: {

                Map<String, Object> addedToCartAttributes = new HashMap<>();
                addedToCartAttributes.put("Product ID", 1337);
                addedToCartAttributes.put("Price", 39.80);
                addedToCartAttributes.put("Quantity", 1);
                addedToCartAttributes.put("Product", "Givenchy Pour Homme Cologne");
                addedToCartAttributes.put("Category", "Fragrance");
                addedToCartAttributes.put("Currency", "USD");
                addedToCartAttributes.put("Discounted", true);

                weAnalytics.track("Added to Cart", addedToCartAttributes);

                Toast.makeText(this, R.string.tracking_add_to_cart, Toast.LENGTH_SHORT).show();


                Map<String, Object> orderPlacedAttributes = new HashMap<>();
                orderPlacedAttributes.put("Amount", 808.48);
                orderPlacedAttributes.put("Product 1 SKU Code", "UHUH799");
                orderPlacedAttributes.put("Product 1 Name", "Armani Jeans");
                orderPlacedAttributes.put("Product 1 Price", 300.49);
                orderPlacedAttributes.put("Product 1 Size", "L");
                orderPlacedAttributes.put("Product 2 SKU Code", "FBHG746");
                orderPlacedAttributes.put("Product 2 Name", "Hugo Boss Jacket");
                orderPlacedAttributes.put("Product 2 Price", 507.99);
                orderPlacedAttributes.put("Product 2 Size", "L");
                String dateStr = "2017-10-06T09:27:37Z";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                try {
                    Date date = format.parse(dateStr);
                    orderPlacedAttributes.put("Delivery Date", date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                orderPlacedAttributes.put("Delivery City", "San Francisco");
                orderPlacedAttributes.put("Delivery ZIP", "94121");
                orderPlacedAttributes.put("Coupon Applied", "BOGO17");

                weAnalytics.track("Order Placed", orderPlacedAttributes);


            }
            break;

            case R.id.tv_checkout: {


                Map<String,Object> screenData = new HashMap<String, Object>();
                screenData.put("productId", "~hs7674");
                screenData.put("price", 1200);

                weAnalytics.setScreenData(screenData);
                Toast.makeText(this, R.string.tracking_checkout, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.tv_add_to_wish_list: {
                Toast.makeText(this, R.string.tracking_add_to_wish_list, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.tv_update_profile: {
                // NOTE: User profile should be set only when user identity is present in Smartech SDK.
//                String identity = SmartechHelper.getUserIdentity(this);
//                if (!TextUtils.isEmpty(identity)) {
                startActivity(new Intent(this, ProfileActivity.class));
//                } else {
//                    Toast.makeText(this, R.string.please_login_first, Toast.LENGTH_SHORT).show();
//                }
            }
            break;

            case R.id.tv_clear_identity: {
                SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, false);
                SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, null);
                Toast.makeText(this, R.string.user_identity_cleared, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.tv_logout: {
                SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, false);
                SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, null);

                WebEngage.get().user().logout();
                startActivity(new Intent(this, LoginActivity.class));
                Toast.makeText(this, R.string.you_are_logged_out, Toast.LENGTH_SHORT).show();
                finish();
            }
            break;

            case R.id.tv_set_location: {
                Toast.makeText(this, R.string.default_user_location_set, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.sw_opt_pn: {
            }
            break;

            case R.id.sw_opt_in_app: {
            }
            break;

            case R.id.sw_opt_tracking: {
            }
            break;
        }
    }

    private void init() {
        /*String fcmToken = SmartechHelper.getDevicePushToken(this);
        if (!TextUtils.isEmpty(fcmToken)) {
            TextView tvFcmToken = findViewById(R.id.tv_fcm_token);
            tvFcmToken.setText(fcmToken);
            tvFcmToken.setOnClickListener(this);
        }

        String guid = SmartechHelper.getDeviceUniqueId(this);
        if (!TextUtils.isEmpty(guid)) {
            TextView tvGuid = findViewById(R.id.tv_guid);
            tvGuid.setText(guid);
            tvGuid.setOnClickListener(this);
        }*/

        findViewById(R.id.tv_add_to_cart).setOnClickListener(this);
        findViewById(R.id.tv_checkout).setOnClickListener(this);
        findViewById(R.id.tv_add_to_wish_list).setOnClickListener(this);

        findViewById(R.id.tv_update_profile).setOnClickListener(this);
        findViewById(R.id.tv_clear_identity).setOnClickListener(this);
        findViewById(R.id.tv_logout).setOnClickListener(this);

        SwitchCompat swOptPn, swOptInApp, swOptTracking;
        swOptPn = findViewById(R.id.sw_opt_pn);
        //swOptPn.setChecked(SmartechHelper.hasOptedPushNotification(this));
        swOptPn.setOnCheckedChangeListener(this);

        swOptInApp = findViewById(R.id.sw_opt_in_app);
        //swOptInApp.setChecked(SmartechHelper.hasOptedInAppMessage(this));
        swOptInApp.setOnCheckedChangeListener(this);

        swOptTracking = findViewById(R.id.sw_opt_tracking);
        //swOptTracking.setChecked(SmartechHelper.hasOptedTracking(this));
        swOptTracking.setOnCheckedChangeListener(this);
    }

    private void requestPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
        };

        ActivityCompat.requestPermissions(this, permissions, 1);
    }





}