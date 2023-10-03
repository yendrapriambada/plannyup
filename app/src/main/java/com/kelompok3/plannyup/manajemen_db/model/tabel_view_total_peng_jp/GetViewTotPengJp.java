package com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_jp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewTotPengJp {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewTotPengJp> listDataTotPengJp;

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

    public List<AtributViewTotPengJp> getListDataTotPengJp() {
        return listDataTotPengJp;
    }

    public void setListDataTotPengJp(List<AtributViewTotPengJp> listDataTotPengJp) {
        this.listDataTotPengJp = listDataTotPengJp;
    }
}
