package com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPengeluaranJp {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributPengeluaranJp> listDataPengeluaranJp;

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

    public List<AtributPengeluaranJp> getListDataPengeluaranJp() {
        return listDataPengeluaranJp;
    }

    public void setListDataPengeluaranJp(List<AtributPengeluaranJp> listDataPengeluaranJp) {
        this.listDataPengeluaranJp = listDataPengeluaranJp;
    }
}
