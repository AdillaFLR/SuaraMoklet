package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.User;

/**
 * Created by Asus X450 on 11/20/2016.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button bLogin;
    private Button bSignup;
    private EditText editTextEmail;
    private EditText editTextPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.buttonLogin);
        bSignup = (Button) findViewById(R.id.buttonSignUp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);

        progressDialog = new ProgressDialog(this);


        Auth = FirebaseAuth.getInstance();
        if (Auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), User.class));
        }

        bLogin.setOnClickListener(this);
        bSignup.setOnClickListener(this);
    }


    private void userLogin() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login User...");
        progressDialog.show();
        //end validasi form OK

        //proses login, pencocokan data dengan firebase
        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            if (email.contains("@student")) {
                                startActivity(new Intent(getApplicationContext(), id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.User.class));
                            } else if (email.contains("@smk")) {
                                startActivity(new Intent(getApplicationContext(), Choose.class));
                            } else {
                                startActivity(new Intent(getApplicationContext(), AdminPanel.class));
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Password atau Username salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == bLogin) {
            userLogin();
        } else if (view == bSignup) {
            startActivity(new Intent(getBaseContext(), SignUp.class));
        }

    }
}

