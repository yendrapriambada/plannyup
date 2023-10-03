package com.kelompok3.plannyup.manajemen_db.model.tabel_view_tot_lap_finansial_planner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetViewTotalLapFinansialPlanner {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<AtributViewTotalLapFinansialPlanner> listData;

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

    public List<AtributViewTotalLapFinansialPlanner> getListData() {
        return listData;
    }

    public void setListData(List<AtributViewTotalLapFinansialPlanner> listData) {
        this.listData = listData;
    }
}
