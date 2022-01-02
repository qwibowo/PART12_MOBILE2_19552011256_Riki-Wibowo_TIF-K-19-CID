package com.uas_19552011256_mp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class UpdateActivity extends AppCompatActivity {

    EditText namasite_input, pelaksana_input, jenis_input, tglawal_input, tglakhir_input, teknisi_input, deskripsi_input, kesimpulan_input, saran_input;
    Button update_button, delete_button, bt_awal, bt_akhir;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    String id, namasite, pelaksana, tglawal, tglakhir, jenis, teknisi, deskripsi, kesimpulan, saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        namasite_input = findViewById(R.id.namasite_input2);
        pelaksana_input = findViewById(R.id.pelaksana_input2);
        //tglawal_input = findViewById(R.id.tglawal_input2);
        //tglakhir_input = findViewById(R.id.tglakhir_input2);
        jenis_input = findViewById(R.id.jenis_input2);
        teknisi_input = findViewById(R.id.teknisi_input2);
        deskripsi_input = findViewById(R.id.deskripsi_input2);
        kesimpulan_input = findViewById(R.id.kesimpulan_input2);
        saran_input = findViewById(R.id.saran_input2);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tglawal_input = (EditText) findViewById(R.id.tglawal_input2);
        bt_awal = (Button) findViewById(R.id.date_awal);
        bt_awal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showbt_awal();
            }

            private void showbt_awal(){

                /**
                 * Calendar untuk mendapatkan tanggal sekarang
                 */
                Calendar newCalendar = Calendar.getInstance();

                /**
                 * Inisialisasi DatePicker dialog
                 */
                datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        /**
                         * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                         */

                        /**
                         * Set Calendar untuk menampung tanggal yang dipilih
                         */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        /**
                         * Update EditView dengan tanggal yang kita pilih
                         */
                        tglawal_input.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                /**
                 * Tampilkan DatePicker dialog
                 */
                datePickerDialog.show();
            }
        });

        tglakhir_input = (EditText) findViewById(R.id.tglakhir_input2);
        bt_akhir = (Button) findViewById(R.id.date_akhir);
        bt_akhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showbt_akhir();
            }

            private void showbt_akhir(){

                /**
                 * Calendar untuk mendapatkan tanggal sekarang
                 */
                Calendar newCalendar = Calendar.getInstance();

                /**
                 * Inisialisasi DatePicker dialog
                 */
                datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        /**
                         * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                         */

                        /**
                         * Set Calendar untuk menampung tanggal yang dipilih
                         */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        /**
                         * Update EditView dengan tanggal yang kita pilih
                         */
                        tglakhir_input.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                /**
                 * Tampilkan DatePicker dialog
                 */
                datePickerDialog.show();
            }
        });


        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(namasite);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                namasite = namasite_input.getText().toString().trim();
                pelaksana = pelaksana_input.getText().toString().trim();
                tglawal = tglawal_input.getText().toString().trim();
                tglakhir = tglakhir_input.getText().toString().trim();
                jenis = jenis_input.getText().toString().trim();
                teknisi = teknisi_input.getText().toString().trim();
                deskripsi = deskripsi_input.getText().toString().trim();
                kesimpulan = kesimpulan_input.getText().toString().trim();
                saran = saran_input.getText().toString().trim();
                myDB.ubahData(id, namasite, pelaksana, tglawal, tglakhir, jenis, teknisi, deskripsi, kesimpulan, saran);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("namasite") && getIntent().hasExtra("pelaksana") && getIntent().hasExtra("tglawal") && getIntent().hasExtra("tglakhir")
                && getIntent().hasExtra("jenis") && getIntent().hasExtra("teknisi") && getIntent().hasExtra("deskripsi") && getIntent().hasExtra("kesimpulan") && getIntent().hasExtra("saran")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            namasite = getIntent().getStringExtra("namasite");
            pelaksana = getIntent().getStringExtra("pelaksana");
            tglawal = getIntent().getStringExtra("tglawal");
            tglakhir = getIntent().getStringExtra("tglakhir");
            jenis = getIntent().getStringExtra("jenis");
            teknisi = getIntent().getStringExtra("teknisi");
            deskripsi = getIntent().getStringExtra("deskripsi");
            kesimpulan = getIntent().getStringExtra("kesimpulan");
            saran = getIntent().getStringExtra("saran");

            //Setting Intent Data
            namasite_input.setText(namasite);
            pelaksana_input.setText(pelaksana);
            tglawal_input.setText(tglawal);
            tglakhir_input.setText(tglakhir);
            jenis_input.setText(jenis);
            teknisi_input.setText(teknisi);
            deskripsi_input.setText(deskripsi);
            kesimpulan_input.setText(kesimpulan);
            saran_input.setText(saran);
            Log.d("stev", namasite+" "+pelaksana+" "+tglawal+" "+tglakhir+" "+jenis+" "+teknisi+" "+deskripsi+" "+kesimpulan+" "+saran);
        }else{
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + namasite + " ?");
        builder.setMessage("Apakah anda yakin akan menghapus data " + namasite + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.hapusSatuBaris(id);
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
