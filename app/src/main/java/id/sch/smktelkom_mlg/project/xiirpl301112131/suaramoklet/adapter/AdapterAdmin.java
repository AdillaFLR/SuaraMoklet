package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.adapter;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.AdminPanel;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model.Aspirasi;
import id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.R;

/**
 * Created by nabila on 11/22/2016.
 */
public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.ViewHolder> {
    ArrayList<Aspirasi> asplist;
    AdminPanel adp;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aspirasi_item,parent,false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Aspirasi asp = asplist.get(position);
        holder.tvJudul.setText(asp.getJudul());
        holder.tvDeskripsi.setText(asp.getDeskripsi());
        holder.tvKategori.setText(asp.getKategori());
        holder.ibHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asp.delete();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        adp.refreshData();
                    }
                }, 1554);

            }
        });

        holder.ibKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asp.kirim(asp);
                asp.delete();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        adp.refreshData();
                    }
                }, 1554);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(asplist!=null)
            return asplist.size();
        return 0;
    }
    public AdapterAdmin(ArrayList<Aspirasi> asp, AdminPanel adp){
        this.asplist = asp;
this.adp = adp;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tvDeskripsi,tvKategori;
        ImageButton ibHapus,ibKirim;
        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textview_judul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textview_deskripsi);
             tvKategori  = (TextView) itemView.findViewById(R.id.textview_kategory);
            ibHapus =(ImageButton) itemView.findViewById(R.id.imageButtonHapus  );
            ibKirim = (ImageButton) itemView.findViewById(R.id.imageButtonKirim);
        }
    }
}
