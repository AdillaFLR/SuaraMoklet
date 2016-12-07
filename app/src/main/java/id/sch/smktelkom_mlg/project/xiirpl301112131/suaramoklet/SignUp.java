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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth Auth;
    private EditText eEmail;
    private EditText ePass;
    private Button bSignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Auth = FirebaseAuth.getInstance();
        eEmail = (EditText) findViewById(R.id.editTextEmail);
        ePass = (EditText) findViewById(R.id.editTextPass);
        bSignup = (Button) findViewById(R.id.buttonSignUp);

        progressDialog = new ProgressDialog(this);


        Auth = FirebaseAuth.getInstance();
        if (Auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), AdminPanel.class));
        }

        bSignup.setOnClickListener(this);
    }

    private void userSignup() {
        final String email = eEmail.getText().toString().trim();
        String password = ePass.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signup...");
        progressDialog.show();


        Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Password atau Username salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == bSignup) {
            userSignup();
        }
    }
}
