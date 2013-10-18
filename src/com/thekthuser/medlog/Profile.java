package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.inputmethod.InputMethodManager;

import android.util.Log;

public class Profile extends BaseActivity
{

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

    public void togglePrescriber(View view) {
        View prescriber = findViewById(R.id.prescriber);
        if (prescriber.getVisibility() == View.VISIBLE) {
            prescriber.setVisibility(View.GONE);
        } else {
            prescriber.setVisibility(View.VISIBLE);
        }
    }

    public void toggleSelfEdit(View view) {
        TextView name_show = (TextView) findViewById(R.id.self_name_show);
        TextView name_edit = (TextView) findViewById(R.id.self_name_edit);
        name_edit.setText(name_show.getText());
        TextView address_show = (TextView) findViewById(R.id.self_address_show);
        TextView address_edit = (TextView) findViewById(R.id.self_address_edit);
        address_edit.setText(address_show.getText());
        TextView phone_show = (TextView) findViewById(R.id.self_phone_show);
        TextView phone_edit = (TextView) findViewById(R.id.self_phone_edit);
        phone_edit.setText(phone_show.getText());
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

    public void togglePrescriberEdit(View view) {
        TextView name_show = (TextView) findViewById(R.id.prescriber_name_show);
        TextView name_edit = (TextView) findViewById(R.id.prescriber_name_edit);
        name_edit.setText(name_show.getText());
        TextView address_show = (TextView) findViewById(R.id.prescriber_address_show);
        TextView address_edit = (TextView) findViewById(R.id.prescriber_address_edit);
        address_edit.setText(address_show.getText());
        TextView phone_show = (TextView) findViewById(R.id.prescriber_phone_show);
        TextView phone_edit = (TextView) findViewById(R.id.prescriber_phone_edit);
        phone_edit.setText(phone_show.getText());
        TextView specialty_show = (TextView) findViewById(R.id.prescriber_specialty_show);
        TextView specialty_edit = (TextView) findViewById(R.id.prescriber_specialty_edit);
        specialty_edit.setText(specialty_show.getText());


        View edit = findViewById(R.id.edit_prescriber_button);
        View update = findViewById(R.id.update_prescriber_button);

        name_show.setVisibility(View.GONE);
        name_edit.setVisibility(View.VISIBLE);
        address_show.setVisibility(View.GONE);
        address_edit.setVisibility(View.VISIBLE);
        phone_show.setVisibility(View.GONE);
        phone_edit.setVisibility(View.VISIBLE);
        specialty_show.setVisibility(View.GONE);
        specialty_edit.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        update.setVisibility(View.VISIBLE);
    }

    public void togglePharmacy(View view) {
        View pharmacy = findViewById(R.id.pharmacy);
        if (pharmacy.getVisibility() == View.VISIBLE) {
            pharmacy.setVisibility(View.GONE);
        } else {
            pharmacy.setVisibility(View.VISIBLE);
        }
    }

    public void togglePharmacyEdit(View view) {
        TextView name_show = (TextView) findViewById(R.id.pharmacy_name_show);
        TextView name_edit = (TextView) findViewById(R.id.pharmacy_name_edit);
        name_edit.setText(name_show.getText());
        TextView address_show = (TextView) findViewById(R.id.pharmacy_address_show);
        TextView address_edit = (TextView) findViewById(R.id.pharmacy_address_edit);
        address_edit.setText(address_show.getText());
        TextView phone_show = (TextView) findViewById(R.id.pharmacy_phone_show);
        TextView phone_edit = (TextView) findViewById(R.id.pharmacy_phone_edit);
        phone_edit.setText(phone_show.getText());
        TextView hours_show = (TextView) findViewById(R.id.pharmacy_hours_show);
        TextView hours_edit = (TextView) findViewById(R.id.pharmacy_hours_edit);
        hours_edit.setText(hours_show.getText());


        View edit = findViewById(R.id.edit_pharmacy_button);
        View update = findViewById(R.id.update_pharmacy_button);

        name_show.setVisibility(View.GONE);
        name_edit.setVisibility(View.VISIBLE);
        address_show.setVisibility(View.GONE);
        address_edit.setVisibility(View.VISIBLE);
        phone_show.setVisibility(View.GONE);
        phone_edit.setVisibility(View.VISIBLE);
        hours_show.setVisibility(View.GONE);
        hours_edit.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        update.setVisibility(View.VISIBLE);
    }

    public void updateSelf(View view) {
        EditText name = (EditText) findViewById(R.id.self_name_edit);
        EditText address = (EditText) findViewById(R.id.self_address_edit);
        EditText phone = (EditText) findViewById(R.id.self_phone_edit);

        /*self_general.setName(name.getText().toString());
        self_general.setAddress(address.getText().toString());
        self_general.setPhone(phone.getText().toString());*/
        //Log.i("updateSelf", Integer.toString(self_general.getId()));
        //Log.i("updateSelf", self_general.getName());
        /*GeneralInfo sGeneral = new GeneralInfo(name.getText().toString(), address.getText().toString(), phone.getText().toString());
        Self self = new Self(sGeneral);

        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        pAdapter.addSelf(self);
        Log.i("updateSelf", "after addSelf");
        pAdapter.close();*/
        View vSelf = findViewById(R.id.self);
        Self self = (Self) vSelf.getTag();

        GeneralInfo sGeneral = self.getGeneralInfo();
        sGeneral.setName(name.getText().toString());
        sGeneral.setAddress(address.getText().toString());
        sGeneral.setPhone(phone.getText().toString());
        self.setGeneralInfo(sGeneral);

        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        pAdapter.addSelf(self);
        pAdapter.close();

        /*InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        try {
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        //above hides keyboard
        } catch (Exception e) {
            throw new RuntimeException("hideSoftInputFromWindow", e);
        }*/
        finish();
        startActivity(getIntent());
        //temporary fix

        setContentView(R.layout.profile);
        refreshProfile(findViewById(R.layout.profile));
    }

    public void updatePrescriber(View view) {
        EditText name = (EditText) findViewById(R.id.prescriber_name_edit);
        EditText address = (EditText) findViewById(R.id.prescriber_address_edit);
        EditText phone = (EditText) findViewById(R.id.prescriber_phone_edit);
        EditText specialty = (EditText) findViewById(R.id.prescriber_specialty_edit);

        View vPrescriber = findViewById(R.id.prescriber);
        Prescriber prescriber = (Prescriber) vPrescriber.getTag();

        GeneralInfo pGeneral = prescriber.getGeneralInfo();
        pGeneral.setName(name.getText().toString());
        pGeneral.setAddress(address.getText().toString());
        pGeneral.setPhone(phone.getText().toString());
        prescriber.setSpecialty(specialty.getText().toString());
        prescriber.setGeneralInfo(pGeneral);

        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        pAdapter.addPrescriber(prescriber);
        pAdapter.close();

        finish();
        startActivity(getIntent());
        //temporary fix
        
        setContentView(R.layout.profile);
        refreshProfile(findViewById(R.layout.profile));

        /*GeneralInfo pGeneral = new GeneralInfo(name.getText().toString(), address.getText().toString(), phone.getText().toString());
        Prescriber prescriber = new Prescriber(specialty.getText().toString(), pGeneral);

        ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        pAdapter.addPrescriber(prescriber);
        pAdapter.close();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        //above hides keyboard

        setContentView(R.layout.profile);
        refreshProfile(findViewById(R.layout.profile));*/
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
        Self self = pAdapter.getSelf(1);
        Prescriber prescriber = pAdapter.getPrescriber(1);
        pAdapter.close();

        GeneralInfo sGeneral = self.getGeneralInfo();
        View sView = findViewById(R.id.self);
        //Log.i("refreshprofile getSelf", "self id = " + Integer.toString(self.getId()));
        sView.setTag(self);

        GeneralInfo pGeneral = prescriber.getGeneralInfo();
        View pView = findViewById(R.id.prescriber);
        pView.setTag(prescriber);

        TextView self_name = (TextView) findViewById(R.id.self_name_show);
        TextView self_address = (TextView) findViewById(R.id.self_address_show);
        TextView self_phone = (TextView) findViewById(R.id.self_phone_show);
        self_name.setText(sGeneral.getName());
        self_address.setText(sGeneral.getAddress());
        self_phone.setText(sGeneral.getPhone());

        TextView prescriber_name = (TextView) findViewById(R.id.prescriber_name_show);
        TextView prescriber_address = (TextView) findViewById(R.id.prescriber_address_show);
        TextView prescriber_phone = (TextView) findViewById(R.id.prescriber_phone_show);
        TextView prescriber_specialty = (TextView) findViewById(R.id.prescriber_specialty_show);
        prescriber_name.setText(pGeneral.getName());
        prescriber_address.setText(pGeneral.getAddress());
        prescriber_phone.setText(pGeneral.getPhone());
        prescriber_specialty.setText(prescriber.getSpecialty());

        /*ProfileAdapter pAdapter = new ProfileAdapter(getApplicationContext());
        pAdapter.open();
        Log.i("refresh", "before get");
        Self self = pAdapter.getSelf(1);
        GeneralInfo self_general = self.getGeneralInfo();
        Log.i("refresh", "after self get, before pres get");

        Prescriber prescriber = pAdapter.getPrescriber(1);
        GeneralInfo prescriber_general = prescriber.getGeneralInfo();
        pAdapter.close();

        TextView self_name = (TextView) findViewById(R.id.self_name_show);
        TextView self_address = (TextView) findViewById(R.id.self_address_show);
        TextView self_phone = (TextView) findViewById(R.id.self_phone_show);

        self_name.setText(self_general.getName());
        self_address.setText(self_general.getAddress());
        self_phone.setText(self_general.getPhone());


        TextView prescriber_name = (TextView) findViewById(R.id.prescriber_name_show);
        TextView prescriber_address = (TextView) findViewById(R.id.prescriber_address_show);
        TextView prescriber_phone = (TextView) findViewById(R.id.prescriber_phone_show);
        TextView prescriber_specialty = (TextView) findViewById(R.id.prescriber_specialty_show);

        prescriber_name.setText(prescriber_general.getName());
        prescriber_address.setText(prescriber_general.getAddress());
        prescriber_phone.setText(prescriber_general.getPhone());
        prescriber_specialty.setText(prescriber.getSpecialty());*/
    }
}
