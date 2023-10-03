package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.AtributPengeluaranJp;
import com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_jp.FromEditPengeluaranJpActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class adapterPengeluaranJp extends RecyclerView.Adapter<adapterPengeluaranJp.MyViewHolder>{
    List<AtributPengeluaranJp> mList;

    public adapterPengeluaranJp(List<AtributPengeluaranJp> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_pengeluaran_jp, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        int numberNominal = mList.get(position).getNominal_pengeluaran_jp();
        String uangNominal = NumberFormat.getNumberInstance(Locale.US).format(numberNominal);

        int numberTabung = mList.get(position).getNominal_penabungan_bulanan();
        String uangTabung = NumberFormat.getNumberInstance(Locale.US).format(numberTabung);

        holder.mTextNama.setText(mList.get(position).getNama_kebutuhan_jp());
        holder.mTextNonimal.setText("Rp "+uangNominal+",00");
        holder.mTextJangkaWaktu.setText(Integer.toString(mList.get(position).getJangka_waktu_menabung()));
        holder.mTextTabung.setText("Rp "+uangTabung+",00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), FromEditPengeluaranJpActivity.class);
                mIntent.putExtra("id_pengeluaran_jp", mList.get(position).getId_pengeluaran_jp());
                mIntent.putExtra("nama_kebutuhan_jp", mList.get(position).getNama_kebutuhan_jp());
                mIntent.putExtra("nominal_kebutuhan_jp", Integer.toString(mList.get(position).getNominal_pengeluaran_jp()));
                mIntent.putExtra("jangka_waktu_menabung", Integer.toString(mList.get(position).getJangka_waktu_menabung()));
                mIntent.putExtra("nominal_penabungan_bulanan", Integer.toString(mList.get(position).getNominal_penabungan_bulanan()));
                mIntent.putExtra("waktu_awal_pengisian_jp", mList.get(position).getWaktu_awal_pengisian_jp());
                mIntent.putExtra("waktu_akhir_pengisian_jp", mList.get(position).getWaktu_akhir_pengisian_jp());
                mIntent.putExtra("status", Integer.toString(mList.get(position).getStatus()));
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextNama, mTextNonimal, mTextJangkaWaktu, mTextTabung;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextNama = (TextView) itemView.findViewById(R.id.tv_sh_nama_pengeluaran_jp);
            mTextNonimal = (TextView) itemView.findViewById(R.id.tv_sh_harga_jp);
            mTextJangkaWaktu = (TextView) itemView.findViewById(R.id.tv_sh_jangkaWaktuMenabung_jp);
            mTextTabung = (TextView) itemView.findViewById(R.id.tv_sh_nominalTabung_jp);
        }
    }
}
