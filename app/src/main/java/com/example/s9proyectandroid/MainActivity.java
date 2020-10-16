package com.example.s9proyectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements OnMessageListener, View.OnClickListener {

    private ImageButton btnMalteada;
    private ImageButton btnBurger;
    private ImageButton btnFries;
    private ImageButton btnPizza;

    private UDPConnection udp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMalteada = findViewById(R.id.btnMalteada);
        btnBurger = findViewById(R.id.btnBurger);
        btnFries = findViewById(R.id.btnFries);
        btnPizza = findViewById(R.id.btnPizza);

        udp = new UDPConnection();
        udp.setObserver(this);
        udp.start();

        btnMalteada.setOnClickListener(this);
        btnBurger.setOnClickListener(this);
        btnFries.setOnClickListener(this);
        btnPizza.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Gson gson = new Gson();
        String line;
        AddOrder add = null;
        switch (view.getId()){

            case R.id.btnMalteada:
                add = new AddOrder("Malteada");
                break;
            case R.id.btnBurger:
                add = new AddOrder("Burger");
                break;
            case R.id.btnFries:
                add = new AddOrder("Fries");
                break;
            case R.id.btnPizza:
                add = new AddOrder("Pizza");
                break;

        }

        line = gson.toJson(add);
        udp.sendMessages(line);
    }

    @Override
    public void statusOrder(String onDisplay) {
        runOnUiThread(
                () ->{
                    Log.e("Mensaje",onDisplay);
                    Toast.makeText(getApplicationContext(),onDisplay,Toast.LENGTH_LONG).show();
                }
        );

    }

    @Override
    public void newOrder(String product) {

    }


}