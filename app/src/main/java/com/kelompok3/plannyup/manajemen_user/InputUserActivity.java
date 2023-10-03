package com.kelompok3.plannyup.manajemen_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.activity_tambahan.PilihanMenuFormInputActivity;
import com.kelompok3.plannyup.landing_page.MainActivity;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.PostPutDelUser;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceUser;
import com.kelompok3.plannyup.manajemen_laporan_pengeluaran.Laporan_Pengeluaran;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputUserActivity extends AppCompatActivity {

    EditText edtUsername, edtNama, edtTempatLahir, edtTglLahir, edtProfesi, edtNoHp, edtEmail, edtPemasukan;
    Button btSimpan;
    ApiInterfaceUser mApiInterfaceUser;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user);

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
                saveData();
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
                        Intent home = new Intent(InputUserActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(InputUserActivity.this, Laporan_Pengeluaran.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(InputUserActivity.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(InputUserActivity.this, InputUserActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(InputUserActivity.this, UserActivity.class);
                        startActivity(user);
                        finish();
                        break;
                }
                return true;
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

    // Simpan Data
    private void saveData(){
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

        Call<PostPutDelUser> postHerosCall = mApiInterfaceUser.postUser(
                "insert_user",
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
                Intent showData = new Intent(InputUserActivity.this, MainActivity.class);
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