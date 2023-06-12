package com.tamal.lcmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Spinner loginRole;
    EditText loginUserId;
    TextView loginRegister;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignUp = findViewById(R.id.siButtonSignin);
        loginRole = findViewById(R.id.spinnerLoginRole);
        loginUserId = findViewById(R.id.siLoginUserId);
        loginRegister = findViewById(R.id.tvRegister);

        Database db = new Database(getApplicationContext(), "user", null, 1);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liRole = loginRole.getSelectedItem().toString();
                String liUserId = loginUserId.getText().toString();
                Toast.makeText(getApplicationContext(), "User Role: " +
                        liRole + " User ID: " + liUserId, Toast.LENGTH_SHORT).show();

                if(liRole.length()==0 || liUserId.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All The Data Field.",Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(liRole, liUserId)==1){
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userId", liUserId);
                        editor.commit();
                        editor.apply();
                        //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong User Role or UserID..",Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}