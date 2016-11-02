package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

public class AdminPanel extends AppCompatActivity {
    TextView mbarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        mbarang = (TextView) findViewById(R.id.coba);
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan/ak01/judul");

                    ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mbarang.setText(dataSnapshot.getValue().toString());
                }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
