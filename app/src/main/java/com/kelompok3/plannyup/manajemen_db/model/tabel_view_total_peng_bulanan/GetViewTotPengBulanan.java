package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_bulanan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewTotPengBulanan {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewTotPengBulanan> listDataTotPengBulanan;

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

    public List<AtributViewTotPengBulanan> getListDataTotPengBulanan() {
        return listDataTotPengBulanan;
    }

    public void setListDataTotPengBulanan(List<AtributViewTotPengBulanan> listDataTotPengBulanan) {
        this.listDataTotPengBulanan = listDataTotPengBulanan;
    }
}
