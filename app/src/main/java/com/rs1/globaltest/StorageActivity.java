package com.rs1.globaltest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Database;

import android.view.View;

public class StorageActivity extends AppCompatActivity {
    public static final String CATEGORY = "com.rs1.globaltest.CAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startViewShPref(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(CATEGORY, "Shared_Preferences");
        startActivity(intent);
    }

    public void startViewIntSto(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(CATEGORY, "Internal_Storage");
        startActivity(intent);
    }

    public void startViewCache(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(CATEGORY, "Cache");
        startActivity(intent);
    }

    public void startViewExtSto(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(CATEGORY, "External_Storage");
        startActivity(intent);
    }


    public void startAddShPref(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(CATEGORY, "Shared_Preferences");
        startActivity(intent);
    }
    public void startAddIntSto(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(CATEGORY, "Internal_Storage");
        startActivity(intent);
    }

    public void startAddCache(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(CATEGORY, "Cache");
        startActivity(intent);
    }

    public void startAddExtSto(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(CATEGORY, "External_Storage");
        startActivity(intent);
    }

    public void startDB(View view) {
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

}
