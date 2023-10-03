package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.PostPutDelPengeluaranJp;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranJp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInputPengeluaranJpActivity extends AppCompatActivity {

    EditText edtNamaKebutuhan, edtNominalPengeluaran, edtJangkaWaktuMenabung;
    Button btSubmit;
    ApiInterfacePengeluaranJp mApiInterfacePengeluaranJp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_pengeluaran_jp);

        // Identifikasi Komponen Form
        edtNamaKebutuhan = (EditText) findViewById(R.id.edt_nama_kebutuhan_jp);
        edtNominalPengeluaran = (EditText) findViewById(R.id.edt_nominal_jp);
        edtJangkaWaktuMenabung =  (EditText) findViewById(R.id.edt_jangka_waktu_menabung_jp);
        btSubmit = (Button) findViewById(R.id.btn_simpan);

        // Definisi API
        mApiInterfacePengeluaranJp = ApiClient.getClient().create(ApiInterfacePengeluaranJp.class);

        // Fungsi Tombol Simpan
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    // Simpan Data
    private void saveData(){
        final String waktu_awal_jp = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final String namaKebutuhanJp = edtNamaKebutuhan.getText().toString();
        final String nominalJp = edtNominalPengeluaran.getText().toString();
        final String jangkaWaktuMenabungJp = edtJangkaWaktuMenabung.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int tambah = Integer.parseInt(jangkaWaktuMenabungJp);
        Date tgl = new Date();
        Calendar calTambah = Calendar.getInstance();

        calTambah.setTime(tgl);
        calTambah.add(Calendar.MONTH, tambah);

        final String waktu_akhir_jp = sdf.format(calTambah.getTime());

        final String status = "0";
//        status = 1 (udh lunas)
//        status = 0 (blm lunas)
        int nominalNabungJpPerBulan = Integer.parseInt(nominalJp)/Integer.parseInt(jangkaWaktuMenabungJp);
        String snominalNabungJpPerBulan = Integer.toString(nominalNabungJpPerBulan);

        Call<PostPutDelPengeluaranJp> postHerosCall = mApiInterfacePengeluaranJp.postPengeluaranJp(
                "insert_pengeluaran_jp",
                RequestBody.create(MediaType.parse("text/plain"), namaKebutuhanJp),
                RequestBody.create(MediaType.parse("text/plain"), nominalJp),
                RequestBody.create(MediaType.parse("text/plain"), jangkaWaktuMenabungJp),
                RequestBody.create(MediaType.parse("text/plain"), snominalNabungJpPerBulan),
                RequestBody.create(MediaType.parse("text/plain"), waktu_awal_jp),
                RequestBody.create(MediaType.parse("text/plain"), waktu_akhir_jp),
                RequestBody.create(MediaType.parse("text/plain"), status));

        postHerosCall.enqueue(new Callback<PostPutDelPengeluaranJp>() {
            @Override
            public void onResponse(Call<PostPutDelPengeluaranJp> call, Response<PostPutDelPengeluaranJp> response) {
                Intent showData = new Intent(FormInputPengeluaranJpActivity.this, showDataPengeluaranJpActivity.class);
                startActivity(showData);
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelPengeluaranJp> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
            }
        });
    }
}