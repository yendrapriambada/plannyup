package com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_bulanan.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_bulanan.AtributPengeluaranBulanan;
import com.kelompok3.plannyup.manajemen_pengeluaran.pengeluaran_bulanan.FormEditPengeluaranBulananActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class adapterPengeluaranBulanan extends RecyclerView.Adapter<adapterPengeluaranBulanan.MyViewHolder>{
    List<AtributPengeluaranBulanan> mList;

    public adapterPengeluaranBulanan(List<AtributPengeluaranBulanan> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_pengeluaran_bulanan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        int numberNominal = mList.get(position).getNominal_pengeluaran_bulanan();
        String uangNominal = NumberFormat.getNumberInstance(Locale.US).format(numberNominal);

        holder.mTextNama.setText(mList.get(position).getNama_kebutuhan_bulanan());
        holder.mTextNominal.setText("Rp "+uangNominal+",00");
        holder.mTextBatasWaktuPembayaran.setText(mList.get(position).getBatas_waktu_pembayaran());
        holder.mTextJenisPengeluaran.setText(mList.get(position).getJenis_pengeluaran());
        holder.mTextBulanPengeluaran.setText(mList.get(position).getBulan_pengeluaran());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), FormEditPengeluaranBulananActivity.class);
                mIntent.putExtra("id_pengeluaran_bulanan", mList.get(position).getId_pengeluaran_bulanan());
                mIntent.putExtra("nama_kebutuhan_bulanan", mList.get(position).getNama_kebutuhan_bulanan());
                mIntent.putExtra("nominal_kebutuhan_bulanan", Integer.toString(mList.get(position).getNominal_pengeluaran_bulanan()));
                mIntent.putExtra("batas_waktu_pembayaran", mList.get(position).getBatas_waktu_pembayaran());
                mIntent.putExtra("jenis_pengeluaran", mList.get(position).getJenis_pengeluaran());
                mIntent.putExtra("bulan_pengeluaran", mList.get(position).getBulan_pengeluaran());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextNama, mTextNominal, mTextBatasWaktuPembayaran, mTextJenisPengeluaran, mTextBulanPengeluaran;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextNama = (TextView) itemView.findViewById(R.id.tv_sh_nama_pengeluaran_bulanan);
            mTextNominal = (TextView) itemView.findViewById(R.id.tv_sh_nominal_kebutuhan_bulanan);
            mTextBatasWaktuPembayaran = (TextView) itemView.findViewById(R.id.tv_sh_batas_waktu_bayar);
            mTextJenisPengeluaran = (TextView) itemView.findViewById(R.id.tv_sh_jenis_pengeluaran);
        }
    }
}

