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

            /*ArrayList meds = createGroupList();
            ArrayList prescriptions = createChildList(meds);
            SimpleExpandableListAdapter xList = 
                new SimpleExpandableListAdapter(
                    this,
                    //createGroupList(),
                    meds,
                    //R.layout.manage_medication_expanded,
                    R.layout.manage_medication,
                    new String[] {"Scientific Name", "Brand Name"},
                    new int[] {R.id.display_scientific_name, R.id.display_brand_name},
                    //createChildList(),
                    prescriptions,
                    R.layout.manage_prescription,
                    //R.layout.manage_prescription_last,
                    //new String[] {"Sub Item"},
                    new String[] {"Pill Dosage", "Dosage Taken"},
                    new int[] {R.id.display_pill_dosage}
                );
            getExpandableListView().setGroupIndicator(null);
            getExpandableListView().setOnChildClickListener(this);
            setListAdapter(xList);
        } catch (Exception e) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEE " + e.getMessage());
        }*/
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
        /*for (int i = 0; i < meds.size(); i++) {
            //ArrayList<Prescription> pres = mAdapter.getPrescriptionList();
            //meds.add(pres);
        }*/

        mAdapter.close();

        return meds;
    }

    public ArrayList<Medication> dummy_data() {
        /*ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        ArrayList meds = mAdapter.getMedicationList();

        for (int i = 0; i < meds.size(); i++) {
            ArrayList<Prescription> pres = new ArrayList();
            for (int j = 0; j < 3; j++) {
                Prescription child = new Prescription(i, "pill", "dosage");
                pres.add(child);
            }
            meds.add(pres);
        }
        mAdapter.close();
        return meds;*/
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
        return result;*/
            
        ArrayList result = new ArrayList();
        for(int i = 0; i < 4; i++) {
            ArrayList secList = new ArrayList();
            for(int n = 0; n < 3; n++) {
                HashMap child = new HashMap();
                //child.put("Sub Item", "Sub" + Integer.toString(n));
                child.put("Pill Dosage", "pill dosage");
                child.put("Dosage Taken", "dosage taken");
            secList.add( child );
            }
        result.add(secList);
        }
        return result;

        /*ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        ArrayList result = new ArrayList();
        for (int i = 0; i < meds.size(); i++) {
            ArrayList test = mAdapter.getPrescriptionList(i);
            result.add(test);
        }
        mAdapter.close();
        return result;*/

        /*ManageAdapter mAdapter = new ManageAdapter(getApplicationContext());
        mAdapter.open();
        List prescriptions = mAdapter.getPrescriptionList(count);
        mAdapter.close();
        return prescriptions;*/
    }

    public void togglePrescriberEdit(View view) {
        /*ExpandableListView ev = getExpandableListView();
        long evid = ev.getSelectedPosition();
        Toast.makeText(getBaseContext(), Long.toString(evid), Toast.LENGTH_LONG).show();*/
        /*View newPres = findViewById(R.id.test);
        if (newPres.getVisibility() == View.VISIBLE) {
            newPres.setVisibility(View.GONE);
        } else {
            newPres.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getBaseContext(), Integer.toString(groupPosition), Toast.LENGTH_LONG).show();
        return true;
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

}
