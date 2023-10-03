package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.AtributPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.GetPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranJp;
import com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp.adapter.adapterPengeluaranJp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class showDataPengeluaranJpActivity extends AppCompatActivity {

    ApiInterfacePengeluaranJp mApiInterfaceJp;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_pengeluaran_jp);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_notes);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterfaceJp = ApiClient.getClient().create(ApiInterfacePengeluaranJp.class);
        showData();
    }

    public void showData() {
        Call<GetPengeluaranJp> HerosCall = mApiInterfaceJp.getPengeluaranJp("get_pengeluaran_jp");
        HerosCall.enqueue(new Callback<GetPengeluaranJp>() {
            @Override
            public void onResponse(Call<GetPengeluaranJp> call, Response<GetPengeluaranJp>
                    response) {
                List<AtributPengeluaranJp> jpList = response.body().getListDataPengeluaranJp();
                Log.d("Retrofit Get", "Jumlah data Pengeluaran Jp: " +
                        String.valueOf(jpList.size()));
                mAdapter = new adapterPengeluaranJp(jpList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPengeluaranJp> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}