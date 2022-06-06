package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class informationpage extends AppCompatActivity {

    hoteldatabase database;
    Spinner roomType;
    Button Calculate,ubdate,delete,read,close,clear;
    EditText name,email,phone,Adulnum,days, id;

    TextView result;

    int price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationpage);

        roomType = (Spinner) findViewById(R.id.roomtype);

        Calculate = (Button) findViewById(R.id.Calculate);
        ubdate = (Button) findViewById(R.id.Update);
        delete = (Button) findViewById(R.id.Delete);
        read = (Button) findViewById(R.id.Read);
        close = (Button) findViewById(R.id.Close);
        clear = (Button) findViewById(R.id.Clear);

        name=(EditText)findViewById(R.id.name1);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.numpey);
        Adulnum=(EditText)findViewById(R.id.numperad);
        days=(EditText)findViewById(R.id.numpdays);
        id = (EditText)findViewById(R.id.id);

        database = new hoteldatabase(this);

        result = (TextView) findViewById(R.id.result);

        ArrayList<String> Rooms = new ArrayList<String>();
        Rooms.add("Single bad");
        Rooms.add("Single bad with sea view");
        Rooms.add("Double bad");
        Rooms.add("Double bad with normal view");
        Rooms.add("two bedroom apartment");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Rooms);
        roomType.setAdapter(arrayAdapter);


        CalculateDat();
        UpdateDat();
        DeleteDat();
        ClearDat();
        CloseDat();
        ReadDat();

    }

    public void CalculateDat()
    {
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int numofDays = Integer.parseInt(days.getText().toString());
                String type = roomType.getSelectedItem().toString();

                if(type == "Single bad")
                    price = 15 * numofDays;
                else if (type =="Single bad with sea view")
                    price = 23 * numofDays;
                else if (type =="Double bad")
                    price = 28 * numofDays;
                else if (type =="two bedroom apartment")
                    price = 35 * numofDays;
                else if (type == "Double bad with normal view")
                    price = 40 * numofDays;
                else
                    price = 0 * numofDays;

                result.setText("The booking price  is: " + price);

                boolean inseted = database.insertData(name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), Adulnum.getText().toString(),days.getText().toString(),
                        String.valueOf(price));

                         if(inseted==true)
                             Toast.makeText(informationpage.this,"data inserted",Toast.LENGTH_LONG).show();
                         else
                             Toast.makeText(informationpage.this,"data not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
//
    public void UpdateDat()
    {
        ubdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean updated = database.updateData(id.getText().toString(), name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), Integer.parseInt(Adulnum.getText().toString()),Integer.parseInt(days.getText().toString()),
                        price);

                if(updated==true)
                    Toast.makeText(informationpage.this,"data updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(informationpage.this,"data no updated",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void DeleteDat()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Integer del=database.deleteData(id.getText().toString());

                if(del>0)
                Toast.makeText(informationpage.this,"Data deleted",Toast.LENGTH_LONG).show();
                else
                Toast.makeText(informationpage.this,"Data not delete",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ClearDat()
    {
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name.setText("");
                email.setText("");
                phone.setText("");
                Adulnum.setText("");
                days.setText("");
                id.setText("");
            }
        });
    }
    public void CloseDat()
    {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                System.exit(0);
            }
        });
    }
    public void ReadDat()
    {
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor r=database.getAllData();
                if (r.getCount()==0)
                {
                    showMessage("error","nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (r.moveToNext())
                {
                    buffer.append("ID: " + r.getString(0) + "\n");
                    buffer.append("Name: " + r.getString(1) + "\n");
                    buffer.append("Email: " + r.getString(2) + "\n");
                    buffer.append("Phone: " + r.getString(3) + "\n");
                    buffer.append("Adult No: " + r.getString(4) + "\n");
                    buffer.append("Days: " + r.getString(5) + "\n");
                    buffer.append("Price: " + r.getString(6) + "\n");

                }
                showMessage("user information",buffer.toString());
            }
        });
    }

    private void showMessage(String error, String nothing_found)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(error);
        alert.setMessage(nothing_found);
        alert.show();
    }
}