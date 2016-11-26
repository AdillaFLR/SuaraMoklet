package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.AdapterAdmin;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;

public class AdminPanel extends AppCompatActivity {
    Map<Integer,Aspirasi> mapias = new HashMap<Integer, Aspirasi>();
    ArrayList<Aspirasi> aspl = new ArrayList<Aspirasi>();
    AdapterAdmin mAdapter;
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        Firebase.setAndroidContext(this);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
refreshData();
            }
        });
        ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/admin");
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvAdmin);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter    = new AdapterAdmin(aspl,AdminPanel.this);
        rv.setAdapter(mAdapter);
        filldata();
        refreshData();
    }

    public void filldata() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()
                        ) {
                    String judul = "null";
                    String deskr = "null";
                    String kateg = "null";
                    String idx ="null";
                    if(null == ds.child("judul").getValue(String.class) || null ==ds.child("deskripsi").getValue(String.class)){

                    }else{
                     idx = ds.getKey();
                    judul = ds.child("judul").getValue(String.class);
                    deskr = ds.child("deskripsi").getValue(String.class);
                    kateg = ds.child("kategori").getValue(String.class);
aspl.clear();
 }

                    mapias.put(i,new Aspirasi(idx,judul,deskr,kateg));

i++;
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                    refreshData();
                    }
                }, 1554);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        refreshData();
    }

    public void refreshData() {
        for (int i = 0; i<=(mapias.size()-1); i++) {
            if(mapias.get(i)==null){

            }else{
                if(mapias.get(i).getAlready()){
                    }else{
                    aspl.add(mapias.get(i));
                    mapias.get(i).setAlready(true);
                }
            }

        }
        mAdapter.notifyDataSetChanged();
    }

}