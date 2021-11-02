package com.example.webengagesdkintegration.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.webengagesdkintegration.R;
import com.example.webengagesdkintegration.sample.utils.Utility;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etPhone, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear: {
                EditText[] inputs = {etFirstName, etLastName, etPhone, etCity};
                Utility.clearInputs(inputs);
            }
            break;

            case R.id.btn_update_profile: {
                EditText[] inputs = {etFirstName, etLastName, etPhone, etCity};
                if (!Utility.areInputsEmpty(inputs)) {
                    Toast.makeText(this, R.string.profile_updated, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, R.string.all_fields_are_required, Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private void init() {
        EditText etIdentity = findViewById(R.id.et_identity);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        etCity = findViewById(R.id.et_city);

        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_update_profile).setOnClickListener(this);

//        etIdentity.setText(SmartechHelper.getUserIdentity(this));
    }
}