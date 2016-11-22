package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;

/**
 * Created by asus on 23/11/2016.
 */
public class User extends AppCompatActivity {
    EditText etNama;
    Button bOk;
    RadioGroup rgStatus;
    TextView tvHasil;

    findfindViewById(R.id.rgStatus);

    ViewById(R.id.hasil);

    findViewById(R.id.buttonOK)rgStatus

    =(RadioGroup)

    setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view)
        {
            doClick();
        }


        tvHasil = (TextView)
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);

            etNama = (EditText) etNama.findViewById(R.id.editTextaspirasi);
            bOk = (Button) bOk.findViewById(R.id.buttonOK);
            tvHasil = (TextView) tvHasil.findViewById(R.id.hasil);


        private void doProcess () {
            String nama = null;
            if (isValid())

                nama = etNama.getText().toString();
            tvHasil.setText(nama);
        }.
        private boolean isValid ()
        {
            boolean valid = true;

            String nama = etNama.getText().toString();

            if (nama.isEmpty()) {
                etNama.setError("Aspirasi belum diisi");
                valid = false;
            } else if (nama.length() < 3) {
                etNama.setError("Aspirasi minimal 3 karakter");
                valid = false;
            } else {
                etNama.setError(null);
            }
            return false;
        }
        private void doClick ()
        {
            String hasil = null;

            if (rgStatus.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgStatus.getCheckedRadioButtonId());
                hasil = rb.getText().toString();

                if (rgStatus.getCheckedRadioButtonId() != R.id.radiokesiswaan) {
                    EditText etJA = (EditText) findViewById(R.id.editTextJA);
                    hasil += "\nJumlah Anak : " + etJA.getText();
                }
            }
            if (hasil == null) {
                tvHasil.setText("Belum Memilih Kategori");
            } else {
                tvHasil.setText(hasil);
            }
        }
        })
        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonBM) {
                    findViewById(R.id.tilJA).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.tilJA).setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
