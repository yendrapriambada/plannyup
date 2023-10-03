package com.kelompok3.plannyup.manajemen_laporan_pengeluaran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.plannyup.R;
import com.kelompok3.plannyup.manajemen_db.model.tabel_pengeluaran_jp.AtributPengeluaranJp;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter_Laporan_Pengeluaran_JP extends RecyclerView.Adapter<Adapter_Laporan_Pengeluaran_JP.MyViewHolder>{
    List<AtributPengeluaranJp> mList;
    List<AtributPengeluaranJp> mListFiltered;

    public Adapter_Laporan_Pengeluaran_JP (List<AtributPengeluaranJp> listData) {
        mList = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.laporan_pengeluaran_jp, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        int number = mList.get(position).getNominal_pengeluaran_jp();
        String uang = NumberFormat.getNumberInstance(Locale.US).format(number);

        int number2 = mList.get(position).getNominal_penabungan_bulanan();
        String uang2 = NumberFormat.getNumberInstance(Locale.US).format(number2);

        holder.mtv_nama_kebutuhan.setText(mList.get(position).getNama_kebutuhan_jp());
        holder.mtv_nominal.setText("Rp "+uang+",00");
        holder.mtv_jangka_waktu.setText(Integer.toString(mList.get(position).getJangka_waktu_menabung()));
        holder.mtv_nominal_pengeluaran_bulanan.setText("Rp "+uang2+",00");
        if(mList.get(position).getStatus() == 0) {
            holder.mtv_status.setText("Berjalan");
        } else {
            holder.mtv_status.setText("Selesai");
        }
    }

    @Override
    public int getItemCount () {
        return mList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mListFiltered = mList;
                } else {
                    List<AtributPengeluaranJp> filteredList = new ArrayList<>();
                    for (AtributPengeluaranJp row : mList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        String status;
                        if(row.getStatus() == 0) {
                            status  = "Berjalan";
                        } else {
                            status = "Selesai";
                        }
                        if (status.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFiltered = (ArrayList<AtributPengeluaranJp>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtv_nama_kebutuhan, mtv_nominal, mtv_jangka_waktu, mtv_nominal_pengeluaran_bulanan, mtv_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            mtv_nama_kebutuhan = (TextView) itemView.findViewById(R.id.tv_nama_kebutuhan_jp);
            mtv_nominal = (TextView) itemView.findViewById(R.id.tv_nominal_jp);
            mtv_jangka_waktu= (TextView) itemView.findViewById(R.id.tv_jangka_waktu_menabung);
            mtv_nominal_pengeluaran_bulanan  = (TextView) itemView.findViewById(R.id.tv_nominal_penabungan_bulanan);
            mtv_status = (TextView) itemView.findViewById(R.id.tv_status_lpJp);
        }
    }
}