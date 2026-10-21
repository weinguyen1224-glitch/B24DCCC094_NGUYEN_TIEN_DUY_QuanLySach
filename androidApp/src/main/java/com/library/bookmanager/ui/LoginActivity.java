package com.library.bookmanager.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.library.bookmanager.R;

public class LoginActivity extends Activity {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView errorLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        errorLabel = findViewById(R.id.tvError);
        findViewById(R.id.btnLogin).setOnClickListener(view -> login());
    }

    private void login() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        if (!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            errorLabel.setText(username.isEmpty() || password.isEmpty() ? R.string.login_blank : R.string.login_error);
            errorLabel.setVisibility(View.VISIBLE);
            return;
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
