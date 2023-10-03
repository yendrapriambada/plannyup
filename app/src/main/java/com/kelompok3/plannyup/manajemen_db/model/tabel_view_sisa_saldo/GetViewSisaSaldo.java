package com.kelompok3.plannyup.manajemen_db.model.tabel_view_sisa_saldo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewSisaSaldo {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewSisaSaldo> listData;

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

    public List<AtributViewSisaSaldo> getListData() {
        return listData;
    }

    public void setListData(List<AtributViewSisaSaldo> listData) {
        this.listData = listData;
    }
}
