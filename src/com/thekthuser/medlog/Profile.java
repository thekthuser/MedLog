package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;

public class Profile extends BaseActivity
{

    private GeneralInfo self_general;
    //should be private?

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        refreshProfile(findViewById(R.layout.profile));

    }

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
        EditText name = (EditText) findViewById(R.id.self_name_edit);
        EditText address = (EditText) findViewById(R.id.self_address_edit);
        EditText phone = (EditText) findViewById(R.id.self_phone_edit);

        self_general.setName(name.getText().toString());
        self_general.setAddress(address.getText().toString());
        self_general.setPhone(phone.getText().toString());
        Log.i("updateSelf", Integer.toString(self_general.getId()));
        Log.i("updateSelf", self_general.getName());

        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        pAdapter.addSelf(self_general);
        pAdapter.close();
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

    public void refreshProfile(View view) {
        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();

        self_general = pAdapter.getGeneralInfo(1);
        //change this later
        pAdapter.close();

        TextView self_name = (TextView) findViewById(R.id.self_name_show);
        TextView self_address = (TextView) findViewById(R.id.self_address_show);
        TextView self_phone = (TextView) findViewById(R.id.self_phone_show);

        self_name.setText(self_general.getName());
        self_address.setText(self_general.getAddress());
        self_phone.setText(self_general.getPhone());
    }
}
