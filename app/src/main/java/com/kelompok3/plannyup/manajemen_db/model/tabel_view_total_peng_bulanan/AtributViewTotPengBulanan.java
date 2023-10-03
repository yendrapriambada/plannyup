package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_bulanan;

import com.google.gson.annotations.SerializedName;

public class AtributViewTotPengBulanan {
    @SerializedName("total_pengeluaran_bulanan")
    private int total_pengeluaran_bulanan;

    public AtributViewTotPengBulanan() {
    }

    public AtributViewTotPengBulanan(int total_pengeluaran_bulanan) {
        this.total_pengeluaran_bulanan = total_pengeluaran_bulanan;
    }

    public int getTotal_pengeluaran_bulanan() {
        return total_pengeluaran_bulanan;
    }

    public void setTotal_pengeluaran_bulanan(int total_pengeluaran_bulanan) {
        this.total_pengeluaran_bulanan = total_pengeluaran_bulanan;
    }
}
