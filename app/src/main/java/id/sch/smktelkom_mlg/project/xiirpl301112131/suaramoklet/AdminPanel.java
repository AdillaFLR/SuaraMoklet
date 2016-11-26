package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.AdapterAdmin;

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
        mAdapter    = new AdapterAdmin(aspl);
        rv.setAdapter(mAdapter);
        filldata();
        refreshData();
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()
//                     ) {
//                    Aspirasi asp = ds.getValue(Aspirasi.class);
//                    aspl.add(asp);
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }

//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

    }

    private void filldata() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()
                        ) {
                    String judul = "null";
                    String deskr = "null";
                    if(null == ds.child("judul").getValue(String.class) || null ==ds.child("deskripsi").getValue(String.class)){

                    }else{
                    judul = ds.child("judul").getValue(String.class);
                    deskr = ds.child("deskripsi").getValue(String.class);
aspl.clear();
 }

                    mapias.put(i,new Aspirasi(judul,deskr));

i++;
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        refreshData();
    }

    private void refreshData() {
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