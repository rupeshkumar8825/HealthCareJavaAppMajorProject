package com.example.healthcareappmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterAcitivity extends AppCompatActivity {

    EditText userNameEditText;
    EditText passwordEditText;
    EditText emailEditText;
    EditText confirmPasswordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameEditText = findViewById(R.id.userNameEditView);
        passwordEditText = findViewById(R.id.passwordEditView);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        emailEditText = findViewById(R.id.emailTextView);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we have to take the values and then need to check what to do with these values
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                Log.i("Register", String.format("The value of confirm password is %s, " +
                        "and the value of password is %s", confirmPassword, password));

                if(username.isEmpty() || email.isEmpty() || password.isEmpty()
                    || confirmPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!confirmPassword.equals(password))
                    {
                        Toast.makeText(getApplicationContext(), String.format("Confirm password is %s" +
                                "and password is %s", confirmPassword, password), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // we need to insert this data into the database
                        //otherwise this means that the user has entered the correct credentials
                        Toast.makeText(getApplicationContext(), "Registeration Successfully done", Toast.LENGTH_SHORT).show();
                        // now we will redirect to the login activity
                        Intent redirectToLoginIntent = new Intent(RegisterAcitivity.this, LoginAcitivity.class);
                        startActivity(redirectToLoginIntent);
                    }
                }
            }
        });




    }



}