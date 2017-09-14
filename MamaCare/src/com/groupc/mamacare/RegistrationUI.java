package com.groupc.mamacare;

import java.util.List;

import com.groupc.mamacare.model.Woman;
import com.groupc.mamacare.service.MamaCareService;
import com.groupc.mamacare.service.impl.MamaCareServiceImpl;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is responsible for registering a women into MamaCare
 * 
 * @author jmpango
 *
 */
public class RegistrationUI extends Activity {

	private TextView dashboardTitleTxtField;

	private EditText firstNameTxtField;
	private EditText lastNameTxtField;
	private EditText ageTxtField;
	private EditText addressTxtField;

	private Button saveWomanButton;

	private MamaCareService mamaCareService;

	private Woman woman;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration_ui);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mamaCareService = new MamaCareServiceImpl(getApplicationContext());

		initRegistrationUI();

	}

	/**
	 * This method is responsible for creating the registrationUI
	 */
	@SuppressWarnings("deprecation")
	private void initRegistrationUI() {
		dashboardTitleTxtField = (TextView) findViewById(R.id.title_one_txt);
		firstNameTxtField = (EditText) findViewById(R.id.editTextFirstName);
		lastNameTxtField = (EditText) findViewById(R.id.editTextLastName);
		ageTxtField = (EditText) findViewById(R.id.editTextAge);
		addressTxtField = (EditText) findViewById(R.id.editTextAddress);

		saveWomanButton = (Button) findViewById(R.id.saveWomanBtn);

		dashboardTitleTxtField.setText(Html.fromHtml("Registration Form"));

		saveWomanButton.setOnClickListener(new SaveWomanButtonClickListner());

	}

	/*
	 * This method is called when the addWomanButton is clicked
	 */
	private class SaveWomanButtonClickListner implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			// Populate the woman object with filled in values
			String firstName = firstNameTxtField.getText().toString();
			String lastName = lastNameTxtField.getText().toString();
			String age = ageTxtField.getText().toString();
			String address = addressTxtField.getText().toString();

			if (firstName.equalsIgnoreCase("") || lastName.equalsIgnoreCase("")) {
				Toast.makeText(getBaseContext(), "You CAN'T save. Missing FirstName or LastName", Toast.LENGTH_LONG)
						.show();
			} else {

				// first retrieve the number of women records in the DB
				List<Woman> womenInDatabase = mamaCareService.getWomen();
				int womanId = 1;

				if (womenInDatabase.size() > 0 && womenInDatabase != null) {
					womanId = womenInDatabase.size() + 1;
				}

				woman = new Woman(womanId, firstName, lastName, Integer.parseInt(age), address);

				// save to database
				womenInDatabase = mamaCareService.saveWoman(woman);

				if (womenInDatabase.size() > 0 && womenInDatabase != null) {
					Toast.makeText(getBaseContext(), womenInDatabase.size() + " women records saved!",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getBaseContext(), "No record saved", Toast.LENGTH_LONG).show();
				}

				// clear the registration form for new entry
				lastNameTxtField.setText("");
				firstNameTxtField.setText("");
				ageTxtField.setText("");
				addressTxtField.setText("");

			}

		}
	}
}
