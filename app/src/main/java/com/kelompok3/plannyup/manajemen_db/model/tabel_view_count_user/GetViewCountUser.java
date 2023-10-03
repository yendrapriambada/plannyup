package com.kelompok3.plannyup.manajemen_db.model.tabel_view_count_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewCountUser {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewCountUser> listDataCount;

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

    public List<AtributViewCountUser> getListDataCount() {
        return listDataCount;
    }

    public void setListDataCount(List<AtributViewCountUser> listDataCount) {
        this.listDataCount = listDataCount;
    }
}
