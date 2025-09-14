package com.example.healthcareappmajorproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcareappmajorproject.Database.Database;

public class LoginAcitivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextViewPassword;
    Button loginButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the objects of all the controls or UI elements that we want to use to do our logics
        editTextUserName = findViewById(R.id.userNameEditView);
        editTextViewPassword = findViewById(R.id.passwordEditView);
        loginButton = findViewById(R.id.loginButton);
        textView = findViewById(R.id.registerNewUserTextView);
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);


        /**
         *
         * Add the event handler for logging in to the application
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUserName = editTextUserName.getText().toString();
                String enteredPassword = editTextViewPassword.getText().toString();
                if(enteredUserName.isEmpty() || enteredPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int isLogin = db.login(enteredUserName, enteredPassword);
                    if(isLogin == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                        // now we will save the user information in the shared preference itself
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", enteredUserName);
                        editor.apply();

                        // redirect the user to the home page for this purpose
                        Intent redirectToHomeIntent = new Intent(LoginAcitivity.this, HomeActivity.class);
                        startActivity(redirectToHomeIntent);

                        // once the user has redirected to the home page we do not need
                        // to come back again to the login activity we can simply
                        // exit the application.
                        finish();
                    }
                }
            }
        });

        // Event handler on textview to navigate to the register activity
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we need to redirect to the register new user activity
                // we will be using the concept of intent here in this case
                Intent registerIntent = new Intent(LoginAcitivity.this, RegisterAcitivity.class);
                startActivity(registerIntent);

                // note that we will not call the finish() which means that this activity will not
                // be killed and it will be alive in the background. When user presses the back button
                // it will end up landing to login activity again

            }
        });




    }
}