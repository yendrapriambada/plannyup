package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.Config;
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

public class FromEditPengeluaranJpActivity extends AppCompatActivity {

    EditText edtNamaKebutuhan, edtNominalPengeluaran, edtJangkaWaktuMenabung;
    Button btSubmit, btDelete;
    RadioGroup rgStatus;
    ApiInterfacePengeluaranJp mApiInterfacePengeluaranJp;
    String ID;

    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_edit_pengeluaran_jp);

        RadioButton rbSelesai, rbBerjalan;

        // Identifikasi Komponen Form
        edtNamaKebutuhan = (EditText) findViewById(R.id.edt_nama_kebutuhan_jp);
        edtNominalPengeluaran = (EditText) findViewById(R.id.edt_nominal_jp);
        edtJangkaWaktuMenabung =  (EditText) findViewById(R.id.edt_jangka_waktu_menabung_jp);
        rgStatus = findViewById(R.id.rg_status);
        rbSelesai = findViewById(R.id.rb_selasai);
        rbBerjalan = findViewById(R.id.rb_berjalan);

        btSubmit = (Button) findViewById(R.id.btn_simpan);
        btDelete = (Button) findViewById(R.id.btn_delete);

        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("id_pengeluaran_jp");
        edtNamaKebutuhan.setText(mIntent.getStringExtra("nama_kebutuhan_jp"));
        edtNominalPengeluaran.setText(mIntent.getStringExtra("nominal_penabungan_bulanan"));
        edtJangkaWaktuMenabung.setText(mIntent.getStringExtra("jangka_waktu_menabung"));
        if(mIntent.getStringExtra("status").contains("0")) {
            rbBerjalan.setChecked(true);
        } else if (mIntent.getStringExtra("status").contains("1")) {
            rbSelesai.setChecked(true);
        }

        // Definisi API
        mApiInterfacePengeluaranJp = ApiClient.getClient().create(ApiInterfacePengeluaranJp.class);

        // Fungsi Tombol Simpan
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    // Update Data
    private void updateData() {
        final String waktu_awal_jp = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final String namaKebutuhanJp = edtNamaKebutuhan.getText().toString();
        final String nominalJp = edtNominalPengeluaran.getText().toString();
        final String jangkaWaktuMenabungJp = edtJangkaWaktuMenabung.getText().toString();
        int selectidIdRgStatus = rgStatus.getCheckedRadioButtonId();
        String pilih = null;

        switch (selectidIdRgStatus) {
            case R.id.rb_selasai:
                pilih = "1";
                break;
            case R.id.rb_berjalan:
                pilih = "0";
                break;
        }

        final String status = pilih;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int tambah = Integer.parseInt(jangkaWaktuMenabungJp);
        Date tgl = new Date();
        Calendar calTambah = Calendar.getInstance();

        calTambah.setTime(tgl);
        calTambah.add(Calendar.MONTH, tambah);

        final String waktu_akhir_jp = sdf.format(calTambah.getTime());
//        status = 1 (udh lunas)
//        status = 0 (blm lunas)
        int nominalNabungJpPerBulan = Integer.parseInt(nominalJp)/Integer.parseInt(jangkaWaktuMenabungJp);
        String snominalNabungJpPerBulan = Integer.toString(nominalNabungJpPerBulan);


        Call<PostPutDelPengeluaranJp> putHerosCall = mApiInterfacePengeluaranJp.postUpdatePengeluaranJp(
                "update_pengeluaran_jp", ID,
                RequestBody.create(MediaType.parse("text/plain"), namaKebutuhanJp),
                RequestBody.create(MediaType.parse("text/plain"), nominalJp),
                RequestBody.create(MediaType.parse("text/plain"), jangkaWaktuMenabungJp),
                RequestBody.create(MediaType.parse("text/plain"), snominalNabungJpPerBulan),
                RequestBody.create(MediaType.parse("text/plain"), waktu_awal_jp),
                RequestBody.create(MediaType.parse("text/plain"), waktu_akhir_jp),
                RequestBody.create(MediaType.parse("text/plain"), status));
        putHerosCall.enqueue(new Callback<PostPutDelPengeluaranJp>() {
            @Override
            public void onResponse(Call<PostPutDelPengeluaranJp> call, Response<PostPutDelPengeluaranJp> response) {
                Intent showData = new Intent(FromEditPengeluaranJpActivity.this, showDataPengeluaranJpActivity.class);
                startActivity(showData);
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelPengeluaranJp> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, update data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteData() {
        showAlertDialog(ALERT_DIALOG_DELETE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Notes";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            // Kode Hapus
                            if (ID.trim().isEmpty()==false){
                                Call<PostPutDelPengeluaranJp> deleteHeros = mApiInterfacePengeluaranJp.deletePengeluaranJp("delete_pengeluaran_jp", ID);
                                deleteHeros.enqueue(new Callback<PostPutDelPengeluaranJp>() {
                                    @Override
                                    public void onResponse(Call<PostPutDelPengeluaranJp> call, Response<PostPutDelPengeluaranJp> response) {
                                        Intent showData = new Intent(FromEditPengeluaranJpActivity.this, showDataPengeluaranJpActivity.class);
                                        startActivity(showData);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<PostPutDelPengeluaranJp> call, Throwable t) {
                                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                                        Log.d("RETRO", "ON FAILURE : " + t.getCause());
                                        Toast.makeText(getApplicationContext(), "Error, delete data", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}