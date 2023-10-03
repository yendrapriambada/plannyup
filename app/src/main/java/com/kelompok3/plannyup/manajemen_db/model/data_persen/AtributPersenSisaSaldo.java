package com.kelompok3.plannyup.manajemen_db.model.data_persen;

import com.google.gson.annotations.SerializedName;

public class AtributPersenSisaSaldo {
    @SerializedName("persen_sisa_saldo")
    private int persen_sisa_saldo;

    public AtributPersenSisaSaldo() {
    }

    public AtributPersenSisaSaldo(int persen_sisa_saldo) {
        this.persen_sisa_saldo = persen_sisa_saldo;
    }

    public int getPersen_sisa_saldo() {
        return persen_sisa_saldo;
    }

    public void setPersen_sisa_saldo(int persen_sisa_saldo) {
        this.persen_sisa_saldo = persen_sisa_saldo;
    }
}
