package com.kelompok3.plannyup.manajemen_db.model.data_persen;

import com.google.gson.annotations.SerializedName;

public class AtributPersenPrimer {
    @SerializedName("persen_peng_primer")
    private int persen_peng_primer;

    public AtributPersenPrimer() {
    }

    public AtributPersenPrimer(int persen_peng_primer) {
        this.persen_peng_primer = persen_peng_primer;
    }

    public int getPersen_peng_primer() {
        return persen_peng_primer;
    }

    public void setPersen_peng_primer(int persen_peng_primer) {
        this.persen_peng_primer = persen_peng_primer;
    }
}
