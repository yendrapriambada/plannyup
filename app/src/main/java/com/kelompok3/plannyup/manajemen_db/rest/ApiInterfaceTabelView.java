package com.kelompok3.plannyup.manajemen_db.rest;

import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenJP;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenPrimer;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenSekunder;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_count_user.GetViewCountUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_peng_jp_terbaru.GetViewPengJpTerbaru;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran.GetViewReminderPengeluaran;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_sisa_saldo.GetViewSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_tot_lap_finansial_planner.GetViewTotalLapFinansialPlanner;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_bayar_pengeluaran_jp.GetViewTotalBayarPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_bulanan.GetViewTotPengBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_jp.GetViewTotPengJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_primer.GetViewPengeluaranPrimer;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_sekunder.GetViewPengeluaranSekunder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceTabelView {
    @GET("restapi_view_tabel.php")
    Call<GetViewCountUser> getViewCountUser(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewTotPengBulanan> getViewTotPengBulanan(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewTotPengJp> getViewTotPengJp(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewPengJpTerbaru> getViewPengJpTerbaru(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewReminderPengeluaran> getViewReminderPengeluaran(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewPengeluaranPrimer> getViewPengeluaranPrimer(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewPengeluaranSekunder> getViewPengeluaranSekunder(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewTotalBayarPengeluaranJp> getViewTotalBayarPengeluaranJp(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewSisaSaldo> getViewSisaSaldo(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetViewTotalLapFinansialPlanner> getViewTotalLapFinansialPlanner(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetPersenPrimer> getPersenPrimer(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetPersenSekunder> getPersenSekunder(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetPersenJP> getPersenJP(@Query("function") String function);

    @GET("restapi_view_tabel.php")
    Call<GetPersenSisaSaldo> getPersenSisaSaldo(@Query("function") String function);
}
