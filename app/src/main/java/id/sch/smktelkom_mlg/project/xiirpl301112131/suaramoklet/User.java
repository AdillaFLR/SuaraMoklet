package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.AspirasiFB;

public class User extends AppCompatActivity implements View.OnClickListener {

    private EditText eAspirasi, eJudul;
    private RadioButton rKesiswaan, rSarpra;
    private Button bOk;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Firebase.setAndroidContext(this);
        eJudul = (EditText) findViewById(R.id.editTextJudul);
        eAspirasi = (EditText) findViewById(R.id.editTextAspirasi);
        rKesiswaan = (RadioButton) findViewById(R.id.radiokesiswaan);
        rSarpra = (RadioButton) findViewById(R.id.radiosarpra);
        bOk = (Button) findViewById(R.id.buttonOK);

        pDialog = new ProgressDialog(this);

        bOk.setOnClickListener(this);

    }


    private void aspirasi() {
        String aspirasi = eAspirasi.getText().toString().trim();
        String judul = eJudul.getText().toString().trim();
        String kategori = rSarpra.isChecked() ? "sarpra" : "kesiswaan";

        if (TextUtils.isEmpty(aspirasi) || TextUtils.isEmpty(judul)) {
            Toast.makeText(this, "Tolong diisi", Toast.LENGTH_SHORT).show();
            return;
        } else {
            pDialog.setMessage("Mengirim...");
            pDialog.show();
            tambahdata(judul, aspirasi, kategori);
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
