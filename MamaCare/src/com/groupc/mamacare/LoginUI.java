package com.groupc.mamacare;

import com.groupc.mamacare.model.User;
import com.groupc.mamacare.service.UserService;
import com.groupc.mamacare.service.impl.UserServiceImpl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is the class responsible for starting up the application
 * @author ckabahuma
 *
 */
public class LoginUI extends ActionBarActivity implements View.OnClickListener {
	
	    private EditText textInputEditTextUserName;
	    private EditText textInputEditTextPassword;

	    private Button btnSignIn;
	    
	    private UserService userService;
	    
	    private User loginUser;
	    

	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_ui);
		
		userService = new UserServiceImpl(getApplicationContext());
		//Start the login Page
		initLoginUI();
	}

	private void initLoginUI() {
		textInputEditTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
 
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        
     // Set OnClick Listener on SignUp button 
        btnSignIn.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			//Get user entered text
			String userName=textInputEditTextUserName.getText().toString();
			String password=textInputEditTextPassword.getText().toString();
			
			if(textInputEditTextPassword == null || textInputEditTextUserName == null){
				showMessageToUserLong("Please fill in both Username and Password!");
			}else{
				loginUser = userService.getUser(userName, password);
				if(loginUser == null){
					showMessageToUserLong("Invalid Username or Password!");
				}else{
					showMessageToUserLong("Congrats: Login Successfull");
					
					// Display the MamaCare dashboard
					startActivity(new Intent(getApplicationContext(), DashboardUI.class));
				}
			}
 
			}

		});
 
	}

	
	private void showMessageToUserLong(String message) {
		Toast.makeText(LoginUI.this, message, Toast.LENGTH_LONG).show();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_ui, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		
	}
}
