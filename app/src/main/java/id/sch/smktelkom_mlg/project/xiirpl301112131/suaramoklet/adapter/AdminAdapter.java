package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.firebase.client.Query;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;

/**
 * Created by nabila on 11/20/2016.
 */
public class AdminAdapter  extends FirebaseRecyclerAdapter<AdminAdapter.ViewHolder, Aspirasi> {



    public AdminAdapter(Query mQuery, Class<Aspirasi> itemClass, ArrayList<Aspirasi> mAdapterItems, ArrayList<String> mAdapterKeys) {
        super(mQuery, itemClass, mAdapterItems, mAdapterKeys);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView textViewJudul;
            TextView textViewDeskripsi;

            public ViewHolder(View view) {
                super(view);
                textViewJudul = (TextView) view.findViewById(R.id.textview_judul);
                textViewDeskripsi = (TextView) view.findViewById(R.id.textview_deskripsi);
            }
        }


        @Override
        public AdminAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.aspirasi_item, parent, false);

            return new ViewHolder(view);
        }

    @Override
        public void onBindViewHolder(AdminAdapter.ViewHolder holder, int position) {
            Aspirasi item = getItem(position);
            holder.textViewJudul.setText(item.getJudul());
            holder.textViewDeskripsi.setText(item.getDeskripsi());
        }

    @Override
    protected void itemAdded(Aspirasi item, String key, int position) {

    }

    @Override
    protected void itemChanged(Aspirasi oldItem, Aspirasi newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Aspirasi item, String key, int position) {

    }

    @Override
    protected void itemMoved(Aspirasi item, String key, int oldPosition, int newPosition) {

    }

}


