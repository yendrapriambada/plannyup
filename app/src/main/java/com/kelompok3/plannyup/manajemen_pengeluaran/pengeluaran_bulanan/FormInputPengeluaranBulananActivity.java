package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_bulanan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.PostPutDelPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfacePengeluaranBulanan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInputPengeluaranBulananActivity extends AppCompatActivity {

    EditText edtNamaKebutuhanBulanan, edtNominalPengeluaranBulanan, txTanggal;
    Spinner spnJenisPengeluaranBulanan;
    Button btSubmit;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    ApiInterfacePengeluaranBulanan mApiInterfacePengeluaranBulanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_pengeluaran_bulanan);

        // Identifikasi Komponen Form
        edtNamaKebutuhanBulanan = (EditText) findViewById(R.id.edt_nama_kebutuhan_bulanan);
        edtNominalPengeluaranBulanan = (EditText) findViewById(R.id.edt_nominal_bulanan);
        txTanggal = (EditText) findViewById(R.id.tx_Tanggal);
        dateFormatter = new SimpleDateFormat ("yyyy-MM-dd");

        spnJenisPengeluaranBulanan = (Spinner) findViewById(R.id.spn_jenis_pengeluaran_bulanan);
        btSubmit = (Button) findViewById(R.id.btn_simpan);

        // Definisi API
        mApiInterfacePengeluaranBulanan = ApiClient.getClient().create(ApiInterfacePengeluaranBulanan.class);

        txTanggal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                showDateDialog();
            }
        });

        // Fungsi Tombol Simpan
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet (DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                txTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void saveData(){
        final String namaKebutuhanBulanan = edtNamaKebutuhanBulanan.getText().toString();
        final String nominalBulanan = edtNominalPengeluaranBulanan.getText().toString();
        final String batasWaktuPembayaran = txTanggal.getText().toString();
        final String jenisPembayaran = spnJenisPengeluaranBulanan.getSelectedItem().toString();
        final String bulanPengeluaran = getCurrentDate().toString();

        Call<PostPutDelPengeluaranBulanan> postHerosCall = mApiInterfacePengeluaranBulanan.postPengeluaranBulanan(
                "insert_pengeluaran_bulanan",
                RequestBody.create(MediaType.parse("text/plain"), namaKebutuhanBulanan),
                RequestBody.create(MediaType.parse("text/plain"), nominalBulanan),
                RequestBody.create(MediaType.parse("text/plain"), batasWaktuPembayaran),
                RequestBody.create(MediaType.parse("text/plain"), jenisPembayaran),
                RequestBody.create(MediaType.parse("text/plain"), bulanPengeluaran));

        postHerosCall.enqueue(new Callback<PostPutDelPengeluaranBulanan>() {
            @Override
            public void onResponse(Call<PostPutDelPengeluaranBulanan> call, Response<PostPutDelPengeluaranBulanan> response) {
//                Intent home = new Intent(FormInputPengeluaranBulananActivity.this, MainActivity.class);
//                startActivity(home);
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelPengeluaranBulanan> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
            }
        });
    }
}