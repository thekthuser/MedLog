package com.thekthuser.medlog;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.BaseExpandableListAdapter;
import com.thekthuser.medlog.Medication;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.content.res.Resources;

import android.util.Log;

public class ManageExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Medication> groups;

    public ManageExpandableListAdapter(Context context, 
    ArrayList<Medication> groups) {
        this.context = context;
        this.groups = groups;
    }

    public void addItem(Prescription item, Medication group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<Prescription> ch = groups.get(index).getPrescriptions();
        ch.add(item);
        groups.get(index).setPrescriptions(ch);
    }

    public Prescription getChild(int groupPosition, int childPosition) {
        ArrayList<Prescription> chList = 
        groups.get(groupPosition).getPrescriptions();
        return chList.get(childPosition);
    }

    //take another look at this
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(int groupPosition, int childPosition, 
    boolean isLastChild, View view, ViewGroup parent) {
        Prescription child = (Prescription) getChild(groupPosition, 
        childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) 
            context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.manage_prescription, null);
        }
        //populate xml here
        TextView pill_dosage = (TextView) view.findViewById(R.id.display_pill_dosage);
        TextView dosage_taken = (TextView) view.findViewById(R.id.display_dosage_taken);
        pill_dosage.setText(child.getPillDosage());
        dosage_taken.setText(child.getDosageTaken());

        return view;
    }

    public int getChildrenCount(int groupPosition) {
        ArrayList<Prescription> chList = groups.get(groupPosition).
        getPrescriptions();
        return chList.size();
    }

    public Medication getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isLastChild, 
    View view, ViewGroup parent) {
        Medication group = (Medication) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater inf = (LayoutInflater)
            context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.manage_medication, null);
        }

        TextView brand_name = (TextView) view.findViewById(R.id.display_brand_name);
        TextView scientific_name = (TextView) view.findViewById(R.id.display_scientific_name);
        ImageView button = (ImageView) view.findViewById(R.id.toggleNewPrescription);
        LinearLayout toggle = (LinearLayout) view.findViewById(R.id.newPrescription);
        //ImageView open = (ImageView) view.findViewById(R.id.openMedication);
        //ImageView close = (ImageView) view.findViewById(R.id.closeMedication);
        toggle.setTag("newPrescription" + group.getId());
        //open.setTag("openMedication" + Integer.toString(groupPosition));
        //close.setTag("closeMedication" + Integer.toString(groupPosition));

        EditText dosage = (EditText) view.findViewById(R.id.dosage_taken_edit);
        dosage.setTag("newDosage" + group.getId());
        
        LinearLayout medication = (LinearLayout) view.findViewById(R.id.medication);
        medication.setTag(group);
        

        brand_name.setText(group.getBrandName());
        scientific_name.setText(group.getScientificName());
        button.setTag(group.getId());

        return view;
    }

    public boolean hasStableIds() {
        //what does this do?
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        //what does this do?
        return true;
    }
}
