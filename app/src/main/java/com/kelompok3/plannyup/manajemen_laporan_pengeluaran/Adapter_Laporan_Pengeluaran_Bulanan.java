package com.kelompok3.plannyup.manajemen_laporan_pengeluaran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.AtributPengeluaranBulanan;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Adapter_Laporan_Pengeluaran_Bulanan extends RecyclerView.Adapter<Adapter_Laporan_Pengeluaran_Bulanan.MyViewHolder>{
    List<AtributPengeluaranBulanan> mList;

    public Adapter_Laporan_Pengeluaran_Bulanan(List<AtributPengeluaranBulanan> listData) {
        mList = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.laporan_pengeluaran_bulanan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        int number = mList.get(position).getNominal_pengeluaran_bulanan();
        String uang = NumberFormat.getNumberInstance(Locale.US).format(number);

        holder.mtv_nama_kebutuhan.setText(mList.get(position).getNama_kebutuhan_bulanan());
        holder.mtv_nominal.setText("Rp "+uang+",00");
        holder.mtv_waktu_pembayaran.setText(mList.get(position).getBatas_waktu_pembayaran());
        holder.mtv_jenis.setText(mList.get(position).getJenis_pengeluaran());
    }

    @Override
    public int getItemCount () {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtv_nama_kebutuhan, mtv_nominal, mtv_waktu_pembayaran, mtv_jenis;

        public MyViewHolder(View itemView) {
            super(itemView);
            mtv_nama_kebutuhan = (TextView) itemView.findViewById(R.id.tv_nama_kebutuhan_bulanan);
            mtv_nominal = (TextView) itemView.findViewById(R.id.tv_nominal_bulanan);
            mtv_waktu_pembayaran = (TextView) itemView.findViewById(R.id.tv_batas_waktu_bulanan);
            mtv_jenis = (TextView) itemView.findViewById(R.id.tv_jenis_pengeluaran_bulanan);
        }
    }
}