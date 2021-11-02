package com.example.webengagesdkintegration.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webengagesdkintegration.R;
import com.example.webengagesdkintegration.sample.utils.Keys;
import com.example.webengagesdkintegration.sample.utils.SharedPreferenceHelper;

// Import WebEngage 'User'
import com.webengage.sdk.android.User;
import com.webengage.sdk.android.WebEngage;
import com.webengage.sdk.android.utils.Gender;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edIdentity;
    User weUser = WebEngage.get().user();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isUserLoggedIn = SharedPreferenceHelper.getBoolean(this, Keys.IS_USER_LOGGED_IN, false);
        String loggedInUserIdentity = SharedPreferenceHelper.getString(this, Keys.LOGGED_IN_USER_IDENTITY, null);
        if (isUserLoggedIn && !TextUtils.isEmpty(loggedInUserIdentity)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_identity: {
                String identity = edIdentity.getText().toString();
                if (!TextUtils.isEmpty(identity)) {
                    SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, true);
                    SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, identity);
                      //login data  will fetch using this
                    WebEngage.get().user().login(identity);
                     WebEngage.get().user().setAttribute("mail",identity);

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, R.string.enter_email_id, Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.btn_register: {
                startActivity(new Intent(this, RegisterActivity.class));
            }
            break;

            case R.id.btn_guest_login: {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            break;
        }
    }

    private void init() {
        edIdentity = findViewById(R.id.et_identity);
        findViewById(R.id.btn_update_identity).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.btn_guest_login).setOnClickListener(this);
    }
}