package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.AspirasiFB;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    Button bOk;
    RadioGroup rgStatus;
    TextView tvHasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        initializefirebase();
        etNama = (EditText) findViewById(R.id.editTextAspirasi);
        bOk = (Button) findViewById(R.id.buttonOK);

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase sender = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/");
                AspirasiFB aspfb = new AspirasiFB("judul", "deskripsi", "kategori");
                sender.child("admin").push().setValue(aspfb);
            }
        });

    }

    private void initializefirebase() {
        Firebase.setAndroidContext(this);
    }
}
