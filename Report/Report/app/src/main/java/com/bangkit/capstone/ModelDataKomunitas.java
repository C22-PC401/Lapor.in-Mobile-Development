package com.bangkit.capstone;

public class ModelDataKomunitas {
    private String namagroup,icongroup;

    public ModelDataKomunitas() {
    }

    public ModelDataKomunitas(String namagroup, String icongroup) {
        this.namagroup = namagroup;
        this.icongroup = icongroup;
    }

    public String getNamagroup() {
        return namagroup;
    }

    public void setNamagroup(String namagroup) {
        this.namagroup = namagroup;
    }

    public String getIcongroup() {
        return icongroup;
    }

    public void setIcongroup(String icongroup) {
        this.icongroup = icongroup;
    }
}