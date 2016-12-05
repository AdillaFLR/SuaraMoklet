package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.GuruAspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;

/**
 * Created by SMK Telkom SP Malang on 22-Nov-16.
 */
public class AdapterAdminGuru extends RecyclerView.Adapter<AdapterAdminGuru.ViewHolder> {
    ArrayList<GuruAspirasi> asplist;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guru_item, parent, false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GuruAspirasi asp = asplist.get(position);
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
            tvJudul = (TextView) itemView.findViewById(R.id.textview_judulguru);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textview_deskripsiguru);

        }
    }
}