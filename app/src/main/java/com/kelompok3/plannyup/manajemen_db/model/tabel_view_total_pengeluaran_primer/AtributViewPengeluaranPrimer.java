package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_primer;

import com.google.gson.annotations.SerializedName;

public class AtributViewPengeluaranPrimer {
    @SerializedName("Pengeluaran_Bulanan_primer")
    private int Pengeluaran_Bulanan_primer;

    public AtributViewPengeluaranPrimer() {
    }

    public AtributViewPengeluaranPrimer(int pengeluaran_Bulanan_primer) {
        Pengeluaran_Bulanan_primer = pengeluaran_Bulanan_primer;
    }

    public int getPengeluaran_Bulanan_primer() {
        return Pengeluaran_Bulanan_primer;
    }

    public void setPengeluaran_Bulanan_primer(int pengeluaran_Bulanan_primer) {
        Pengeluaran_Bulanan_primer = pengeluaran_Bulanan_primer;
    }
}
