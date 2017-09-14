package com.groupc.mamacare;

import java.util.List;

import com.groupc.mamacare.model.Woman;
import com.groupc.mamacare.service.MamaCareService;
import com.groupc.mamacare.service.impl.MamaCareServiceImpl;
import com.groupc.mamacare.util.CustomWomanListViewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is responsible for displaying the list of women registered into MamaCare
 * @author jmpango
 *
 */
public class DashboardUI  extends Activity{
	
	private TextView dashboardTitleTxtField;
	private Button addWomanButton, refreshBtn;
	private ListView womanListView;
	private TextView dashboardContentTxtField;
	
	private MamaCareService mamaCareService;
	private List<Woman> womenList; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dashboard_ui);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mamaCareService = new MamaCareServiceImpl(getApplicationContext());
		
		initDashboardUI();
		
	}

	/**
	 * This method is responsible for creating the dashboardUI
	 */
	@SuppressWarnings("deprecation")
	private void initDashboardUI() {
		dashboardTitleTxtField = (TextView) findViewById(R.id.title_one_txt);
		dashboardContentTxtField = (TextView) findViewById(R.id.app_dashboardContent_txt);
		
		addWomanButton = (Button) findViewById(R.id.addWomanBtn);
		refreshBtn = (Button) findViewById(R.id.refreshBtn);
		
		womanListView =  (ListView) findViewById(R.id.listWomanView);
		
		dashboardTitleTxtField.setText(Html.fromHtml("Dashboard"));
		
		addWomanButton.setOnClickListener(new AddWomanButtonClickListner());
		refreshBtn.setOnClickListener(new RefreshButtonClickListner());
		
		//Retrieve all the women in the database.
		womenList = mamaCareService.getWomen();
		
		if(womenList != null && womenList.size() > 0){
			dashboardContentTxtField.setText(Html.fromHtml("Number of women registered: " + womenList.size()));
			womanListView.setAdapter(new CustomWomanListViewAdapter(getApplicationContext(), womenList));
		}else{
			dashboardContentTxtField.setText(Html.fromHtml("No women registered."));
		}
		
	}
	
	/*
	 * This method is called when the addWomanButton is clicked
	 */
	private class AddWomanButtonClickListner implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			startActivity(new Intent(getApplicationContext(), RegistrationUI.class));
		}
	}
	
	/*
	 * This method is called when the refreshWomanButton is clicked to reload list
	 */
	private class RefreshButtonClickListner implements View.OnClickListener {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			//Retrieve all the women in the database.
			womenList = mamaCareService.getWomen();
			
			if(womenList != null && womenList.size() > 0){
				dashboardContentTxtField.setText(Html.fromHtml("Number of women registered: " + womenList.size()));
				womanListView.setAdapter(new CustomWomanListViewAdapter(getApplicationContext(), womenList));
			}else{
				dashboardContentTxtField.setText(Html.fromHtml("No women registered."));
				Toast.makeText(DashboardUI.this, "No women registered.", Toast.LENGTH_LONG).show();
			}
		}
	}
}
