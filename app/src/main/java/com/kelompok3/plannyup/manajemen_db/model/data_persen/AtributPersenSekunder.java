package com.kelompok3.plannyup.manajemen_db.model.data_persen;

import com.google.gson.annotations.SerializedName;

public class AtributPersenSekunder {
    @SerializedName("persen_peng_sekunder")
    private int persen_peng_sekunder;

    public AtributPersenSekunder() {
    }

    public AtributPersenSekunder(int persen_peng_sekunder) {
        this.persen_peng_sekunder = persen_peng_sekunder;
    }

    public int getPersen_peng_sekunder() {
        return persen_peng_sekunder;
    }

    public void setPersen_peng_sekunder(int persen_peng_sekunder) {
        this.persen_peng_sekunder = persen_peng_sekunder;
    }
}
