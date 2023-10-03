package com.kelompok3.plannyup.laporan.manajemen_laporan_finansial_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.AtributPersenJP;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.AtributPersenPrimer;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.AtributPersenSekunder;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.AtributPersenSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenJP;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenPrimer;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenSekunder;
import com.kelompok3.plannyup.manajemen_db.model.data_persen.GetPersenSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_sisa_saldo.AtributViewSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_sisa_saldo.GetViewSisaSaldo;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_bayar_pengeluaran_jp.AtributViewTotalBayarPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_bayar_pengeluaran_jp.GetViewTotalBayarPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_primer.AtributViewPengeluaranPrimer;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_primer.GetViewPengeluaranPrimer;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_sekunder.AtributViewPengeluaranSekunder;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_total_pengeluaran_sekunder.GetViewPengeluaranSekunder;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceTabelView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanFinansialPlannerActivity extends AppCompatActivity {

    // Create the object of TextView and PieChart class
    TextView tvPengJp, tvPengPrimer, tvPengSekunder, tvSisaSaldo, tvPersenPengJp, tvPersenPengPrimer, tvPersenPengSekunder, tvPersenSisaSaldo;
    ApiInterfaceTabelView mApiInterfaceTabelView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_finansial_planner);

        mApiInterfaceTabelView = ApiClient.getClient().create(ApiInterfaceTabelView.class);

        tvPengJp = findViewById(R.id.tv_peng_lap_jp);
        tvPengPrimer = findViewById(R.id.tv_peng_lap_primer);
        tvPengSekunder = findViewById(R.id.tv_peng_lap_sekunder);
        tvSisaSaldo = findViewById(R.id.tv_lap_sisa_saldo);
        tvPersenPengJp = findViewById(R.id.tv_persen_peng_jp);
        tvPersenPengPrimer = findViewById(R.id.tv_persen_peng_lap_primer);
        tvPersenPengSekunder = findViewById(R.id.tv_persen_peng_lap_sekunder);
        tvPersenSisaSaldo = findViewById(R.id.tv_persen_lap_sisa_saldo);
        pieChart = findViewById(R.id.piechart);

        showPengJp();
        showKebutuhanPrimer();
        showKebutuhanSekunder();
        showSisaSaldo();
        showPersenPengJp();
        showPersenKebutuhanPrimer();
        showPersenKebutuhanSekunder();
        showPersenSisaSaldo();
