package com.uas_19552011256_mp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "pemeliharaan.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbl_pemeliharaan";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_namasite = "pem_namasite";
    private static final String COLUMN_pelaksana = "pem_pelaksana";
    private static final String COLUMN_tglawal = "pem_tglawal";
    private static final String COLUMN_tglakhir = "pem_tglakhir";
    private static final String COLUMN_jenis = "pem_jenis";
    private static final String COLUMN_teknisi = "pem_teknisi";
    private static final String COLUMN_deskripsi = "pem_deskripsi";
    private static final String COLUMN_kesimpulan = "pem_kesimpulan";
    private static final String COLUMN_saran = "pem_saran";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_namasite + " TEXT, " +
                        COLUMN_pelaksana + " TEXT, " +
                        COLUMN_tglawal + " TEXT, " +
                        COLUMN_tglakhir + " TEXT, " +
                        COLUMN_jenis + " TEXT, " +
                        COLUMN_teknisi + " TEXT, " +
                        COLUMN_deskripsi + " TEXT, " +
                        COLUMN_kesimpulan + " TEXT, " +
                        COLUMN_saran + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void tambah(String namasite, String pelaksana, String tglawal, String tglakhir, String jenis,  String teknisi, String deskripsi, String kesimpulan, String saran){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_namasite, namasite);
        cv.put(COLUMN_pelaksana, pelaksana);
        cv.put(COLUMN_tglawal, tglawal);
        cv.put(COLUMN_tglakhir, tglakhir);
        cv.put(COLUMN_jenis, jenis);
        cv.put(COLUMN_teknisi, teknisi);
        cv.put(COLUMN_deskripsi, deskripsi);
        cv.put(COLUMN_kesimpulan, kesimpulan);
        cv.put(COLUMN_saran, saran);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Data gagal ditambah !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Data berhasil ditambah !", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void ubahData(String row_id, String namasite, String pelaksana, String tglawal, String tglakhir, String jenis, String teknisi, String deskripsi, String kesimpulan, String saran){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_namasite, namasite);
        cv.put(COLUMN_pelaksana, pelaksana);
        cv.put(COLUMN_tglawal, tglawal);
        cv.put(COLUMN_tglakhir, tglakhir);
        cv.put(COLUMN_jenis, jenis);
        cv.put(COLUMN_teknisi, teknisi);
        cv.put(COLUMN_deskripsi, deskripsi);
        cv.put(COLUMN_kesimpulan, kesimpulan);
        cv.put(COLUMN_saran, saran);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Data gagal diubah !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Data berhasil diubah !", Toast.LENGTH_SHORT).show();
        }

    }

    void hapusSatuBaris(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Data gagal dihapus !", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data berhasil dihapus !", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusSemuaData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
