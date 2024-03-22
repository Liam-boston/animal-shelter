package edu.psu.sweng888.animalshelter.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.splashscreen.SplashScreen;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.psu.sweng888.animalshelter.R;

public class Login extends Activity {
    // declare UI elements
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;
    private TextView createAccount;

    // declare FirebaseAuth
    private FirebaseAuth mAuth;

    // Intent extra values
    public static final String INTENT_EXTRA_DISPLAY_NAME = "user_display_name";
    public static final String INTENT_EXTRA_EMAIL_ADDRESS = "user_email_address";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // display SplashScreen for 3.5 seconds
        try {
            Thread.sleep(3500);
            SplashScreen.installSplashScreen(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // continue to LoginActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // initialize UI elements
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        createAccount = findViewById(R.id.createAccountRedirect);

        // initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // authenticates user credentials and navigates to the MainActivity on completion
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent mainViewIntent = new Intent(Login.this, MainActivity.class);

                                        // Get details from the logged-in user to create a user in our local database
                                        FirebaseUser user = authResult.getUser();
                                        mainViewIntent.putExtra(
                                                INTENT_EXTRA_DISPLAY_NAME,
                                                user == null ? "<Unknown>" : user.getDisplayName()
                                        );
                                        mainViewIntent.putExtra(
                                                INTENT_EXTRA_EMAIL_ADDRESS,
                                                user == null ? "<Unknown>" : user.getEmail()
                                        );
                                        startActivity(mainViewIntent);
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Enter password");
                    }

                } else if (email.isEmpty()) {
                    loginEmail.setError("Enter email");
                } else {
                    loginEmail.setError("Enter email");
                }
            }
        });

        // navigates to the SignUpActivity
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, CreateAccount.class));
            }
        });
    }
}