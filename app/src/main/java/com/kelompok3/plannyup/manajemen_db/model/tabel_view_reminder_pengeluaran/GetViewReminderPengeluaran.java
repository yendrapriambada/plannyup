package com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewReminderPengeluaran {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewReminderPengeluaran> listDataReminderPengeluaran;

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

    public List<AtributViewReminderPengeluaran> getListDataReminderPengeluaran() {
        return listDataReminderPengeluaran;
    }

    public void setListDataReminderPengeluaran(List<AtributViewReminderPengeluaran> listDataReminderPengeluaran) {
        this.listDataReminderPengeluaran = listDataReminderPengeluaran;
    }
}
