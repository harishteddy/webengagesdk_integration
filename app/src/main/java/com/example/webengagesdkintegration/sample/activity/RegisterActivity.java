package com.example.webengagesdkintegration.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webengagesdkintegration.R;
import com.example.webengagesdkintegration.sample.utils.Utility;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etIdentity, etFirstName, etLastName, etPhone, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear: {
                EditText[] inputs = {etIdentity, etFirstName, etLastName, etPhone, etCity};
                Utility.clearInputs(inputs);
            }
            break;

            case R.id.btn_register: {
                EditText[] inputs = {etIdentity, etFirstName, etLastName, etPhone, etCity};
                if (!Utility.areInputsEmpty(inputs)) {
                    Toast.makeText(this, R.string.registration_successful, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, R.string.all_fields_are_required, Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private void init() {
        etIdentity = findViewById(R.id.et_identity);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        etCity = findViewById(R.id.et_city);

        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }
}