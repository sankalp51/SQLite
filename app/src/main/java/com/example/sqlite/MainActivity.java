package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextName,editTextPhone,editTextAge;
    Button buttonAdd,contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper helper=new DatabaseHelper(this);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonAdd = findViewById(R.id.buttonAdd);
        editTextAge=findViewById(R.id.editTextAge);
        contactList=findViewById(R.id.buttonContacts);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editTextName.getText().toString().trim();
                String phone=editTextPhone.getText().toString().trim();
                String age=editTextAge.getText().toString().trim();
                if(name.isEmpty()){
                    editTextName.setError("This field is compulsory");
                    editTextName.requestFocus();
                }
                else if(phone.isEmpty()){
                    editTextPhone.setError("This field is compulsory");
                    editTextPhone.requestFocus();
                }
                else if(age.isEmpty()){
                    editTextAge.setError("This field is compulsory");
                    editTextAge.requestFocus();
                }
                else{
                    helper.addContact(name,phone,age);
                    Toast.makeText(MainActivity.this, "Successfully saved contact", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,ListViewData.class));
                }
            }
        });
        contactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ListViewData.class));
            }
        });
    }
}