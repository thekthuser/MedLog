package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.util.ArrayList;
import android.widget.ExpandableListView;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.widget.ExpandableListView.OnChildClickListener;
import android.view.LayoutInflater;

import android.widget.SimpleExpandableListAdapter;
import java.util.HashMap;
import java.util.List;

import com.thekthuser.medlog.ManageExpandableListAdapter;
import java.util.Iterator;

import android.widget.LinearLayout;
import android.content.res.Resources;

import android.util.Log;

public class Manage extends BaseListActivity {
    private ManageExpandableListAdapter ExpAdapter;
    private ArrayList<Medication> ExpListItems;
    private ExpandableListView ExpList;

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.manage);
            ExpList = (ExpandableListView) findViewById(android.R.id.list);
            Log.i("AAAAAAAAAAAAAAAAAAA", "create explist");
            //ExpListItems = dummy_data();
            ExpListItems = fetch_data();
            Log.i("AAAAAAAAAAAAAAAAAA", "create dummy_data");
            ExpAdapter = new ManageExpandableListAdapter(Manage.this, 
            ExpListItems);
            Log.i("AAAAAAAAAAAAAAAAAA", "create explist adapter");
            ExpList.setAdapter(ExpAdapter);
            Log.i("AAAAAAAAAAAAAAAAAA", "set adapter");
        } catch (Exception e) {
            Log.i("EEEEEEEEEEEEEEEEE", e.getMessage());
        }

    }

    public ArrayList<Medication> fetch_data(){
        ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        ArrayList<Medication> meds = mAdapter.getMedicationList();

        Iterator<Medication> iterator = meds.iterator();
        while (iterator.hasNext()) {
            Medication m = iterator.next();
            ArrayList<Prescription> pres = mAdapter.getPrescriptionList(m.getId());
            m.setPrescriptions(pres);
        }

        mAdapter.close();

        return meds;
    }

    public ArrayList<Medication> dummy_data() {
        ArrayList<Medication> meds = new ArrayList<Medication>();
        ArrayList<Prescription> pres = new ArrayList<Prescription>();

        Medication group1 = new Medication(1, "sci", "bra");
        Prescription child1 = new Prescription(1, 1, "pill", "dosage");
        pres.add(child1);
        Prescription child2 = new Prescription(1, 2, "pill2", "dosage2");
        pres.add(child2);
        group1.setPrescriptions(pres);

        //ArrayList<Prescription> pres = new ArrayList<Prescription>();
        pres.clear();
        Medication group2 = new Medication(1, "sci2", "bra2");
        Prescription child21 = new Prescription(1, 1, "pill", "dosage");
        pres.add(child21);
        Prescription child22 = new Prescription(1, 2, "pill2", "dosage2");
        pres.add(child22);
        group2.setPrescriptions(pres);
        

        meds.add(group1);
        meds.add(group2);

        return meds;

    }

    public void toggleNewPrescription(View view) {
        String groupId = view.getTag().toString();
        //need to go 2 levels up to find LinearLayout
        View parent = (View) view.getParent().getParent();
        LinearLayout toggle = (LinearLayout) parent.findViewWithTag("newPrescription" + groupId);
        //Toast.makeText(getBaseContext(), toggle.getTag().toString(), Toast.LENGTH_LONG).show();

        if (toggle.getVisibility() == View.VISIBLE) {
            //ideally, this should be View.GONE
            toggle.setVisibility(View.GONE);
        } else {
            toggle.setVisibility(View.VISIBLE);
        }
    }

    public void toggleNewMed(View view) {
        View new_med = findViewById(R.id.new_med);
        Button new_med_button = (Button) findViewById(R.id.toggle_new_med);
        if (new_med.getVisibility() == View.VISIBLE) {
            new_med.setVisibility(View.GONE);
            new_med_button.setVisibility(View.VISIBLE);
        } else {
            new_med.setVisibility(View.VISIBLE);
            new_med_button.setVisibility(View.GONE);
        }
    }

    public void updateMed(View view) {
        EditText scientific = (EditText) findViewById(R.id.scientific_name);
        EditText brand = (EditText) findViewById(R.id.brand_name);
        Medication med = new Medication(scientific.getText().toString(), brand.getText().toString());

        ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        mAdapter.addMedication(med);
        mAdapter.close();
        
        //toggle_new_med(view);
        finish();
        startActivity(getIntent());

    }

    public void updatePrescription(View view) {
        LinearLayout layout = (LinearLayout) view.getParent().getParent();
        Medication med = (Medication) layout.getTag();
        String asdf = (String) Integer.toString(med.getId());
        Toast.makeText(getBaseContext(), "newDosage" + asdf, Toast.LENGTH_LONG).show();

        /*EditText dosage = (EditText) view.findViewWithTag("newDosage" + asdf);
        String aaa = dosage.getText().toString();
        Toast.makeText(getBaseContext(), aaa, Toast.LENGTH_LONG).show();*/


    }

}
