package com.kelompok3.plannyup.manajemen_db.model.tabel_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUser {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributUser> listDataUser;

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

    public List<AtributUser> getListDataUser() {
        return listDataUser;
    }

    public void setListDataUser(List<AtributUser> listDataUser) {
        this.listDataUser = listDataUser;
    }
}
