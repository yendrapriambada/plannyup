package com.kelompok3.plannyup.manajemen_db.model.tabel_view_sisa_saldo;

import com.google.gson.annotations.SerializedName;

public class AtributViewSisaSaldo {
    @SerializedName("sisa_saldo")
    private int sisa_saldo;

    public AtributViewSisaSaldo() {
    }

    public AtributViewSisaSaldo(int sisa_saldo) {
        this.sisa_saldo = sisa_saldo;
    }

    public int getSisa_saldo() {
        return sisa_saldo;
    }

    public void setSisa_saldo(int sisa_saldo) {
        this.sisa_saldo = sisa_saldo;
    }
}
