package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.SarpraPanel;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;


/**
 * Created by SMK Telkom SP Malang on 22-Nov-16.
 */
public class SarpraAdapter extends RecyclerView.Adapter<SarpraAdapter.ViewHolder> {
    ArrayList<Aspirasi> asplist;
    SarpraPanel adp;

    public SarpraAdapter(ArrayList<Aspirasi> asp, SarpraPanel adp) {
        this.asplist = asp;
        this.adp = adp;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guru_item_sarpra, parent, false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Aspirasi asp = asplist.get(position);
        holder.tvJudul.setText(asp.getJudul());
        holder.tvDeskripsi.setText(asp.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        if (asplist != null)
            return asplist.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textview_judulguru2);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textview_deskripsiguru2);
        }
    }
}
