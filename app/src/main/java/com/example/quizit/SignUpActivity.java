package com.example.quizit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText name, email, pass, confirmPass;
    private Button signUpB;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr, passStr, confirmPassStr, nameStr;
    private Dialog progressDialog;
    private TextView dialogText;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.username);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        signUpB = findViewById(R.id.signUpB);
        backB = findViewById(R.id.backButton);


        progressDialog = new Dialog(SignUpActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = progressDialog.findViewById(R.id.dialogText2);
        dialogText.setText("Registering User...");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    signupNewUser();
                }

            }
        });

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }


    private void signupNewUser() {
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "Successful Welcome To Quizit.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            SignUpActivity.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private Boolean validate() {

        nameStr = name.getText().toString().trim();
        passStr = pass.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        confirmPassStr = confirmPass.getText().toString().trim();


        if (nameStr.isEmpty()) {
            name.setError("Enter Your Name");
            return false;
        }

        if (emailStr.isEmpty()) {
            email.setError("Enter Your Name");
            return false;


        }


        if (passStr.isEmpty()) {
            pass.setError("Enter Your Name");
            return false;
        }

        if (confirmPassStr.isEmpty()) {
            confirmPass.setError("Enter Your Name");
            return false;
        }

        if (passStr.compareTo(confirmPassStr) != 0) {

            Toast.makeText(SignUpActivity.this, "Password and Confirm Password Should be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}