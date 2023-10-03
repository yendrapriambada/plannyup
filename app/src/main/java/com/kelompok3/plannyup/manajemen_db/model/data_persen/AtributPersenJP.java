package com.kelompok3.plannyup.manajemen_db.model.data_persen;

import com.google.gson.annotations.SerializedName;

public class AtributPersenJP {
    @SerializedName("persen_peng_jp")
    private int persen_peng_jp;

    public AtributPersenJP() {
    }

    public AtributPersenJP(int persen_peng_jp) {
        this.persen_peng_jp = persen_peng_jp;
    }

    public int getPersen_peng_jp() {
        return persen_peng_jp;
    }

    public void setPersen_peng_jp(int persen_peng_jp) {
        this.persen_peng_jp = persen_peng_jp;
    }

}
