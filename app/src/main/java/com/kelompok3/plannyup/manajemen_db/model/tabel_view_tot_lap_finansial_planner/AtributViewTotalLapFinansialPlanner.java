package com.kelompok3.plannyup.manajemen_db.model.tabel_view_tot_lap_finansial_planner;

import com.google.gson.annotations.SerializedName;

public class AtributViewTotalLapFinansialPlanner {
    @SerializedName("total")
    private int total;

    public AtributViewTotalLapFinansialPlanner() {
    }

    public AtributViewTotalLapFinansialPlanner(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
