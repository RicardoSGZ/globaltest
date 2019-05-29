package com.rs1.globaltest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddActivity extends AppCompatActivity {
    Toolbar myToolbar;
    EditText key_input;
    String key;
    EditText value_input;
    String value;
    String filename_int;
    String filename_cache;
    String filename_ext;
    String group;
    Intent intent;
    String cat;
    SharedPreferences sharedPreferences;
    Intent intent_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void add(View view) {
        key_input = findViewById(R.id.key);
        key = key_input.getText().toString();
        filename_int = getString(R.string.file_internal);
        filename_cache = getString(R.string.file_cache);
        filename_ext = getString(R.string.file_ext);
        value_input = findViewById(R.id.value);
        value = value_input.getText().toString();
        group = "Preferencias";
        intent = getIntent();
        cat = intent.getStringExtra(StorageActivity.CATEGORY);
        switch (cat) {
            case "Shared_Preferences":
                addShared(key, value, group);
                break;

            case "Internal_Storage":
                addIntStorage(filename_int, key, value);
                break;

            case "Cache":
                addCache(filename_cache, key, value);
                break;

            case "External_Storage":
                addExtStorage(this, filename_ext, key, value);
                break;

            case "Database":
                addDB(1, key, value);
                break;

            default:
                Toast.makeText(getApplicationContext() , "Cat no encontrada", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void addShared(String key, String value, String group) {
        sharedPreferences = getSharedPreferences(group, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        intent_home = new Intent(this, StorageActivity.class);
        startActivity(intent_home);
    }

    public void addIntStorage(String filename, String key, String value) {
        String sep = ":";
        try{
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(key.getBytes());
            fos.write(sep.getBytes());
            fos.write(value.getBytes());
            fos.close();
        }catch(IOException e){
            Toast.makeText(getApplicationContext() , e.toString(), Toast.LENGTH_SHORT).show();
        }
        intent_home = new Intent(this, StorageActivity.class);
        startActivity(intent_home);

    }

    public void addCache(String filename, String key, String value) {
        String sep = ":";
        try{
            FileOutputStream fos = new FileOutputStream(new File(getCacheDir(), filename));
            OutputStreamWriter osr = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osr);
            bw.write(key + sep + value);
            bw.close();
        }catch(IOException e){
            Toast.makeText(getApplicationContext() , e.toString(), Toast.LENGTH_SHORT).show();
        }
        intent_home = new Intent(this, StorageActivity.class);
        startActivity(intent_home);
    }

    public void addExtStorage(Context context, String filename, String key, String value) {
        String sep = ":";
        if(ViewActivity.checkExtSto() == 0) {
            try{
                FileOutputStream fos = new FileOutputStream(new File(context.getExternalFilesDir("textos"), filename));
                OutputStreamWriter osr = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osr);
                bw.write(key + sep + value);
                bw.close();
            }catch(IOException e){
                Toast.makeText(getApplicationContext() , e.toString(), Toast.LENGTH_SHORT).show();
            }
            intent_home = new Intent(this, StorageActivity.class);
            startActivity(intent_home);
        }else{
            Toast.makeText(this, "Error con el almacenamiento", Toast.LENGTH_SHORT).show();
        }
    }

    public void addDB(int user_id, String first_name, String last_name) {

    }


}