//        setDataPieChart();
    }

    public void showKebutuhanPrimer() {
        Call<GetViewPengeluaranPrimer> Data = mApiInterfaceTabelView.getViewPengeluaranPrimer("get_pengeluaran_primer");
        Data.enqueue(new Callback<GetViewPengeluaranPrimer>() {
            @Override
            public void onResponse(Call<GetViewPengeluaranPrimer> call, Response<GetViewPengeluaranPrimer>
                    response) {
                List<AtributViewPengeluaranPrimer> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data kebutuhan primer: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPengeluaran_Bulanan_primer();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvPengPrimer.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewPengeluaranPrimer> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showKebutuhanSekunder() {
        Call<GetViewPengeluaranSekunder> Data = mApiInterfaceTabelView.getViewPengeluaranSekunder("get_pengeluaran_sekunder");
        Data.enqueue(new Callback<GetViewPengeluaranSekunder>() {
            @Override
            public void onResponse(Call<GetViewPengeluaranSekunder> call, Response<GetViewPengeluaranSekunder>
                    response) {
                List<AtributViewPengeluaranSekunder> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data kebutuhan sekunder: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPengeluaran_bulanan_sekunder();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvPengSekunder.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewPengeluaranSekunder> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showPengJp() {
        Call<GetViewTotalBayarPengeluaranJp> Data = mApiInterfaceTabelView.getViewTotalBayarPengeluaranJp("get_total_bayar_pengeluaran_jp");
        Data.enqueue(new Callback<GetViewTotalBayarPengeluaranJp>() {
            @Override
            public void onResponse(Call<GetViewTotalBayarPengeluaranJp> call, Response<GetViewTotalBayarPengeluaranJp>
                    response) {
                List<AtributViewTotalBayarPengeluaranJp> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data kebutuhan bayar pengeluaran jp]: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getTotal_jp_bayar();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvPengJp.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewTotalBayarPengeluaranJp> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showSisaSaldo() {
        Call<GetViewSisaSaldo> Data = mApiInterfaceTabelView.getViewSisaSaldo("get_sisa_saldo");
        Data.enqueue(new Callback<GetViewSisaSaldo>() {
            @Override
            public void onResponse(Call<GetViewSisaSaldo> call, Response<GetViewSisaSaldo>
                    response) {
                List<AtributViewSisaSaldo> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data kebutuhan sisa saldo: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getSisa_saldo();
                String uang = NumberFormat.getNumberInstance(Locale.US).format(number);
                tvSisaSaldo.setText("Rp "+uang+",00");
            }

            @Override
            public void onFailure(Call<GetViewSisaSaldo> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showPersenKebutuhanPrimer() {
        Call<GetPersenPrimer> Data = mApiInterfaceTabelView.getPersenPrimer("get_persen_peng_primer");
        Data.enqueue(new Callback<GetPersenPrimer>() {
            @Override
            public void onResponse(Call<GetPersenPrimer> call, Response<GetPersenPrimer>
                    response) {
                List<AtributPersenPrimer> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data persen kebutuhan primer: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPersen_peng_primer();
                tvPersenPengPrimer.setText(Integer.toString(number));
                pieChart.addPieSlice(
                        new PieModel(
                                "Kebutuhan Primer",
                                number,
                                Color.parseColor("#66BB6A")));
            }

            @Override
            public void onFailure(Call<GetPersenPrimer> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showPersenKebutuhanSekunder() {
        Call<GetPersenSekunder> Data = mApiInterfaceTabelView.getPersenSekunder("get_persen_peng_sekunder");
        Data.enqueue(new Callback<GetPersenSekunder>() {
            @Override
            public void onResponse(Call<GetPersenSekunder> call, Response<GetPersenSekunder>
                    response) {
                List<AtributPersenSekunder> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data persen kebutuhan sekunder: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPersen_peng_sekunder();
                tvPersenPengSekunder.setText(Integer.toString(number));
                pieChart.addPieSlice(
                        new PieModel(
                                "Kebutuhan Sekunder",
                                number,
                                Color.parseColor("#29B6F6")));
            }

            @Override
            public void onFailure(Call<GetPersenSekunder> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showPersenPengJp() {
        Call<GetPersenJP> Data = mApiInterfaceTabelView.getPersenJP("get_persen_jp");
        Data.enqueue(new Callback<GetPersenJP>() {
            @Override
            public void onResponse(Call<GetPersenJP> call, Response<GetPersenJP>
                    response) {
                List<AtributPersenJP> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data persen jp: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPersen_peng_jp();
                tvPersenPengJp.setText(Integer.toString(number));
                pieChart.addPieSlice(
                        new PieModel(
                                "Pengeluaran Jangka Panjang",
                                number,
                                Color.parseColor("#FFA726")));
            }

            @Override
            public void onFailure(Call<GetPersenJP> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void showPersenSisaSaldo() {
        Call<GetPersenSisaSaldo> Data = mApiInterfaceTabelView.getPersenSisaSaldo("get_persen_sisa_saldo");
        Data.enqueue(new Callback<GetPersenSisaSaldo>() {
            @Override
            public void onResponse(Call<GetPersenSisaSaldo> call, Response<GetPersenSisaSaldo>
                    response) {
                List<AtributPersenSisaSaldo> list = response.body().getListData();
                Log.d("Retrofit Get", "Jumlah data persen sisa saldo: " +
                        String.valueOf(list.size()));
                int number = list.get(0).getPersen_sisa_saldo();
                tvPersenSisaSaldo.setText(Integer.toString(number));
                pieChart.addPieSlice(
                        new PieModel(
                                "Sisa Saldo",
                                number,
                                Color.parseColor("#EF5350")));
            }

            @Override
            public void onFailure(Call<GetPersenSisaSaldo> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}