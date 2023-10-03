package com.kelompok3.plannyup.landing_page.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_view_reminder_pengeluaran.AtributViewReminderPengeluaran;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReminderPengeluaranAdapter extends RecyclerView.Adapter<ReminderPengeluaranAdapter.MyViewHolder>{
    List<AtributViewReminderPengeluaran> mList;

    public ReminderPengeluaranAdapter(List<AtributViewReminderPengeluaran> listData) {
        mList = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reminder_pengeluaran, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        int number = mList.get(position).getNominal_pengeluaran_bulanan();
        String uang = NumberFormat.getNumberInstance(Locale.US).format(number);

        holder.mtv_nama_kebutuhan.setText(mList.get(position).getNama_kebutuhan_bulanan());
        holder.mtv_harga_kebutuhan.setText("Rp "+uang+",00");
        holder.mtv_tanggal_jatuh_tempo.setText(mList.get(position).getBatas_waktu_pembayaran());
    }

    @Override
    public int getItemCount () {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtv_nama_kebutuhan, mtv_harga_kebutuhan, mtv_tanggal_jatuh_tempo;

        public MyViewHolder(View itemView) {
            super(itemView);
            mtv_nama_kebutuhan = (TextView) itemView.findViewById(R.id.tv_nama_kebutuhan);
            mtv_harga_kebutuhan = (TextView) itemView.findViewById(R.id.tv_harga_kebutuhan);
            mtv_tanggal_jatuh_tempo = (TextView) itemView.findViewById(R.id.tv_tanggal_jatuh_tempo);
        }
    }
}
