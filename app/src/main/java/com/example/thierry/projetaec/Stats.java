package com.example.thierry.projetaec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Stats extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        web = (WebView)findViewById(R.id.webview);
        web.loadUrl("https://www.google.ca");//Insérer l'adresse du site
    }
}
