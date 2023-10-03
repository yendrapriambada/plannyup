package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_primer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewPengeluaranPrimer {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewPengeluaranPrimer> listData;

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

    public List<AtributViewPengeluaranPrimer> getListData() {
        return listData;
    }

    public void setListData(List<AtributViewPengeluaranPrimer> listData) {
        this.listData = listData;
    }
}
