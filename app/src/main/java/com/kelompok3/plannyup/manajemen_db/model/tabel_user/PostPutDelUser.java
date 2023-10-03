package com.kelompok3.plannyup.manajemen_db.model.tabel_user;

import com.google.gson.annotations.SerializedName;

public class PostPutDelUser {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    AtributUser mUser;

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
    public AtributUser getHerosUser() {
        return mUser;
    }
    public void setHerosUser(AtributUser DataUser) {
        mUser = DataUser;
    }
}
