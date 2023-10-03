package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_jp;

import com.google.gson.annotations.SerializedName;

public class AtributViewTotPengJp {
    @SerializedName("total_pengeluaran_jp")
    private int total_pengeluaran_jp;

    public AtributViewTotPengJp() {
    }

    public AtributViewTotPengJp(int total_pengeluaran_jp) {
        this.total_pengeluaran_jp = total_pengeluaran_jp;
    }

    public int getTotal_pengeluaran_jp() {
        return total_pengeluaran_jp;
    }

    public void setTotal_pengeluaran_jp(int total_pengeluaran_jp) {
        this.total_pengeluaran_jp = total_pengeluaran_jp;
    }
}
