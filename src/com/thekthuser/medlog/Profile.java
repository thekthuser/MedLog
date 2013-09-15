package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends BaseActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    public void updateProfile(View view) {
        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText address = (EditText) findViewById(R.id.edit_address);
        EditText phone = (EditText) findViewById(R.id.edit_phone);
        //Toast.makeText(getApplicationContext(), name.getText() + " " + address.getText() + " " + phone.getText(), Toast.LENGTH_LONG).show();

        TextView outname = (TextView) findViewById(R.id.profile_name);
        outname.setText(name.getText());
       // findViewById(R.id.profile_name).setText(name.getText());
    }
}
