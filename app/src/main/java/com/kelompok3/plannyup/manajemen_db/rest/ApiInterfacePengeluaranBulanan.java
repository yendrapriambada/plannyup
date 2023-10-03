package com.kelompok3.plannyup.manajemen_db.rest;

import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.GetPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.PostPutDelPengeluaranBulanan;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfacePengeluaranBulanan {
    //Ambil data select dari tabel MELALUI API
    @GET("restapi.php")
    Call<GetPengeluaranBulanan> getPengeluaranBulanan(@Query("function") String function);

    //Insert data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelPengeluaranBulanan> postPengeluaranBulanan(@Query("function") String function,
                                                 @Part("nama_kebutuhan_bulanan") RequestBody nama_kebutuhan_bulanan,
                                                 @Part("nominal_pengeluaran_bulanan") RequestBody nominal_pengeluaran_bulanan,
                                                 @Part("batas_waktu_pembayaran") RequestBody batas_waktu_pembayaran,
                                                 @Part("jenis_pengeluaran") RequestBody jenis_pengeluaran,
                                                 @Part("bulan_pengeluaran") RequestBody bulan_pengeluaran);

    //Update data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelPengeluaranBulanan> postUpdatePengeluaranBulanan(@Query("function") String function,
                                                       @Query("id_pengeluaran_bulanan") String id_pengeluaran_bulanan,
                                                       @Part("nama_kebutuhan_bulanan") RequestBody nama_kebutuhan_bulanan,
                                                       @Part("nominal_pengeluaran_bulanan") RequestBody nominal_pengeluaran_bulanan,
                                                       @Part("batas_waktu_pembayaran") RequestBody batas_waktu_pembayaran,
                                                       @Part("jenis_pengeluaran") RequestBody jenis_pengeluaran,
                                                       @Part("bulan_pengeluaran") RequestBody bulan_pengeluaran);

    //Delete data ke tabel melalui API
    @POST("restapi.php")
    Call<PostPutDelPengeluaranBulanan> deletePengeluaranBulanan(@Query("function") String function, @Query("id_pengeluaran_bulanan") String id_pengeluaran_bulanan);
}
