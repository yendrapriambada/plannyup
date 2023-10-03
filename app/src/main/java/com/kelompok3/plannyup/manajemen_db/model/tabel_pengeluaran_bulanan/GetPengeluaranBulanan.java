package com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPengeluaranBulanan {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributPengeluaranBulanan> listDataPengeluaranBulanan;

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

    public List<AtributPengeluaranBulanan> getListDataPengeluaranBulanan() {
        return listDataPengeluaranBulanan;
    }

    public void setListDataPengeluaranBulanan(List<AtributPengeluaranBulanan> listDataPengeluaranBulanan) {
        this.listDataPengeluaranBulanan = listDataPengeluaranBulanan;
    }
}
