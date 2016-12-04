package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet.model;

/**
 * Created by nabila on 11/26/2016.
 */
public class AspirasiFB {
    String judul,deskripsi,kategori;
    public AspirasiFB(String judul,String deskripsi,String kategori) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKategori() {
        return kategori;
    }
}
