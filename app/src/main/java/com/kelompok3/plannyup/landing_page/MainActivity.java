package com.kelompok3.plannyup.landing_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.plannyup.activity_tambahan.PilihanMenuFormInputActivity;
import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.landing_page.adapter.ReminderPengeluaranAdapter;
import com.kelompok3.plannyup.laporan.manajemen_laporan_finansial_planner.LaporanFinansialPlannerActivity;
import com.kelompok3.plannyup.manajemen_db.Config;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.AtributUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.GetUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_count_user.AtributViewCountUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_count_user.GetViewCountUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_peng_jp_terbaru.AtributViewPengJpTerbaru;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_peng_jp_terbaru.GetViewPengJpTerbaru;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran.AtributViewReminderPengeluaran;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran.GetViewReminderPengeluaran;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_bulanan.AtributViewTotPengBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_bulanan.GetViewTotPengBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_jp.AtributViewTotPengJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_peng_jp.GetViewTotPengJp;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceTabelView;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceUser;
import com.kelompok3.plannyup.manajemen_laporan_pengeluaran.Laporan_Pengeluaran;
import com.kelompok3.plannyup.manajemen_user.InputUserActivity;
import com.kelompok3.plannyup.manajemen_user.UserActivity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterfaceUser mApiInterfaceUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapterReminderPengeluaran;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiInterfaceTabelView mApiInterfaceTabelView;
    TextView tvUsername, tvTotalUang, tvTanggal, tvTotalPengeluaranBulanan, tvTotalPengeluaranJp, tvJpterbaru, tvUangJpTerbaru;
    ImageView imgProfile;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername = findViewById(R.id.tv_username);
        tvTotalUang = findViewById(R.id.tv_totalUang);
        tvTanggal = findViewById(R.id.tv_date);
        tvTotalPengeluaranBulanan = findViewById(R.id.tv_totalPengeluaranBulanan);
        tvTotalPengeluaranJp = findViewById(R.id.tv_totalUangPengeluaranFinancialPlanner);
        tvJpterbaru = findViewById(R.id.tv_jp_terbaru);
        tvUangJpTerbaru = findViewById(R.id.tv_uang_jp_terbaru);
        imgProfile = findViewById(R.id.gambar_profil);

        mApiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);
        mApiInterfaceTabelView = ApiClient.getClient().create(ApiInterfaceTabelView.class);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_reminder_pengeluaran);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // cek apakah sudah isi profile
        cekDataProfile();

        // Show Data
        showDataUser();
        showDataNominalPengeluaranJp();
        showDataNominalPengeluaranBulanan();
        showDataPengeluaranJpTerbaru();
        showDataReminderPengeluaran();
        // End Show Data


        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home :
                        Intent home = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(MainActivity.this, Laporan_Pengeluaran.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(MainActivity.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(MainActivity.this, LaporanFinansialPlannerActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(user);
                        finish();
                        break;
                }
                return true;
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.placeholder);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.placeholder){
                    Intent add = new Intent(MainActivity.this, PilihanMenuFormInputActivity.class);
                    startActivity(add);
                    finish();

                }
            }
        });

    }

    public void cekDataProfile() {
        Call<GetViewCountUser> DataCountUser = mApiInterfaceTabelView.getViewCountUser("get_count_user");
        DataCountUser.enqueue(new Callback<GetViewCountUser>() {
            @Override
            public void onResponse(Call<GetViewCountUser> call, Response<GetViewCountUser>
                    response) {
                List<AtributViewCountUser> listCountUser = response.body().getListDataCount();
                Log.d("Retrofit Get", "Jumlah user: " + String.valueOf(listCountUser.size()));
                if (listCountUser.get(0).getJumlah_data_user() == 0){
                    Intent isiDataUser = new Intent(MainActivity.this, InputUserActivity.class);
                    startActivity(isiDataUser);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetViewCountUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showDataUser() {
        Call<GetUser> DataUser = mApiInterfaceUser.getUser("get_user");
        DataUser.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser>
                    response) {
                List<AtributUser> listUser = response.body().getListDataUser();
                Log.d("Retrofit Get", "Jumlah data User: " +
                        String.valueOf(listUser.size()));
                tvUsername.setText(listUser.get(0).getUsername());
                int number = listUser.get(0).getSaldo_utama();
                String saldo = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvTotalUang.setText("Rp "+saldo+",00");
                Glide.with(MainActivity.this)
                        .load(Config.IMAGES_URL + listUser.get(0).getFoto_profil())
                        .apply(new RequestOptions().placeholder(R.color.design_default_color_on_primary))
                        .into(imgProfile);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
        final String date = new SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(new Date());
        tvTanggal.setText(date);
    }

    public void showDataNominalPengeluaranBulanan() {
        Call<GetViewTotPengBulanan> DataTotBulanan = mApiInterfaceTabelView.getViewTotPengBulanan("get_total_pengeluaran_bulanan");
        DataTotBulanan.enqueue(new Callback<GetViewTotPengBulanan>() {
            @Override
            public void onResponse(Call<GetViewTotPengBulanan> call, Response<GetViewTotPengBulanan>
                    response) {
                List<AtributViewTotPengBulanan> list = response.body().getListDataTotPengBulanan();
                Log.d("Retrofit Get", "Jumlah data Nominal Pengeluaran Bulanan: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getTotal_pengeluaran_bulanan();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvTotalPengeluaranBulanan.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewTotPengBulanan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showDataNominalPengeluaranJp() {
        Call<GetViewTotPengJp> DataTotJp = mApiInterfaceTabelView.getViewTotPengJp("get_total_pengeluaran_jp");
        DataTotJp.enqueue(new Callback<GetViewTotPengJp>() {
            @Override
            public void onResponse(Call<GetViewTotPengJp> call, Response<GetViewTotPengJp>
                    response) {
                List<AtributViewTotPengJp> list = response.body().getListDataTotPengJp();
                Log.d("Retrofit Get", "Jumlah data Nominal Pengeluaran Jp: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getTotal_pengeluaran_jp();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvTotalPengeluaranJp.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewTotPengJp> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showDataPengeluaranJpTerbaru() {
        Call<GetViewPengJpTerbaru> DataJpTerbaru = mApiInterfaceTabelView.getViewPengJpTerbaru("get_pengeluaran_jp_terbaru");
        DataJpTerbaru.enqueue(new Callback<GetViewPengJpTerbaru>() {
            @Override
            public void onResponse(Call<GetViewPengJpTerbaru> call, Response<GetViewPengJpTerbaru>
                    response) {
                List<AtributViewPengJpTerbaru> list = response.body().getListDataJpTerbaru();
                Log.d("Retrofit Get", "Jumlah data Pengeluaran Jp Terbaru: " +
                        String.valueOf(list.size()));
                tvJpterbaru.setText(list.get(0).getNama_kebutuhan_jp());
                int number = list.get(0).getNominal_pengeluaran_jp();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvUangJpTerbaru.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewPengJpTerbaru> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showDataReminderPengeluaran() {
        Call<GetViewReminderPengeluaran> ReminderPengeluaran = mApiInterfaceTabelView.getViewReminderPengeluaran("get_reminder_pengeluaran");
        ReminderPengeluaran.enqueue(new Callback<GetViewReminderPengeluaran>() {
            @Override
            public void onResponse(Call<GetViewReminderPengeluaran> call, Response<GetViewReminderPengeluaran>
                    response) {
                List<AtributViewReminderPengeluaran> list = response.body().getListDataReminderPengeluaran();
                Log.d("Retrofit Get", "Jumlah data reminder pengeluaran: " +
                        String.valueOf(list.size()));
                mAdapterReminderPengeluaran = new ReminderPengeluaranAdapter(list);
                mRecyclerView.setAdapter(mAdapterReminderPengeluaran);
            }

            @Override
            public void onFailure(Call<GetViewReminderPengeluaran> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}