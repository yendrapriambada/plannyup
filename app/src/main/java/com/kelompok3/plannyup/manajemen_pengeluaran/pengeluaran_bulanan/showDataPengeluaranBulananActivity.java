package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_bulanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.AtributPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.GetPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_bulanan.adapter.adapterPengeluaranBulanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class showDataPengeluaranBulananActivity extends AppCompatActivity {

    ApiInterfacePengeluaranBulanan mApiInterfaceBulanan;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_pengeluaran_bulanan);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_notes);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterfaceBulanan = ApiClient.getClient().create(ApiInterfacePengeluaranBulanan.class);
        showData();
    }

    public void showData() {
        Call<GetPengeluaranBulanan> HerosCall = mApiInterfaceBulanan.getPengeluaranBulanan("get_pengeluaran_bulanan");
        HerosCall.enqueue(new Callback<GetPengeluaranBulanan>() {
            @Override
            public void onResponse(Call<GetPengeluaranBulanan> call, Response<GetPengeluaranBulanan>
                    response) {
                List<AtributPengeluaranBulanan> bulananList = response.body().getListDataPengeluaranBulanan();
                Log.d("Retrofit Get", "Jumlah data Pengeluaran Bulanan: " +
                        String.valueOf(bulananList.size()));
                mAdapter = new adapterPengeluaranBulanan(bulananList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPengeluaranBulanan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
