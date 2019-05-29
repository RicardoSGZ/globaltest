package com.rs1.globaltest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    public void changeUrl(View view) {
        EditText urlText = findViewById(R.id.urlInput);
        String url = urlText.getText().toString();
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(url);
    }

    public void backButton(View view) {
        WebView webView = findViewById(R.id.webView);
        webView.goBack();
    }

    public void nextButton(View view) {
        WebView webView = findViewById(R.id.webView);
        webView.goForward();
    }
}
