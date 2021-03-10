package com.example.servidoralgoritmodecristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int porta = 9092;
        try {
            Thread t = new Servidor(porta);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}