package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import org.parceler.Parcel;

/**
 * Created by nabila on 11/20/2016.
 */
@Parcel
public class Aspirasi {
        String judul;
        String deskripsi;
        boolean already;
        public Aspirasi(String judul,String deskripsi) {
        setAlready(false);
        this.judul = judul;
        this.deskripsi = deskripsi;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public boolean getAlready(){return this.already    ;}

        public void setAlready(boolean already){this.already = already;}
    }

