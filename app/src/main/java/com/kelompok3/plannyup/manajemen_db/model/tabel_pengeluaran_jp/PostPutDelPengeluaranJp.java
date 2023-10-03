package com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPengeluaranJp {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    AtributPengeluaranJp mPengeluaranJp;

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
    public AtributPengeluaranJp getHerosPengeluaranJp() {
        return mPengeluaranJp;
    }
    public void setHerosPengeluaranJp(AtributPengeluaranJp DataPengeluaranJp) {
        mPengeluaranJp = DataPengeluaranJp;
    }
}
