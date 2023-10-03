package com.kelompok3.plannyup.manajemen_user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.activity_tambahan.PilihanMenuFormInputActivity;
import com.kelompok3.plannyup.landing_page.MainActivity;
import com.kelompok3.plannyup.manajemen_db.Config;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.AtributUser;
import com.kelompok3.plannyup.manajemen_db.model.tabel_user.GetUser;
import com.kelompok3.plannyup.manajemen_db.rest.ApiClient;
import com.kelompok3.plannyup.manajemen_db.rest.ApiInterfaceUser;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ApiInterfaceUser mApiInterfaceUser;
    TextView tvUsername, tvNama, tvTempatLahir, tvTanggalLahir, tvPemasukan, tvProfesi, tvNoHp, tvEmail;
    ImageView imgProfile;
    BottomNavigationView bottomNavigationView;
    Button btEditProfile;
    String id_user, dataUsername, dataNama, dataTempatLahir, dataTanggalLahir, dataProfesi, dataPemasukan, dataNoHp, dataEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvUsername = findViewById(R.id.tv_username);
        tvNama = findViewById(R.id.tv_nama);
        tvTempatLahir = findViewById(R.id.tv_tempat_lahir);
        tvTanggalLahir = findViewById(R.id.tv_tanggal_lahir);
        tvPemasukan = findViewById(R.id.tv_pemasukan);
        tvProfesi = findViewById(R.id.tv_profesi);
        tvNoHp = findViewById(R.id.tv_nohp);
        tvEmail = findViewById(R.id.tv_email);
        tvPemasukan = findViewById(R.id.tv_pemasukan);
        imgProfile = findViewById(R.id.profile_image);
        btEditProfile = (Button) findViewById(R.id.btn_edt_profile);

        mApiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);

        // Show Data Profile User
        showDataUser();

        // Fungsi Tombol Edit User
        btEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inten ke edit data user
                Intent editUser = new Intent(UserActivity.this, EditUserActivity.class);
                editUser.putExtra("id_user", id_user);
                editUser.putExtra("username", dataUsername);
                editUser.putExtra("nama_user", dataNama);
                editUser.putExtra("tempat_lahir", dataTempatLahir);
                editUser.putExtra("tanggal_lahir", dataTanggalLahir);
                editUser.putExtra("profesi_user", dataProfesi);
                editUser.putExtra("nohp_user", dataNoHp);
                editUser.putExtra("email_user", dataEmail);
                editUser.putExtra("nominal_pemasukan", dataPemasukan);
                startActivity(editUser);
                finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home :
                        Intent home = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(UserActivity.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(UserActivity.this, UserActivity.class);
                        startActivity(user);
                        finish();
                        break;
                }
                return true;
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

                // simpan data pada var global
                id_user = listUser.get(0).getId_user();
                dataUsername = listUser.get(0).getUsername();
                dataNama = listUser.get(0).getNama_user();
                dataTempatLahir = listUser.get(0).getTempat_lahir();
                dataTanggalLahir = listUser.get(0).getTanggal_lahir();
                dataPemasukan = Integer.toString(listUser.get(0).getNominal_pemasukan());
                int nominal = listUser.get(0).getNominal_pemasukan();
                String dataNominal = NumberFormat.getNumberInstance(Locale.US).format(nominal);
                dataProfesi = listUser.get(0).getProfesi_user();
                dataNoHp = listUser.get(0).getNohp_user();
                dataEmail = listUser.get(0).getEmail_user();

                // Tampilkan data pada view
                tvUsername.setText(dataUsername);
                tvNama.setText(dataNama);
                tvTempatLahir.setText(dataTempatLahir);
                tvTanggalLahir.setText(dataTanggalLahir);
                tvPemasukan.setText(String.format("Rp%s", dataNominal));
                tvProfesi.setText(dataProfesi);
                tvNoHp.setText(dataNoHp);
                tvEmail.setText(dataEmail);
                Glide.with(UserActivity.this)
                        .load(Config.IMAGES_URL + listUser.get(0).getFoto_profil())
                        .apply(new RequestOptions().placeholder(R.color.design_default_color_on_primary))
                        .into(imgProfile);

            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}