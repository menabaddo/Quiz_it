package com.example.quizit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button loginB;
    private TextView forgotPassB, signupB;
    private FirebaseAuth mAuth;
    private Dialog progressDialog;
    private TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginB = findViewById(R.id.loginButton);
        forgotPassB = findViewById(R.id.forgot_pass);
        signupB = findViewById(R.id.signupButton);


        progressDialog = new Dialog(LoginActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = progressDialog.findViewById(R.id.dialogText);
        dialogText.setText("Signing in");


        mAuth = FirebaseAuth.getInstance();

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateData()){
                    login();
                }
            }
        });


        signupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateData(){
        boolean staus = false;

        if(email.getText().toString().isEmpty()){

            email.setError("Enter E-Mail ID");
            return  false;
        }


        if(pass.getText().toString().isEmpty()){
            pass.setError("Enter Password");
            return  false;
        }
        return true;
    }

    private  void login(){

        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                          progressDialog.dismiss();
                          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                          startActivity(intent);
                          finish();
                        } else {
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}