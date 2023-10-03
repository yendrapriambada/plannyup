package com.kelompok3.plannyup.activity_tambahan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.landing_page.MainActivity;
import com.kelompok3.plannyup.laporan.manajemen_laporan_finansial_planner.LaporanFinansialPlannerActivity;
import com.kelompok3.plannyup.manajemen_laporan_pengeluaran.Laporan_Pengeluaran;
import com.kelompok3.plannyup.manajemen_user.InputUserActivity;
import com.kelompok3.plannyup.manajemen_user.UserActivity;

public class PilihanMenuFormInputActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_menu_form_input);


        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home :
                        Intent home = new Intent(PilihanMenuFormInputActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.menu_lap_peng:
                        Intent lap_peng = new Intent(PilihanMenuFormInputActivity.this, Laporan_Pengeluaran.class);
                        startActivity(lap_peng);
                        finish();
                        break;
                    case R.id.placeholder:
                        Intent add = new Intent(PilihanMenuFormInputActivity.this, PilihanMenuFormInputActivity.class);
                        startActivity(add);
                        finish();
                        break;
                    case R.id.menu_lap_financial_planner:
                        Intent lap_financial_planner = new Intent(PilihanMenuFormInputActivity.this, LaporanFinansialPlannerActivity.class);
                        startActivity(lap_financial_planner);
                        finish();
                        break;
                    case R.id.menu_user :
                        Intent user = new Intent(PilihanMenuFormInputActivity.this, UserActivity.class);
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
                    Intent add = new Intent(PilihanMenuFormInputActivity.this, PilihanMenuFormInputActivity.class);
                    startActivity(add);
                    finish();

                }
            }
        });
    }
}