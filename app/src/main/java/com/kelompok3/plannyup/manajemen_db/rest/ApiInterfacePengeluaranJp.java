package com.kelompok3.plannyup.manajemen_db.rest;

import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.GetPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.PostPutDelPengeluaranJp;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfacePengeluaranJp {
    //Ambil data select dari tabel MELALUI API
    @GET("restapi.php")
    Call<GetPengeluaranJp> getPengeluaranJp(@Query("function") String function);

    //Insert data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelPengeluaranJp> postPengeluaranJp(@Query("function") String function,
                                                    @Part("nama_kebutuhan_jp") RequestBody nama_kebutuhan_jp,
                                                    @Part("nominal_pengeluaran_jp") RequestBody nominal_pengeluaran_jp,
                                                    @Part("jangka_waktu_menabung") RequestBody jangka_waktu_menabung,
                                                    @Part("nominal_penabungan_bulanan") RequestBody nominal_penabungan_bulanan,
                                                    @Part("waktu_awal_pengisian_jp") RequestBody waktu_awal_pengisian_jp,
                                                    @Part("waktu_akhir_pengisian_jp") RequestBody waktu_akhir_pengisian_jp,
                                                    @Part("status") RequestBody status);

    //Update data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelPengeluaranJp> postUpdatePengeluaranJp(@Query("function") String function,
                                                          @Query("id_pengeluaran_jp") String id_pengeluaran_jp,
                                                          @Part("nama_kebutuhan_jp") RequestBody nama_kebutuhan_jp,
                                                          @Part("nominal_pengeluaran_jp") RequestBody nominal_pengeluaran_jp,
                                                          @Part("jangka_waktu_menabung") RequestBody jangka_waktu_menabung,
                                                          @Part("nominal_penabungan_bulanan") RequestBody nominal_penabungan_bulanan,
                                                          @Part("waktu_awal_pengisian_jp") RequestBody waktu_awal_pengisian_jp,
                                                          @Part("waktu_akhir_pengisian_jp") RequestBody waktu_akhir_pengisian_jp,
                                                          @Part("status") RequestBody status);

    //Delete data ke tabel melalui API
    @POST("restapi.php")
    Call<PostPutDelPengeluaranJp> deletePengeluaranJp(@Query("function") String function, @Query("id_pengeluaran_jp") String id_pengeluaran_jp);
}
