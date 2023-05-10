package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewData extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<ContactModel> contactList;
    private ListView listView;
    private Button addcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_data);
        databaseHelper = new DatabaseHelper(this);

        listView = findViewById(R.id.listViewContacts);
        addcontact=findViewById(R.id.enterNewContact);
        // Fetch contacts from the database
        contactList = databaseHelper.fetchContacts();

        // Create an ArrayList of contact names for display
        ArrayList<String> contactDetails = new ArrayList<>();
        for (ContactModel contact : contactList) {
            String details = contact.name + "\n\n" + contact.number+"\n\n"+contact.age;
            contactDetails.add(details);
        }

        // Create a custom ArrayAdapter to display contact details
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactDetails);
        listView.setAdapter(adapter);

        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListViewData.this,MainActivity.class));
            }
        });

    }
}