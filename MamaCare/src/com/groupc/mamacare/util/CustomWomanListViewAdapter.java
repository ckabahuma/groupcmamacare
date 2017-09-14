package com.groupc.mamacare.util;

import java.util.List;

import com.groupc.mamacare.R;
import com.groupc.mamacare.model.Woman;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is responsible for laying out the display of women on the
 * dashboard
 * 
 * @author jmpango
 *
 */
public class CustomWomanListViewAdapter extends ArrayAdapter<Woman> {

	private List<Woman> womenList;
	private Context mContext;
	private LayoutInflater layoutInflater;

	public CustomWomanListViewAdapter(Context context, List<Woman> womanListz) {
		super(context, 0, womanListz);
		this.mContext = context;
		this.womenList = womanListz;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		final Woman woman = womenList.get(position);
		if (woman != null) {
			Woman mWoman = (Woman) woman;
			view = layoutInflater.inflate(R.layout.dashboard_woman_row, null);
			TextView textViewWomanName = (TextView) view.findViewById(R.id.textViewWomanName);
			TextView textViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
			TextView textViewStatus = (TextView) view.findViewById(R.id.status);

			if (textViewWomanName != null)
				textViewWomanName.setText(mWoman.getFirstName() + " " + mWoman.getLastName());
			if (textViewAddress != null)
				textViewAddress.setText(mWoman.getAddress());

			/*if (woman.getVisit().size() < 3 && woman.getVisit() != null) {
				if (textViewStatus != null) {
					textViewStatus.setText("(Active)");
				}
			} else {
				if (textViewStatus != null) {
					textViewStatus.setText("(Complete)");
				}
			}*/

			// Activates display of the woman specific dashboard to add visit
			textViewStatus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO: Direct user to a woman specific dashboard to add
					// visits
					Toast.makeText(mContext,
							"You have clicked to view details for: " + woman.getFirstName() + " " + woman.getLastName(),
							Toast.LENGTH_LONG).show();
				}
			});
		}

		return view;
	}
}
