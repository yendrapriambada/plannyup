package com.kelompok3.plannyup.manajemen_db.model.tabel_user;

import com.google.gson.annotations.SerializedName;

public class AtributUser {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("username")
    private String username;
    @SerializedName("nama_user")
    private String nama_user;
    @SerializedName("tempat_lahir")
    private String tempat_lahir;
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @SerializedName("profesi_user")
    private String profesi_user;
    @SerializedName("nohp_user")
    private String nohp_user;
    @SerializedName("email_user")
    private String email_user;
    @SerializedName("tanggal_pemasukan")
    private String tanggal_pemasukan;
    @SerializedName("nominal_pemasukan")
    private int nominal_pemasukan;
    @SerializedName("saldo_utama")
    private int saldo_utama;
    @SerializedName("foto_profil")
    private String foto_profil;

    public AtributUser() {
    }

    public AtributUser(String id_user, String username, String nama_user, String tempat_lahir, String tanggal_lahir, String profesi_user, String nohp_user, String email_user, String tanggal_pemasukan, int nominal_pemasukan, int saldo_utama, String foto_profil) {
        this.id_user = id_user;
        this.username = username;
        this.nama_user = nama_user;
        this.tempat_lahir = tempat_lahir;
        this.tanggal_lahir = tanggal_lahir;
        this.profesi_user = profesi_user;
        this.nohp_user = nohp_user;
        this.email_user = email_user;
        this.tanggal_pemasukan = tanggal_pemasukan;
        this.nominal_pemasukan = nominal_pemasukan;
        this.saldo_utama = saldo_utama;
        this.foto_profil = foto_profil;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getProfesi_user() {
        return profesi_user;
    }

    public void setProfesi_user(String profesi_user) {
        this.profesi_user = profesi_user;
    }

    public String getNohp_user() {
        return nohp_user;
    }

    public void setNohp_user(String nohp_user) {
        this.nohp_user = nohp_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getTanggal_pemasukan() {
        return tanggal_pemasukan;
    }

    public void setTanggal_pemasukan(String tanggal_pemasukan) {
        this.tanggal_pemasukan = tanggal_pemasukan;
    }

    public int getNominal_pemasukan() {
        return nominal_pemasukan;
    }

    public void setNominal_pemasukan(int nominal_pemasukan) {
        this.nominal_pemasukan = nominal_pemasukan;
    }

    public int getSaldo_utama() {
        return saldo_utama;
    }

    public void setSaldo_utama(int saldo_utama) {
        this.saldo_utama = saldo_utama;
    }

    public String getFoto_profil() {
        return foto_profil;
    }

    public void setFoto_profil(String foto_profil) {
        this.foto_profil = foto_profil;
    }
}
