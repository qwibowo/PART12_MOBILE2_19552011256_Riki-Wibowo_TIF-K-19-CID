package com.uas_19552011256_mp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> pem_id, pem_namasite, pem_pelaksana, pem_tglawal, pem_tglakhir, pem_jenis, pem_teknisi, pem_deskripsi, pem_kesimpulan, pem_saran;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        pem_id = new ArrayList<>();
        pem_namasite = new ArrayList<>();
        pem_pelaksana = new ArrayList<>();
        pem_tglawal = new ArrayList<>();
        pem_tglakhir = new ArrayList<>();
        pem_jenis = new ArrayList<>();
        pem_teknisi = new ArrayList<>();
        pem_deskripsi = new ArrayList<>();
        pem_kesimpulan = new ArrayList<>();
        pem_saran = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, pem_id, pem_namasite, pem_pelaksana, pem_tglawal, pem_tglakhir, pem_jenis, pem_teknisi, pem_deskripsi, pem_kesimpulan, pem_saran);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                pem_id.add(cursor.getString(0));
                pem_namasite.add(cursor.getString(1));
                pem_pelaksana.add(cursor.getString(2));
                pem_tglawal.add(cursor.getString(3));
                pem_tglakhir.add(cursor.getString(4));
                pem_jenis.add(cursor.getString(5));
                pem_teknisi.add(cursor.getString(6));
                pem_deskripsi.add(cursor.getString(7));
                pem_kesimpulan.add(cursor.getString(8));
                pem_saran.add(cursor.getString(9));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Semua Data ?");
        builder.setMessage("Apakah anda yakin akan menghapus semua data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.hapusSemuaData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
