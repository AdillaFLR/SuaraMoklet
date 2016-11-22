package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Query;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.SarpraAspirasi;


/**
 * Created by SMK Telkom SP Malang on 22-Nov-16.
 */
public class SarpraAdapter extends FireBaseSarpraAdapter<SarpraAdapter.ViewHolder, SarpraAspirasi> {

    public SarpraAdapter(Query mQuery, Class<SarpraAspirasi> itemClass, ArrayList<SarpraAspirasi> mAdapterItems, ArrayList<String> mAdapterKeys) {
        super(mQuery, itemClass, mAdapterItems, mAdapterKeys);
    }

    @Override
    public SarpraAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guru_item_sarpra, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SarpraAdapter.ViewHolder holder, int position) {
        SarpraAspirasi item = getItem(position);
        holder.textViewJudul.setText(item.getJudul());
        holder.textViewDeskripsi.setText(item.getDeskripsi());
    }


    @Override
    protected void itemAdded(SarpraAspirasi item, String key, int position) {

    }

    @Override
    protected void itemChanged(SarpraAspirasi oldItem, SarpraAspirasi newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(SarpraAspirasi item, String key, int position) {

    }

    @Override
    protected void itemMoved(SarpraAspirasi item, String key, int oldPosition, int newPosition) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewJudul;
        TextView textViewDeskripsi;

        public ViewHolder(View view) {
            super(view);
            textViewJudul = (TextView) view.findViewById(R.id.textview_judulguru2);
            textViewDeskripsi = (TextView) view.findViewById(R.id.textview_deskripsiguru2);
        }
    }

}

