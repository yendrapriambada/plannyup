package com.kelompok3.plannyup.manajemen_db.rest;

import com.kelompok3.plannyup.manajemen_db.model.tabel_user.GetUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.PostPutDelUser;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfaceUser {
    //Ambil data select dari tabel MELALUI API
    @GET("restapi.php")
    Call<GetUser> getUser(@Query("function") String function);

    //Insert data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelUser> postUser(@Query("function") String function,
                                   @Part("username") RequestBody username,
                                   @Part("nama_user") RequestBody nama_user,
                                   @Part("tempat_lahir") RequestBody tempat_lahir,
                                   @Part("tanggal_lahir") RequestBody tanggal_lahir,
                                   @Part("profesi_user") RequestBody profesi_user,
                                   @Part("nohp_user") RequestBody nohp_user,
                                   @Part("email_user") RequestBody email_user,
                                   @Part("tanggal_pemasukan") RequestBody tanggal_pemasukan,
                                   @Part("nominal_pemasukan") RequestBody nominal_pemasukan,
                                   @Part("saldo_utama") RequestBody saldo_utama,
                                   @Part("foto_profil") RequestBody foto_profil);

    //Update data ke tabel melalui API
    @Multipart
    @POST("restapi.php")
    Call<PostPutDelUser> postUpdateUser(@Query("function") String function,
                                        @Query("id_user") String id_user,
                                        @Part("username") RequestBody username,
                                        @Part("nama_user") RequestBody nama_user,
                                        @Part("tempat_lahir") RequestBody tempat_lahir,
                                        @Part("tanggal_lahir") RequestBody tanggal_lahir,
                                        @Part("profesi_user") RequestBody profesi_user,
                                        @Part("nohp_user") RequestBody nohp_user,
                                        @Part("email_user") RequestBody email_user,
                                        @Part("tanggal_pemasukan") RequestBody tanggal_pemasukan,
                                        @Part("nominal_pemasukan") RequestBody nominal_pemasukan,
                                        @Part("saldo_utama") RequestBody saldo_utama,
                                        @Part("foto_profil") RequestBody foto_profil);

    //Delete data ke tabel melalui API
    @POST("restapi.php")
    Call<PostPutDelUser> deleteUser(@Query("function") String function, @Query("id_user") String id_user);
}
