package com.app.walk2earn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText sEmailField;
    private EditText sPasswordField,sPasswordField2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sEmailField = findViewById(R.id.fieldEmail);
        sPasswordField = findViewById(R.id.fieldPassword);
        sPasswordField2=findViewById(R.id.fieldPassword2);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    private void createAccount(String email, String password) {
        //Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUp.this, "Success",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            //updateUI(user);
                        } else {

                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = sEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            sEmailField.setError("Email is Mandatory.");
            valid = false;
        } else {
            sEmailField.setError(null);
        }

        String password = sPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            sPasswordField.setError("Please enter your Password.");
            valid = false;
        } else {
            sPasswordField.setError(null);
        }
        String ConfirmPassword =sPasswordField2.getText().toString();
        if(!ConfirmPassword.equals(password)){
            sPasswordField2.setError("Password Mismatch");
            valid=false;
        }
        return valid;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(sEmailField.getText().toString(), sPasswordField.getText().toString());
        }
    }
}
