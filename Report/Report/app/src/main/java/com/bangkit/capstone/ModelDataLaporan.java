package com.bangkit.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelDataLaporan {
    private String nama, tanggallahir, jeniskelamin, nomortelepon, alamat, tanggalkejadian, jenispelecehan, lokasikejadian, deskripsipelecehan, buktipendukung, buktigambar;

    public ModelDataLaporan() {
    }

    public ModelDataLaporan(String nama, String tanggallahir, String jeniskelamin, String nomortelepon, String alamat, String tanggalkejadian, String jenispelecehan, String lokasikejadian, String deskripsipelecehan, String buktipendukung, String buktigambar) {
        this.nama = nama;
        this.tanggallahir = tanggallahir;
        this.jeniskelamin = jeniskelamin;
        this.nomortelepon = nomortelepon;
        this.alamat = alamat;
        this.tanggalkejadian = tanggalkejadian;
        this.jenispelecehan = jenispelecehan;
        this.lokasikejadian = lokasikejadian;
        this.deskripsipelecehan = deskripsipelecehan;
        this.buktipendukung = buktipendukung;
        this.buktigambar = buktigambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggallahir() {
        return tanggallahir;
    }

    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getNomortelepon() {
        return nomortelepon;
    }

    public void setNomortelepon(String nomortelepon) {
        this.nomortelepon = nomortelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalkejadian() {
        return tanggalkejadian;
    }

    public void setTanggalkejadian(String tanggalkejadian) {
        this.tanggalkejadian = tanggalkejadian;
    }

    public String getJenispelecehan() {
        return jenispelecehan;
    }

    public void setJenispelecehan(String jenispelecehan) {
        this.jenispelecehan = jenispelecehan;
    }

    public String getLokasikejadian() {
        return lokasikejadian;
    }

    public void setLokasikejadian(String lokasikejadian) {
        this.lokasikejadian = lokasikejadian;
    }

    public String getDeskripsipelecehan() {
        return deskripsipelecehan;
    }

    public void setDeskripsipelecehan(String deskripsipelecehan) {
        this.deskripsipelecehan = deskripsipelecehan;
    }

    public String getBuktipendukung() {
        return buktipendukung;
    }

    public void setBuktipendukung(String buktipendukung) {
        this.buktipendukung = buktipendukung;
    }

    public String getBuktigambar() {
        return buktigambar;
    }

    public void setBuktigambar(String buktigambar) {
        this.buktigambar = buktigambar;
    }
}