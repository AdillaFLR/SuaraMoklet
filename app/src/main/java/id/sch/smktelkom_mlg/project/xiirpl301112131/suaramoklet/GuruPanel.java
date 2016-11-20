package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintTypedArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.AdminAdapter;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter.GuruAdapter;

/**
 * Created by SMK Telkom SP Malang on 20-Nov-16.
 */
public class GuruPanel {
    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private com.firebase.client.Query mQuery;
    private GuruAdapter mGuruAdapter;
    private ArrayList<GuruAspirasi> mGuruAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        handleInstanceState(savedInstanceState);
        setupFirebase();
        setupRecyclerview();
        Button btn = (Button) findViewById(R.id.buttonRefreshGuru);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuruAdapter.notifyDataSetChanged();
            }
        });
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mGuruAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mGuruAdapterItems = new ArrayList<GuruAspirasi>();
            mAdapterKeys = new ArrayList<String>();
        }
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        String firebaseLocation = getResources().getString(R.string.firebase_location);
        Firebase ref = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/guru");
        mQuery = ref.orderByChild("judul");

    }

    private TintTypedArray getResources() {
        return null;
    }

    private void setupRecyclerview() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAdmin);
        mGuruAdapter = new AdminAdapter(mQuery, Aspirasi.class, mAdapterItems, mAdapterKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mGuruAdapter);
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
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mGuruAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mGuruAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGuruAdapter.destroy();
    }
}

