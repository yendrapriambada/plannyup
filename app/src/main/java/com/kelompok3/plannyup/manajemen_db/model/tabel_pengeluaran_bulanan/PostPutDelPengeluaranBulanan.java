package com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPengeluaranBulanan {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    AtributPengeluaranBulanan mPengeluaranBulanan;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public AtributPengeluaranBulanan getHerosPengeluaranBulanan() {
        return mPengeluaranBulanan;
    }
    public void setHerosPengeluaranBulanan(AtributPengeluaranBulanan DataPengeluaranBulanan) {
        mPengeluaranBulanan = DataPengeluaranBulanan;
    }
}
