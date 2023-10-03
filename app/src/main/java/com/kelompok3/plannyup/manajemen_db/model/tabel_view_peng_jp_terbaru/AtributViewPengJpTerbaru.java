package com.kelompok3.plannyup.manajemen_db.model.tabel_view_peng_jp_terbaru;

import com.google.gson.annotations.SerializedName;

public class AtributViewPengJpTerbaru {
    @SerializedName("id_pengeluaran_jp")
    private String id_pengeluaran_jp;
    @SerializedName("nama_kebutuhan_jp")
    private String nama_kebutuhan_jp;
    @SerializedName("nominal_pengeluaran_jp")
    private int nominal_pengeluaran_jp;
    @SerializedName("jangka_waktu_menabung")
    private int jangka_waktu_menabung;
    @SerializedName("nominal_penabungan_bulanan")
    private int nominal_penabungan_bulanan;

    public AtributViewPengJpTerbaru() {
    }

    public AtributViewPengJpTerbaru(String id_pengeluaran_jp, String nama_kebutuhan_jp, int nominal_pengeluaran_jp, int jangka_waktu_menabung, int nominal_penabungan_bulanan) {
        this.id_pengeluaran_jp = id_pengeluaran_jp;
        this.nama_kebutuhan_jp = nama_kebutuhan_jp;
        this.nominal_pengeluaran_jp = nominal_pengeluaran_jp;
        this.jangka_waktu_menabung = jangka_waktu_menabung;
        this.nominal_penabungan_bulanan = nominal_penabungan_bulanan;
    }

    public String getId_pengeluaran_jp() {
        return id_pengeluaran_jp;
    }

    public void setId_pengeluaran_jp(String id_pengeluaran_jp) {
        this.id_pengeluaran_jp = id_pengeluaran_jp;
    }

    public String getNama_kebutuhan_jp() {
        return nama_kebutuhan_jp;
    }

    public void setNama_kebutuhan_jp(String nama_kebutuhan_jp) {
        this.nama_kebutuhan_jp = nama_kebutuhan_jp;
    }

    public int getNominal_pengeluaran_jp() {
        return nominal_pengeluaran_jp;
    }

    public void setNominal_pengeluaran_jp(int nominal_pengeluaran_jp) {
        this.nominal_pengeluaran_jp = nominal_pengeluaran_jp;
    }

    public int getJangka_waktu_menabung() {
        return jangka_waktu_menabung;
    }

    public void setJangka_waktu_menabung(int jangka_waktu_menabung) {
        this.jangka_waktu_menabung = jangka_waktu_menabung;
    }

    public int getNominal_penabungan_bulanan() {
        return nominal_penabungan_bulanan;
    }

    public void setNominal_penabungan_bulanan(int nominal_penabungan_bulanan) {
        this.nominal_penabungan_bulanan = nominal_penabungan_bulanan;
    }
}
