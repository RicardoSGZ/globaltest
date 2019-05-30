package com.rs1.globaltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        String url = getString(R.string.remote_image_url);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.imageView2);
        new remoteAsync(url, imageView).execute();


    }

    class remoteAsync extends AsyncTask<Void, Void, Bitmap> {
        String url;
        ImageView imageView;

        remoteAsync(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(Void... params) {
            try{
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.setUseCaches(true);
                connection.connect();
                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }catch(Exception e){
                Toast.makeText(ImagesActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }


    }
}
