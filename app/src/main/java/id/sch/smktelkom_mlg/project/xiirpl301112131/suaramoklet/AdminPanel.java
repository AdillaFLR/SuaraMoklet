package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdminPanel extends AppCompatActivity {
    TextView tampiljudul,tampildesk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        tampiljudul = (TextView) findViewById(R.id.judul);
        tampildesk = (TextView) findViewById(R.id.deskripsi);

        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan/ak01/judul");
        Firebase ref2 = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan/ak01/deskripsi");
                    ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tampiljudul.setText(dataSnapshot.getValue().toString());
                }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
       ref2.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot ds) {
            tampildesk.setText(ds.getValue().toString());
           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {

           }
       });
    }
}
