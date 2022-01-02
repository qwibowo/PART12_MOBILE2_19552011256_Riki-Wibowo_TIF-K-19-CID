package com.uas_19552011256_mp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList pem_id, pem_namasite, pem_pelaksana, pem_tglawal, pem_tglakhir, pem_jenis, pem_teknisi, pem_deskripsi, pem_kesimpulan, pem_saran;

    CustomAdapter(Activity activity, Context context, ArrayList pem_id, ArrayList pem_namasite, ArrayList pem_pelaksana, ArrayList pem_tglawal, ArrayList pem_tglakhir, ArrayList pem_jenis, ArrayList pem_teknisi, ArrayList pem_deskripsi, ArrayList pem_kesimpulan, ArrayList pem_saran){
        this.activity = activity;
        this.context = context;
        this.pem_id = pem_id;
        this.pem_namasite = pem_namasite;
        this.pem_pelaksana = pem_pelaksana;
        this.pem_tglawal = pem_tglawal;
        this.pem_tglakhir = pem_tglakhir;
        this.pem_jenis = pem_jenis;
        this.pem_teknisi = pem_teknisi;
        this.pem_deskripsi = pem_deskripsi;
        this.pem_kesimpulan = pem_kesimpulan;
        this.pem_saran = pem_saran;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //holder.pem_id_txt.setText(String.valueOf(pem_id.get(position)));
        holder.namasite_txt.setText(String.valueOf(pem_namasite.get(position)));
        holder.pelaksana_txt.setText(String.valueOf(pem_pelaksana.get(position)));
        holder.tglawal_txt.setText(String.valueOf(pem_tglawal.get(position)));
        //holder.tglakhir_txt.setText(String.valueOf(pem_tglakhir.get(position)));
        holder.jenis_txt.setText(String.valueOf(pem_jenis.get(position)));
        //holder.teknisi_txt.setText(String.valueOf(pem_teknisi.get(position)));
        //holder.deskripsi_txt.setText(String.valueOf(pem_deskripsi.get(position)));
        //holder.kesimpulan_txt.setText(String.valueOf(pem_kesimpulan.get(position)));
        //holder.saran_txt.setText(String.valueOf(pem_saran.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(pem_id.get(position)));
                intent.putExtra("namasite", String.valueOf(pem_namasite.get(position)));
                intent.putExtra("pelaksana", String.valueOf(pem_pelaksana.get(position)));
                intent.putExtra("tglawal", String.valueOf(pem_tglawal.get(position)));
                intent.putExtra("tglakhir", String.valueOf(pem_tglakhir.get(position)));
                intent.putExtra("jenis", String.valueOf(pem_jenis.get(position)));
                intent.putExtra("teknisi", String.valueOf(pem_teknisi.get(position)));
                intent.putExtra("deskripsi", String.valueOf(pem_deskripsi.get(position)));
                intent.putExtra("kesimpulan", String.valueOf(pem_kesimpulan.get(position)));
                intent.putExtra("saran", String.valueOf(pem_saran.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return pem_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namasite_txt, pelaksana_txt, tglawal_txt, jenis_txt;
                //pem_id_txt, tglakhir_txt, teknisi_txt, deskripsi_txt, kesimpulan_txt, saran_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //pem_id_txt = itemView.findViewById(R.id.pem_id_txt);
            namasite_txt = itemView.findViewById(R.id.namasite_txt);
            pelaksana_txt = itemView.findViewById(R.id.pelaksana_txt);
            tglawal_txt = itemView.findViewById(R.id.tglawal_txt);
            //tglakhir_txt = itemView.findViewById(R.id.tglakhir_txt);
            jenis_txt = itemView.findViewById(R.id.jenis_txt);
            //teknisi_txt = itemView.findViewById(R.id.teknisi_txt);
            //deskripsi_txt = itemView.findViewById(R.id.deskripsi_txt);
            //kesimpulan_txt = itemView.findViewById(R.id.kesimpulan_txt);
            //saran_txt = itemView.findViewById(R.id.saran_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
