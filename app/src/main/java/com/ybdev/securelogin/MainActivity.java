package com.ybdev.securelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText MainActivity_LBL_password;
    private MaterialButton MainActivity_BTN_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        loginObserver();

    }

    private void loginObserver() {
        MainActivity_BTN_login.setOnClickListener(view -> checkCredentials());
    }

    private void checkCredentials() {
        try {
            CredentialsChecker credentialsChecker = new CredentialsChecker(this);
            if (credentialsChecker.checkAccess(MainActivity_LBL_password.getText().toString()))
                Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"Wrong Password",Toast.LENGTH_LONG).show();


        }catch (NullPointerException | StringIndexOutOfBoundsException e){
            Toast.makeText(this,"Invalid Password",Toast.LENGTH_LONG).show();
        }
    }


    private void findViews() {
        MainActivity_LBL_password = findViewById(R.id.MainActivity_LBL_password);
        MainActivity_BTN_login = findViewById(R.id.MainActivity_BTN_login);
    }
}