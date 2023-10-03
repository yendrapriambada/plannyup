package com.kelompok3.plannyup.manajemen_laporan_pengeluaran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.plannyup.activity_tambahan.PilihanMenuFormInputActivity;
import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.landing_page.MainActivity;
import com.kelompok3.plannyup.laporan.manajemen_laporan_finansial_planner.LaporanFinansialPlannerActivity;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.AtributPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.GetPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.AtributPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.GetPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceTabelView;
import com.kelompok3.plannyup.manajemen_user.InputUserActivity;
import com.kelompok3.plannyup.manajemen_user.UserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Laporan_Pengeluaran extends AppCompatActivity{

    // Buat textview
    TextView tvNamaKebutuhanBulanan, tvNominalBulanan, tvBatasWaktuPengeluaranBulanan, tvJenisPengeluaranBulanan,
            tvNamaKebutuhanJP, tvNominalJP, tvJangkaWaktuJP, tvNominalPengeluaranBulananJP;
    ApiInterfacePengeluaranBulanan mApiInterfacePengeluaranBulanan;
    ApiInterfacePengeluaranJp mApiInterfacePengeluaranJP;
    ApiInterfaceTabelView mApiInterfaceTabelViewBulanan;
    ApiInterfaceTabelView mApiInterfaceTabelViewJangkaPanjang;
    private RecyclerView mRecyclerViewBulanan, mRecyclerViewJangkaPanjang;
    private RecyclerView.Adapter mAdapterBulanan, mAdapterJangkaPanjang;
    private RecyclerView.LayoutManager mLayoutManagerBulanan, mLayoutManagerJangkaPanjang;
    BottomNavigationView bottomNavigationView;
    Spinner mSpinFilterJp, mSpinFilterBulanan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_pengeluaran);

        mApiInterfacePengeluaranBulanan = ApiClient.getClient().create(ApiInterfacePengeluaranBulanan.class);
        mApiInterfacePengeluaranJP = ApiClient.getClient().create(ApiInterfacePengeluaranJp.class);

        mApiInterfaceTabelViewBulanan = ApiClient.getClient().create(ApiInterfaceTabelView.class);
        mApiInterfaceTabelViewJangkaPanjang = ApiClient.getClient().create(ApiInterfaceTabelView.class);

        tvNamaKebutuhanBulanan = findViewById(R.id.tv_nama_kebutuhan_bulanan);
        tvNominalBulanan = findViewById(R.id.tv_nominal_bulanan);
        tvBatasWaktuPengeluaranBulanan = findViewById(R.id.tv_batas_waktu_bulanan);
        tvJenisPengeluaranBulanan = findViewById(R.id.tv_jenis_pengeluaran_bulanan);
        tvNamaKebutuhanJP = findViewById(R.id.tv_nama_kebutuhan_jp);
        tvNominalJP = findViewById(R.id.tv_nominal_jp);
        tvJangkaWaktuJP = findViewById(R.id.tv_jangka_waktu_menabung);
        tvNominalPengeluaranBulananJP = findViewById(R.id.tv_nominal_penabungan_bulanan);
        mSpinFilterJp = findViewById(R.id.spnr_filter_byStatus_lpJp);
        mSpinFilterBulanan = findViewById(R.id.spnr_filter_byBulan_lpBulanan);


        mRecyclerViewBulanan = (RecyclerView) findViewById(R.id.rv_laporan_pengeluaran_bulanan);
        mRecyclerViewJangkaPanjang = (RecyclerView) findViewById(R.id.rv_laporan_pengeluaran_jp);
        mLayoutManagerBulanan = new LinearLayoutManager(this);
        mLayoutManagerJangkaPanjang = new LinearLayoutManager(this);
        mRecyclerViewBulanan.setLayoutManager(mLayoutManagerBulanan);
        mRecyclerViewJangkaPanjang.setLayoutManager(mLayoutManagerJangkaPanjang);

        showPengeluaranBulanan();
        showPengeluaranJangkaPanjang();


        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home :
                        Intent home = new Intent(Laporan_Pengeluaran.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(Laporan_Pengeluaran.this, Laporan_Pengeluaran.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(Laporan_Pengeluaran.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(Laporan_Pengeluaran.this, LaporanFinansialPlannerActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(Laporan_Pengeluaran.this, UserActivity.class);
                        startActivity(user);
                        finish();
                        break;
                }
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home :
                        Intent home = new Intent(Laporan_Pengeluaran.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(Laporan_Pengeluaran.this, Laporan_Pengeluaran.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(Laporan_Pengeluaran.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(Laporan_Pengeluaran.this, LaporanFinansialPlannerActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(Laporan_Pengeluaran.this, UserActivity.class);
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
                    Intent add = new Intent(Laporan_Pengeluaran.this, PilihanMenuFormInputActivity.class);
                    startActivity(add);
                    finish();

                }
            }
        });

    }

    public void showPengeluaranBulanan(){
        List<AtributPengeluaranBulanan> listFiltered = new ArrayList<>();

        Call<GetPengeluaranBulanan> Data = mApiInterfacePengeluaranBulanan.getPengeluaranBulanan("get_pengeluaran_bulanan");
        Data.enqueue(new Callback<GetPengeluaranBulanan>() {
            @Override
            public void onResponse(retrofit2.Call<GetPengeluaranBulanan> call, Response<GetPengeluaranBulanan>
                    response) {
                List<AtributPengeluaranBulanan> list = response.body().getListDataPengeluaranBulanan();
                //on item select event handling
                mSpinFilterBulanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch(position){
                            case 1:
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(list);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 2:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 1){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 3:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 2){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 4:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 3){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 5:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 4){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 6:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 5){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 7:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 6){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 8:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 7){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 9:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 8){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 10:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 9){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 11:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 10){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 12:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 11){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                            case 13:
                                listFiltered.clear();
                                for (AtributPengeluaranBulanan element : list) {
                                    if (element.getBulan_pengeluaran() == 12){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(listFiltered);
                                mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mAdapterBulanan = new Adapter_Laporan_Pengeluaran_Bulanan(list);
                        mRecyclerViewBulanan.setAdapter(mAdapterBulanan);
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<GetPengeluaranBulanan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


    public void showPengeluaranJangkaPanjang(){
        List<AtributPengeluaranJp> listFiltered = new ArrayList<>();

        Call<GetPengeluaranJp> Data = mApiInterfacePengeluaranJP.getPengeluaranJp("get_pengeluaran_jp");
        Data.enqueue(new Callback<GetPengeluaranJp>() {
            @Override
            public void onResponse(retrofit2.Call<GetPengeluaranJp> call, Response<GetPengeluaranJp>
                    response) {
                List<AtributPengeluaranJp> list = response.body().getListDataPengeluaranJp();
                //on item select event handling
                mSpinFilterJp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch(position){
                            case 1:
                                mAdapterJangkaPanjang = new Adapter_Laporan_Pengeluaran_JP(list);
                                mRecyclerViewJangkaPanjang.setAdapter(mAdapterJangkaPanjang);
                                break;
                            case 2:
                                listFiltered.clear();
                                for (AtributPengeluaranJp element : list) {
                                    if (element.getStatus() == 0){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterJangkaPanjang = new Adapter_Laporan_Pengeluaran_JP(listFiltered);
                                mRecyclerViewJangkaPanjang.setAdapter(mAdapterJangkaPanjang);
                                break;
                            case 3:
                                listFiltered.clear();
                                for (AtributPengeluaranJp element : list) {
                                    if (element.getStatus() == 1){
                                        listFiltered.add(element);
                                    }
                                }
                                mAdapterJangkaPanjang = new Adapter_Laporan_Pengeluaran_JP(listFiltered);
                                mRecyclerViewJangkaPanjang.setAdapter(mAdapterJangkaPanjang);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mAdapterJangkaPanjang = new Adapter_Laporan_Pengeluaran_JP(list);
                        mRecyclerViewJangkaPanjang.setAdapter(mAdapterJangkaPanjang);
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<GetPengeluaranJp> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}