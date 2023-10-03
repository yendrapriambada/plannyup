package com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran;

import com.google.gson.annotations.SerializedName;

public class AtributViewReminderPengeluaran {
    @SerializedName("id_pengeluaran_bulanan")
    private String id_pengeluaran_bulanan;
    @SerializedName("nama_kebutuhan_bulanan")
    private String nama_kebutuhan_bulanan;
    @SerializedName("nominal_pengeluaran_bulanan")
    private int nominal_pengeluaran_bulanan;
    @SerializedName("batas_waktu_pembayaran")
    private String batas_waktu_pembayaran;
    @SerializedName("jenis_pengeluaran")
    private String jenis_pengeluaran;
    @SerializedName("bulan_pengeluaran")
    private String bulan_pengeluaran;

    public AtributViewReminderPengeluaran() {
    }

    public AtributViewReminderPengeluaran(String id_pengeluaran_bulanan, String nama_kebutuhan_bulanan, int nominal_pengeluaran_bulanan, String batas_waktu_pembayaran, String jenis_pengeluaran, String bulan_pengeluaran) {
        this.id_pengeluaran_bulanan = id_pengeluaran_bulanan;
        this.nama_kebutuhan_bulanan = nama_kebutuhan_bulanan;
        this.nominal_pengeluaran_bulanan = nominal_pengeluaran_bulanan;
        this.batas_waktu_pembayaran = batas_waktu_pembayaran;
        this.jenis_pengeluaran = jenis_pengeluaran;
        this.bulan_pengeluaran = bulan_pengeluaran;
    }

    public String getId_pengeluaran_bulanan() {
        return id_pengeluaran_bulanan;
    }

    public void setId_pengeluaran_bulanan(String id_pengeluaran_bulanan) {
        this.id_pengeluaran_bulanan = id_pengeluaran_bulanan;
    }

    public String getNama_kebutuhan_bulanan() {
        return nama_kebutuhan_bulanan;
    }

    public void setNama_kebutuhan_bulanan(String nama_kebutuhan_bulanan) {
        this.nama_kebutuhan_bulanan = nama_kebutuhan_bulanan;
    }

    public int getNominal_pengeluaran_bulanan() {
        return nominal_pengeluaran_bulanan;
    }

    public void setNominal_pengeluaran_bulanan(int nominal_pengeluaran_bulanan) {
        this.nominal_pengeluaran_bulanan = nominal_pengeluaran_bulanan;
    }

    public String getBatas_waktu_pembayaran() {
        return batas_waktu_pembayaran;
    }

    public void setBatas_waktu_pembayaran(String batas_waktu_pembayaran) {
        this.batas_waktu_pembayaran = batas_waktu_pembayaran;
    }

    public String getJenis_pengeluaran() {
        return jenis_pengeluaran;
    }

    public void setJenis_pengeluaran(String jenis_pengeluaran) {
        this.jenis_pengeluaran = jenis_pengeluaran;
    }

    public String getBulan_pengeluaran() {
        return bulan_pengeluaran;
    }

    public void setBulan_pengeluaran(String bulan_pengeluaran) {
        this.bulan_pengeluaran = bulan_pengeluaran;
    }
}
