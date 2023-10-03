package com.kelompok3.plannyup.manajemen_db.model.tabel_view_peng_jp_terbaru;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewPengJpTerbaru {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewPengJpTerbaru> listDataJpTerbaru;

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

    public List<AtributViewPengJpTerbaru> getListDataJpTerbaru() {
        return listDataJpTerbaru;
    }

    public void setListDataJpTerbaru(List<AtributViewPengJpTerbaru> listDataJpTerbaru) {
        this.listDataJpTerbaru = listDataJpTerbaru;
    }
}
