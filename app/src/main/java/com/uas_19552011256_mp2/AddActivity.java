package com.uas_19552011256_mp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    EditText tglawal_input, tglakhir_input, teknisi_input, deskripsi_input, kesimpulan_input, saran_input;
    Spinner namasite_input, pelaksana_input, jenis_input;
    Button add_button, bt_awal, bt_akhir;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        namasite_input = findViewById(R.id.namasite_input);
        pelaksana_input = findViewById(R.id.pelaksana_input);
        jenis_input = findViewById(R.id.jenis_input);
        teknisi_input = findViewById(R.id.teknisi_input);
        deskripsi_input = findViewById(R.id.deskripsi_input);
        kesimpulan_input = findViewById(R.id.kesimpulan_input);
        saran_input = findViewById(R.id.saran_input);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tglawal_input = (EditText) findViewById(R.id.tglawal_input);
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
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {

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

        tglakhir_input = (EditText) findViewById(R.id.tglakhir_input);
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
                 * Initiate DatePicker dialog
                 */
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {

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
                         * Update TextView dengan tanggal yang kita pilih
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


        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.tambah(namasite_input.getSelectedItem().toString().trim(),
                        pelaksana_input.getSelectedItem().toString().trim(),
                        tglawal_input.getText().toString().trim(),
                        tglakhir_input.getText().toString().trim(),
                        jenis_input.getSelectedItem().toString().trim(),
                        teknisi_input.getText().toString().trim(),
                        deskripsi_input.getText().toString().trim(),
                        kesimpulan_input.getText().toString().trim(),
                        saran_input.getText().toString().trim());
                        //Integer.valueOf(jenis_input.getText().toString().trim()));
            }
        });

    }
}
