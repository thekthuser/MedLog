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

    /*public void togglePatient (View view) {
        findViewById(R.id.patient_info).setVisibility(view.GONE);
    }*/

    public void toggleSelf(View view) {
        //findViewById(R.id.self).setVisibility(View.INVISIBLE);
        View self = findViewById(R.id.self);
        if (self.getVisibility() == View.VISIBLE) {
            self.setVisibility(View.GONE);
        } else {
            self.setVisibility(View.VISIBLE);
        }
    }

    public void toggleSelfEdit(View view) {
        View name_show = findViewById(R.id.self_name_show);
        View name_edit = findViewById(R.id.self_name_edit);
        View address_show = findViewById(R.id.self_address_show);
        View address_edit = findViewById(R.id.self_address_edit);
        View phone_show = findViewById(R.id.self_phone_show);
        View phone_edit = findViewById(R.id.self_phone_edit);
        View edit = findViewById(R.id.edit_self_button);
        View update = findViewById(R.id.update_self_button);

        name_show.setVisibility(View.GONE);
        name_edit.setVisibility(View.VISIBLE);
        address_show.setVisibility(View.GONE);
        address_edit.setVisibility(View.VISIBLE);
        phone_show.setVisibility(View.GONE);
        phone_edit.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        update.setVisibility(View.VISIBLE);

        
    }

    public void updateSelf(View view) {
    }

    public void updateProfile(View view) {
        //EditText name = (EditText) findViewById(R.id.edit_name);
        //EditText address = (EditText) findViewById(R.id.edit_address);
        //EditText phone = (EditText) findViewById(R.id.edit_phone);
        //Toast.makeText(getApplicationContext(), name.getText() + " " + address.getText() + " " + phone.getText(), Toast.LENGTH_LONG).show();

        //TextView outname = (TextView) findViewById(R.id.profile_name);
        //outname.setText(name.getText());
       // findViewById(R.id.profile_name).setText(name.getText());
    }
}
