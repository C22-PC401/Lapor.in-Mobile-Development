package com.bangkit.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelDataInformasi {
    private String judulinformasi,tanggalinformasi,gambarinformasi,deskripsiinformasi;

    public ModelDataInformasi(){
    }

    public ModelDataInformasi(String judulinformasi, String tanggalinformasi, String gambarinformasi, String deskripsiinformasi) {
        this.judulinformasi = judulinformasi;
        this.tanggalinformasi = tanggalinformasi;
        this.gambarinformasi = gambarinformasi;
        this.deskripsiinformasi = deskripsiinformasi;
    }

    public String getJudulinformasi() {
        return judulinformasi;
    }

    public void setJudulinformasi(String judulinformasi) {
        this.judulinformasi = judulinformasi;
    }

    public String getTanggalinformasi() {
        return tanggalinformasi;
    }

    public void setTanggalinformasi(String tanggalinformasi) {
        this.tanggalinformasi = tanggalinformasi;
    }

    public String getGambarinformasi() {
        return gambarinformasi;
    }

    public void setGambarinformasi(String gambarinformasi) {
        this.gambarinformasi = gambarinformasi;
    }

    public String getDeskripsiinformasi() {
        return deskripsiinformasi;
    }

    public void setDeskripsiinformasi(String deskripsiinformasi) {
        this.deskripsiinformasi = deskripsiinformasi;
    }
}
