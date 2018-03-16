package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The type Detail view activity.
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, emailField,businessField,provinceField,addressField,numberField;
    Contact receivedPersonInfo;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        database = FirebaseDatabase.getInstance().getReference("Contact");
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        businessField = (EditText) findViewById(R.id.business);
        provinceField = (EditText) findViewById(R.id.province);
        addressField = (EditText) findViewById(R.id.address);
        numberField = (EditText) findViewById(R.id.number);


        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            businessField.setText(receivedPersonInfo.business);
            provinceField.setText(receivedPersonInfo.province);
            addressField.setText(receivedPersonInfo.address);
            numberField.setText(receivedPersonInfo.number);
        }
    }

    /**
     * Update contact.
     *
     * @param v the v
     */
    public void updateContact(View v){
        String uid = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String business = businessField.getText().toString();
        String province = provinceField.getText().toString();
        String address = addressField.getText().toString();
        String number = numberField.getText().toString();

        Contact Contact1 = new Contact(uid,name,number,email,business,province,address);
        database.child(uid).setValue(Contact1);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Erase contact.
     *
     * @param v the v
     */
    public void eraseContact(View v)
    {
        String uid = receivedPersonInfo.uid;
        database.child(uid).removeValue();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
