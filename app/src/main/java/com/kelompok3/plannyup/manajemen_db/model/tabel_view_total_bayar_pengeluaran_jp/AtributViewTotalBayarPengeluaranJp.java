package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_bayar_pengeluaran_jp;

import com.google.gson.annotations.SerializedName;

public class AtributViewTotalBayarPengeluaranJp {
    @SerializedName("total_jp_bayar")
    private int total_jp_bayar;

    public AtributViewTotalBayarPengeluaranJp() {
    }

    public AtributViewTotalBayarPengeluaranJp(int total_jp_bayar) {
        this.total_jp_bayar = total_jp_bayar;
    }

    public int getTotal_jp_bayar() {
        return total_jp_bayar;
    }

    public void setTotal_jp_bayar(int total_jp_bayar) {
        this.total_jp_bayar = total_jp_bayar;
    }
}
