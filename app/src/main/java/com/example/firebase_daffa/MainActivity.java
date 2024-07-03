package com.example.firebase_daffa;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private Button btCreateDB;
    private Button btViewDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreateDB=(Button)findViewById(R.id.bt_createdata);
        btViewDB=(Button)findViewById(R.id.bt_viewdata);
        btCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, DBCreatee.class);
                startActivity(moveIntent);
            }
        });



        btViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
//Kelas untuk View Data
//startActivity(DBReadActivity.getActIntent(MainActivity.this));
        }
        });
    }
}