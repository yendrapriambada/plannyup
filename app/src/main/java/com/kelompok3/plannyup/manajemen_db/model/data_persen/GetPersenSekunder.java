package com.kelompok3.plannyup.manajemen_db.model.data_persen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPersenSekunder {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributPersenSekunder> listData;

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

    public List<AtributPersenSekunder> getListData() {
        return listData;
    }

    public void setListData(List<AtributPersenSekunder> listData) {
        this.listData = listData;
    }
}
