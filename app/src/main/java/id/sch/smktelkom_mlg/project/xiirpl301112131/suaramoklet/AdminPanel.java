package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.firebase.client.Firebase;


import org.parceler.Parcels;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.AdminAdapter;

public class AdminPanel extends AppCompatActivity {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private com.firebase.client.Query mQuery;
    private AdminAdapter mAdminAdapter;
    private ArrayList<Aspirasi> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        handleInstanceState(savedInstanceState);
        setupFirebase();
        setupRecyclerview();
        Button btn = (Button)findViewById(R.id.buttonRefresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdminAdapter.notifyDataSetChanged();
            }
        });
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<Aspirasi>();
            mAdapterKeys = new ArrayList<String>();
        }
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        String firebaseLocation = getResources().getString(R.string.firebase_location);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/admin");
        mQuery = ref.orderByChild("judul");

    }

     private void setupRecyclerview() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAdmin);
        mAdminAdapter = new AdminAdapter(mQuery, Aspirasi.class, mAdapterItems, mAdapterKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdminAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    // Saving the list of items and keys of the items on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mAdminAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mAdminAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdminAdapter.destroy();
    }
}

//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//
//import java.util.ArrayList;
//
//public class AdminPanel extends AppCompatActivity
//    implements ItemAddedHandler {
//        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/admin/id/deskripsi")
//    //data
//       ArrayList<String> Items = new ArrayList<>();
//       ArrayAdapter adapter;
//    //UI
//       RecyclerView RecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_panel);
////        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//
//        RecyclerView = (RecyclerView) findViewById(R.id.recyclerViewAdmin);
//        RecyclerView.setHasFixedSize(true);
//        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        //floatingactionbutton
////        Button fab = (FloatingActionButton) findViewById(R.id);
//    }
////    void onItemAdded(String item) {ref.push().setValue(item); }
////
////
////        ted void onStart() {
////        per.onStart();
//
//        FirebaseRecyclerAdapter<String,MessageViewHolder> adapter =
//                new FirebaseRecyclerAdapter<String,MessageViewHolder>(
//                        String.class,
//                        android.R.layout.two_line_list_item,
//                        MessageViewHolder.class,
//                        ref
//                )
//                {
//                    @Override
//                     protected void populateViewHolder(MessageViewHolder viewHolder, String model, int position) {
//                    messageViewHolder.mText.setText(s);
//                    }
//                };
//    };
//
//            @Override
//            public void onItemAdded(String item) { ref.push().setValue(item);
//         }
//            @Override
//            protected void onStart() {
//            super.onStart();
//        }
//
//    public static class MessageViewHolder
//            extends RecyclerView.ViewHolder {
//            TextView mText;
//
//
//        public MessageViewHolder(View v){
//                super(v);
//            mText =  (TextView) v.findViewById(android.R.id.text1);
//         }
//        }
//    }