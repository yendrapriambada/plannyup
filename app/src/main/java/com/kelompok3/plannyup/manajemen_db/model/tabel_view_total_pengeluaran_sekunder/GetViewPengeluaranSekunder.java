package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_sekunder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewPengeluaranSekunder {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewPengeluaranSekunder> listData;

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

    public List<AtributViewPengeluaranSekunder> getListData() {
        return listData;
    }

    public void setListData(List<AtributViewPengeluaranSekunder> listData) {
        this.listData = listData;
    }
}
