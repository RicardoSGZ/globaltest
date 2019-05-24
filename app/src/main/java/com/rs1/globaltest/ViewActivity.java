package com.rs1.globaltest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {
    LinearLayout layout;
    SharedPreferences sharedPreferences;
    Toolbar myToolbar;
    Button btn;
    Intent intent;
    Intent intent_home;
    String cat;
    TextView textView;
    String filename_int;
    String filename_ext;
    String filename_cache;
    String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        layout = findViewById(R.id.linear);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        filename_int = getString(R.string.file_internal);
        filename_ext = getString(R.string.file_ext);
        filename_cache = getString(R.string.file_cache);
        group = "Preferencias";
        intent = getIntent();
        cat = intent.getStringExtra(StorageActivity.CATEGORY);
        switch (cat) {
            case "Shared_Preferences":
                viewShPref(layout, group);
                break;
            case "Internal_Storage":
                viewIntSto(layout, filename_int);
                break;
            case "Cache":
                viewCache(layout, filename_cache);
                break;
            default:
                textView = new TextView(this);
                textView.setText("Error");
                layout.addView(textView);
        }
        btn = new Button(this);
        btn.setText(R.string.delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        layout.addView(btn);
    }

    public void viewShPref(LinearLayout layout, String group) {
        sharedPreferences = getSharedPreferences(group, 0);
        Map<String, ?> conjunto = sharedPreferences.getAll();
        for(Map.Entry<String, ?> entry : conjunto.entrySet()){
            textView = new TextView(this);
            textView.setText(entry.getKey() + ":" + entry.getValue());
            textView.setTextSize(16);
            textView.setGravity(Gravity.LEFT);
            textView.setPadding(2,0,0,10);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.addView(textView);
        }
    }

    public void viewIntSto(LinearLayout layout, String filename) {
        try{
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                textView = new TextView(this);
                textView.setText(line);
                textView.setGravity(Gravity.LEFT);
                textView.setPadding(2,0,0,10);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(textView);
            }
            br.close();

        }catch(IOException e){
            textView = new TextView(this);
            textView.setText(e.toString());
            layout.addView(textView);
        }
    }

    public void viewCache(LinearLayout layout, String filename) {
        try{
            FileInputStream fis = new FileInputStream(new File(getCacheDir(), filename));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                textView = new TextView(this);
                textView.setText(line);
                textView.setGravity(Gravity.LEFT);
                textView.setPadding(2,0,0,10);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(textView);
            }
            br.close();

        }catch(IOException e){
            textView = new TextView(this);
            textView.setText(e.toString());
            layout.addView(textView);
        }
    }


    public void delete() {
        intent = getIntent();
        cat = intent.getStringExtra(StorageActivity.CATEGORY);
        switch(cat){
            case "Shared_Preferences":
                delShPref();
                break;
            default:
                System.out.println("Categoria no conocida");
        }
    }

    public void delShPref() {
        sharedPreferences = getSharedPreferences("Preferencias", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        intent_home = new Intent(this, StorageActivity.class);
        startActivity(intent_home);

    }
}