package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.AspirasiFB;

public class User extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth Auth;
    private EditText eAspirasi, eJudul;
    private RadioButton rKesiswaan;
    private RadioButton rSarpra;
    private Button bOk;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Auth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        eJudul = (EditText) findViewById(R.id.editTextJudul);
        eAspirasi = (EditText) findViewById(R.id.editTextAspirasi);
        rKesiswaan = (RadioButton) findViewById(R.id.radiokesiswaan);
        rSarpra = (RadioButton) findViewById(R.id.radiosarpra);
        bOk = (Button) findViewById(R.id.buttonOK);
        pDialog = new ProgressDialog(this);

        bOk.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuadmin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Auth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void aspirasi() {
        String judul = eJudul.getText().toString().trim();
        String aspirasi = eAspirasi.getText().toString().trim();
        String kategori = rSarpra.isChecked() ? "sarpra" : "kesiswaan";

        if (TextUtils.isEmpty(aspirasi) || TextUtils.isEmpty(judul)) {
            Toast.makeText(this, "Tolong diisi", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //pDialog.setMessage("Mengirim...");
            //pDialog.show();
            tambahdata(judul, aspirasi, kategori);
            Toast.makeText(getApplicationContext(), "Aspirasi Anda berhasil dikirim", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        if (v == bOk) {
            aspirasi();
        }
    }

    public void tambahdata(String jd, String ds, String kt) {
        Firebase sender = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/");
        AspirasiFB aspfb = new AspirasiFB(jd, ds, kt);
        sender.child("admin").push().setValue(aspfb);
    }

}
