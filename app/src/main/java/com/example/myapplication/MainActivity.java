package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HOME=(Button)findViewById(R.id.HOME);

        HOME.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,informationpage.class);
              startActivity(intent);

          }
      });

       // Toast.makeText(this, "Welcome to Lab",Toast.LENGTH_LONG).show();
    }
}