package com.example.firebase_daffa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class DBCreateActivity extends AppCompatActivity
{
// variable yang merefers ke Firebase Realtime Database

    private DatabaseReference database;
    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etNik;
    private EditText etNama;
    private Spinner etJa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbcreate);
// inisialisasi fields EditText dan Button
        etNik = (EditText) findViewById(R.id.nik);
        etNama = (EditText) findViewById(R.id.nama_dosen);etJa = (Spinner) findViewById(R.id.spinnerJA);
        btSubmit = (Button) findViewById(R.id.bt_submit);
// mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();
// kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            if(!isEmpty(etNik.getText().toString()) && !isEmpty(etNama.getText().toString()))
                submitDosen(new Dosen(etNik.getText().toString(), etNama.getText().toString(),
                        etJa.getSelectedItem().toString()));
            else
                Snackbar.make(findViewById(R.id.bt_submit), "Data Dosen tidak boleh kosong",Snackbar.LENGTH_LONG).show();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etNama.getWindowToken(), 0);
                                            }
                                            });
    }
    private boolean isEmpty(String s) {
// Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }
    private void updateDosen(Dosen dosen) {
// Next Update
    }
    //fungsi Simpan data Dosen
    private void submitDosen(Dosen dosen) {
/**
 * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime
 Database
 * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
 * ketika data berhasil ditambahkan
 */
        database.child("dosen").push().setValue(dosen).addOnSuccessListener(this, new
                        OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNik.setText("");
                etNama.setText("");
                etJa.getSelectedItem().toString()
                ;
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan",Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public static Intent getActIntent(Activity activity) {
// kode untuk pengambilan Intent
        return new Intent(activity, DBCreateActivity.class);
    }
}
