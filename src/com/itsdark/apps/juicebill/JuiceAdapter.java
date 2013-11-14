package com.itsdark.apps.juicebill;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class JuiceAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	SharedPreferences sharedPrefs;

	public JuiceAdapter(Context context, String[] values) {
		super(context, R.layout.singlejuice, values);
		this.context = context;
		this.values = values;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.singlejuice, parent, false);
		TextView name = (TextView) rowView.findViewById(R.id.tvName);
		TextView count = (TextView) rowView.findViewById(R.id.tvCount);
	
		name.setText(values[position]);
		
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		count.setText(String.valueOf(sharedPrefs.getInt(values[position], 0)));
		
		TextView cost = (TextView) rowView.findViewById(R.id.tvCost);
		cost.setText(String.valueOf(sharedPrefs.getInt(values[position]+"2", 0)));
		return rowView;
	}
}
