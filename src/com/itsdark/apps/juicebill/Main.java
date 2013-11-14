package com.itsdark.apps.juicebill;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {

	private Context mcontext = this;
	private String LOG_TAG = "Activity";
	final String[] names = new String[] { "Mausambi(18)", "Watermelon(16)",
			"Chocolate(20)", "Choco-Coffee(20)", "Grape(18)", "Banana(16)",
			"Coffee(20)" };
	final int[] cost = new int[] { 18, 16, 20, 20, 18, 16, 20 };

	ListView juiceList;
	TextView tvTotalCount;
	Button clearBal;

	SharedPreferences sharedPrefs;

	static int totalcost = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, sharedPrefs.getString("cost", "0"));
		// tvTotalCount.setText(sharedPrefs.getString("cost", "NULL"));
		totalcost = Integer.parseInt(sharedPrefs.getString("cost", "0"));
		super.onResume();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor mEditor = sharedPrefs.edit();
		mEditor.putInt("mausambi", 0);
		mEditor.putInt("watermelon", 0);
		mEditor.putInt("chocolate", 0);
		mEditor.putInt("choco-coffee", 0);
		mEditor.putInt("grape", 0);
		mEditor.putInt("banana", 0);
		mEditor.putInt("coffee", 0);
		mEditor.putInt("mausambi2", 0);
		mEditor.putInt("watermelon2", 0);
		mEditor.putInt("chocolate2", 0);
		mEditor.putInt("choco-coffee2", 0);
		mEditor.putInt("grape2", 0);
		mEditor.putInt("banana2", 0);
		mEditor.putInt("coffee2", 0);
		mEditor.commit();

		juiceList = (ListView) findViewById(R.id.lvjuices);
		juiceList.setAdapter(new JuiceAdapter(mcontext, names));

		juiceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i(LOG_TAG, "on item click with juice = " + names[position]);
				totalcost = totalcost + cost[position];
				tvTotalCount.setText("Total Cost : "
						+ String.valueOf(totalcost));
				SharedPreferences.Editor mEditor = sharedPrefs.edit();
				mEditor.putString("cost", String.valueOf(totalcost)).commit();

				int count = sharedPrefs.getInt(names[position], 0);
				TextView tvcnt = (TextView) v.findViewById(R.id.tvCount);
				count = count + 1;
				mEditor.putInt(names[position], count).commit();
				tvcnt.setText(String.valueOf(count));

				TextView tvcst = (TextView) v.findViewById(R.id.tvCost);
				mEditor.putInt(names[position] + "2", count * cost[position])
						.commit();
				tvcst.setText(String.valueOf(count * cost[position]));

				Log.i(LOG_TAG,
						String.valueOf(count) + "  "
								+ String.valueOf(count * cost[position]));
			}
		});

		tvTotalCount = (TextView) findViewById(R.id.bTotalCount);
		tvTotalCount.setText("Total Cost : "
				+ sharedPrefs.getString("cost", "0"));

		clearBal = (Button) findViewById(R.id.bClear);
		clearBal.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		confirmDialog();
	}

	private void confirmDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mcontext);

		// set title
		alertDialogBuilder.setTitle("Are you sure?");

		// set dialog message
		alertDialogBuilder
				.setMessage("Click yes to clear balance!")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								clearBalance();
								dialog.cancel();
							}
						})
				.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();
	}

	private void clearBalance() {
		// TODO Auto-generated method stub
		totalcost = 0;
		tvTotalCount.setText("Total Cost : " + String.valueOf(totalcost));
		SharedPreferences.Editor mEditor = sharedPrefs.edit();
		mEditor.putString("cost", String.valueOf(totalcost));

		for (int i = 0; i < names.length; i++) {
			mEditor.putInt(names[i], 0);
			mEditor.putInt(names[i] + "2", 0);
		}
		mEditor.commit();
		for (int i = 0; i < names.length; i++) {
			juiceList.getAdapter().getView(i, null, null);
		}
		juiceList.setAdapter(new JuiceAdapter(mcontext, names));
	}
}
