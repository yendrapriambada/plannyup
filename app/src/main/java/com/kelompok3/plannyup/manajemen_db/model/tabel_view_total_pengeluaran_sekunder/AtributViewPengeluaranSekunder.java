package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_sekunder;

import com.google.gson.annotations.SerializedName;

public class AtributViewPengeluaranSekunder {
    @SerializedName("pengeluaran_bulanan_sekunder")
    private int pengeluaran_bulanan_sekunder;

    public AtributViewPengeluaranSekunder() {
    }

    public AtributViewPengeluaranSekunder(int pengeluaran_bulanan_sekunder) {
        this.pengeluaran_bulanan_sekunder = pengeluaran_bulanan_sekunder;
    }

    public int getPengeluaran_bulanan_sekunder() {
        return pengeluaran_bulanan_sekunder;
    }

    public void setPengeluaran_bulanan_sekunder(int pengeluaran_bulanan_sekunder) {
        this.pengeluaran_bulanan_sekunder = pengeluaran_bulanan_sekunder;
    }
}
