package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_bayar_pengeluaran_jp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewTotalBayarPengeluaranJp {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewTotalBayarPengeluaranJp> listData;

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

    public List<AtributViewTotalBayarPengeluaranJp> getListData() {
        return listData;
    }

    public void setListData(List<AtributViewTotalBayarPengeluaranJp> listData) {
        this.listData = listData;
    }
}
