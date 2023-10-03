package com.kelompok3.plannyup.manajemen_db.model.tabel_view_count_user;

import com.google.gson.annotations.SerializedName;

public class AtributViewCountUser {
    @SerializedName("jumlah_data_user")
    private int jumlah_data_user;

    public AtributViewCountUser() {

    }

    public AtributViewCountUser(int jumlah_data_user) {
        this.jumlah_data_user = jumlah_data_user;
    }

    public int getJumlah_data_user() {
        return jumlah_data_user;
    }

    public void setJumlah_data_user(int jumlah_data_user) {
        this.jumlah_data_user = jumlah_data_user;
    }
}
