package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class User extends AppCompatActivity {
    TextView tampiljudul, tampildesk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RadioButton radioK, radioS;
        TextView tvHasil;


        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan/ak01/judul");
        Firebase ref2 = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan/ak01/deskripsi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                tampiljudul.setText(ds.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        radioK = (RadioButton) findViewById(R.id.radiokesiswaan);
        radioS = (RadioButton) findViewById(R.id.radiosarpra);

        //tvHasil = (TextView) findViewById(R.id.editText);

    }
}