package com.kelompok3.plannyup.manajemen_user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.PostPutDelUser;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    EditText edtUsername, edtNama, edtTempatLahir, edtTglLahir, edtProfesi, edtNoHp, edtEmail, edtPemasukan;
    String ID;
    Button btSimpan;
    ApiInterfaceUser mApiInterfaceUser;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // Identifikasi Komponen Form
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtNama = (EditText) findViewById(R.id.edt_nama);
        edtTempatLahir =  (EditText) findViewById(R.id.edt_tempat_lahir);
        edtTglLahir =  (EditText) findViewById(R.id.edt_tgllahir);
        edtProfesi =  (EditText) findViewById(R.id.edt_profesi);
        edtNoHp =  (EditText) findViewById(R.id.edt_nohp);
        edtEmail =  (EditText) findViewById(R.id.edt_email);
        edtPemasukan =  (EditText) findViewById(R.id.edt_pemasukan);
        btSimpan = (Button) findViewById(R.id.btn_simpan);

        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("id_user");
        edtUsername.setText(mIntent.getStringExtra("username"));
        edtNama.setText(mIntent.getStringExtra("nama_user"));
        edtTempatLahir.setText(mIntent.getStringExtra("tempat_lahir"));
        edtTglLahir.setText(mIntent.getStringExtra("tanggal_lahir"));
        edtProfesi.setText(mIntent.getStringExtra("profesi_user"));
        edtNoHp.setText(mIntent.getStringExtra("nohp_user"));
        edtEmail.setText(mIntent.getStringExtra("email_user"));
        edtPemasukan.setText(mIntent.getStringExtra("nominal_pemasukan"));

        // Definisi API
        mApiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // Show Date Picker
        edtTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        // Fungsi Tombol Simpan
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    // Show Date Dialog
    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtTglLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    // Update Data
    private void updateData() {
        final String inputUsername = edtUsername.getText().toString();
        final String inputNama = edtNama.getText().toString();
        final String inputTempatLahir = edtTempatLahir.getText().toString();
        final String inputTglLahir = edtTglLahir.getText().toString();
        final String inputProfesi = edtProfesi.getText().toString();
        final String inputNoHp = edtNoHp.getText().toString();
        final String inputEmail = edtEmail.getText().toString();
        final String inputPemasukan = edtPemasukan.getText().toString();
        final String inputTglPemasukan = "0";
        final String inputSaldoUtama = edtPemasukan.getText().toString();
        final String inputFotoProfil = "poto profil.jpg";

        Call<PostPutDelUser> postHerosCall = mApiInterfaceUser.postUpdateUser(
                "update_user", ID,
                RequestBody.create(MediaType.parse("text/plain"), inputUsername),
                RequestBody.create(MediaType.parse("text/plain"), inputNama),
                RequestBody.create(MediaType.parse("text/plain"), inputTempatLahir),
                RequestBody.create(MediaType.parse("text/plain"), inputTglLahir),
                RequestBody.create(MediaType.parse("text/plain"), inputProfesi),
                RequestBody.create(MediaType.parse("text/plain"), inputNoHp),
                RequestBody.create(MediaType.parse("text/plain"), inputEmail),
                RequestBody.create(MediaType.parse("text/plain"), inputTglPemasukan),
                RequestBody.create(MediaType.parse("text/plain"), inputPemasukan),
                RequestBody.create(MediaType.parse("text/plain"), inputSaldoUtama),
                RequestBody.create(MediaType.parse("text/plain"), inputFotoProfil)
        );

        postHerosCall.enqueue(new Callback<PostPutDelUser>() {
            @Override
            public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                Intent showData = new Intent(EditUserActivity.this, UserActivity.class);
                startActivity(showData);
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
            }
        });
    }
}