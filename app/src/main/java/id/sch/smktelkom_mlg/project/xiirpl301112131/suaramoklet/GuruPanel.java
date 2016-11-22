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

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.GuruAdapter;

/**
 * Created by SMK Telkom SP Malang on 20-Nov-16.
 */
public class GuruPanel extends AppCompatActivity {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private com.firebase.client.Query mQuery;
    private GuruAdapter mAdminAdapter;
    private ArrayList<GuruAspirasi> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guru_recycler);

        handleInstanceState(savedInstanceState);
        setupFirebase();
        setupRecyclerview();
        Button btn = (Button) findViewById(R.id.buttonRefreshGuru);
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
            mAdapterItems = new ArrayList<GuruAspirasi>();
            mAdapterKeys = new ArrayList<String>();
        }
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        String firebaseLocation = getResources().getString(R.string.firebase_location);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/kesiswaan");
        mQuery = ref.orderByChild("judul");

    }

    private void setupRecyclerview() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGuru);
        mAdminAdapter = new GuruAdapter(mQuery, GuruAspirasi.class, mAdapterItems, mAdapterKeys);
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
