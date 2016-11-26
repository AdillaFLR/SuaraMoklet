package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.Aspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;

/**
 * Created by nabila on 11/22/2016.
 */
public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.ViewHolder> {
    ArrayList<Aspirasi> asplist;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aspirasi_item,parent,false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Aspirasi asp = asplist.get(position);
        holder.tvJudul.setText(asp.getJudul());
        holder.tvDeskripsi.setText(asp.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        if(asplist!=null)
            return asplist.size();
        return 0;
    }
    public AdapterAdmin(ArrayList<Aspirasi> asp){
        this.asplist = asp;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tvDeskripsi;
        ImageButton ibHapus,ibKirim;
        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textview_judul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textview_deskripsi);

        }
    }
}
