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

public class Manage extends BaseListActivity {

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.manage);

            ArrayList meds = createGroupList();
            ArrayList prescriptions = createChildList(meds);
            SimpleExpandableListAdapter xList = 
                new SimpleExpandableListAdapter(
                    this,
                    //createGroupList(),
                    meds,
                    R.layout.manage_medication,
                    new String[] {"Scientific Name", "Brand Name"},
                    new int[] {R.id.display_scientific_name, R.id.display_brand_name},
                    //createChildList(),
                    prescriptions,
                    R.layout.manage_prescription,
                    //new String[] {"Sub Item"},
                    new String[] {"Pill Dosage"},
                    new int[] {R.id.display_prescription}
                );
            getExpandableListView().setGroupIndicator(null);
            setListAdapter(xList);
        } catch (Exception e) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEE " + e.getMessage());
        }
    }

    private ArrayList createGroupList() {
        /*ArrayList result = new ArrayList();
        for(int i = 0; i < 4; i++) {
            HashMap m = new HashMap();
                m.put("Scientific Name", "Scientific Name" + Integer.toString(i));
                m.put("Brand Name", "Brand Name" + Integer.toString(i));
                result.add(m);
        }
        return (List) result;*/
        ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        ArrayList meds = mAdapter.getMedicationList();
        mAdapter.close();

        return meds;

    }

    private ArrayList createChildList(ArrayList meds) {
        /*ArrayList result = new ArrayList();
        for (int i = 0; i < meds.size(); i++) {
            ArrayList childList = new ArrayList();
            Medication asdf = (Medication) meds.get(i);
            int aaa = asdf.getId();
        ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
            childList = mAdapter.getPrescriptionList(aaa);//asdf.getId());
        mAdapter.close();
            result.add(childList);
        }
        return result;/
            
        /*ArrayList result = new ArrayList();
        for(int i = 0; i < 4; i++) {
            ArrayList secList = new ArrayList();
            for(int n = 0; n < 3; n++) {
                HashMap child = new HashMap();
                child.put("Sub Item", "Sub" + Integer.toString(n));
            secList.add( child );
            }
        result.add(secList);
        }
        return result;*/

        ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        ArrayList result = new ArrayList();
        for (int i = 0; i < meds.size(); i++) {
            ArrayList test = mAdapter.getPrescriptionList(i);
            result.add(test);
        }
        mAdapter.close();
        return result;

        /*ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        List prescriptions = mAdapter.getPrescriptionList(count);
        mAdapter.close();
        return prescriptions;*/
    }


    public void toggle_new_med(View view) {
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

    public void update_med(View view) {
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

}
