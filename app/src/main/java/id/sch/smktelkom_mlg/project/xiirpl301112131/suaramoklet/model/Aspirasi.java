package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model;

import com.firebase.client.Firebase;

import org.parceler.Parcel;

/**
 * Created by nabila on 11/20/2016.
 */
@Parcel
public class Aspirasi {
        String id;
        String judul;
        String deskripsi;
        String kategori;
        boolean already;

        public Aspirasi(String id,String judul,String deskripsi,String kategori) {
        setAlready(false);
        this.judul = judul;
        this.deskripsi = deskripsi;
            this.kategori = kategori;
            this.id=id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Aspirasi getThis(){
        return Aspirasi.this;
    }
    public String getKategori() {
            return kategori;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }
        public void delete(){
            Firebase deletor = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/admin/"+id);
            deletor.setValue(null);
        }
    public void kirim(Aspirasi asp){
        Firebase sender = new Firebase("https://suaramoklet.firebaseio.com/aspirasi/");
        AspirasiFB aspfb = new AspirasiFB(asp.getJudul(),asp.getDeskripsi(),asp.getKategori());
        sender.child(kategori).push().setValue(aspfb);
    }
    }

