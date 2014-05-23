package com.example.abratesesamo;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import com.example.abratesesamo.Client.ClientThread;

public class MainActivity extends FragmentActivity implements ActivityInteraction{
	
	SharedPreferences sh_Pref;
	String login, pass;
	Editor toEdit;
	EditText etLogin, etPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.getActionBar().hide();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		SharedPreferences prefs;
		public PlaceholderFragment() {
			super();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			final ActivityInteraction act = (ActivityInteraction) getActivity();
			
			prefs = act.getSharedPrefs();
			
			final EditText etNome = (EditText) rootView
					.findViewById(R.id.et_login);
			final EditText etSenha = (EditText) rootView
					.findViewById(R.id.et_pass);
			final Button enviar = (Button) rootView
					.findViewById(R.id.bt_enviar);
			
			etNome.setText(prefs.getString("Username",""));
			etSenha.setText(prefs.getString("Password",""));
			
			enviar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Log.i("#######", "foi");
					// captura campo nome
					String login = etNome.getText().toString();
					// captura campo senha
					String senha = etSenha.getText().toString();
					
					act.putSharedPreferences(login, senha);
					new Thread(new ClientThread(login, senha)).start();
					

				}
			});
			
			return rootView;
		}
	}
	

	@Override
	public void putSharedPreferences(String... values) {
		// TODO Auto-generated method stub
		
		login = values[0];
		pass = values[1];

		sh_Pref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
		toEdit = sh_Pref.edit();
		toEdit.putString("Username", login);
		toEdit.putString("Password", pass);
		toEdit.commit();
	}

	@Override
	public SharedPreferences getSharedPrefs() {
		// TODO Auto-generated method stub
		return 	getSharedPreferences("Login Credentials", MODE_PRIVATE);
	}
	
	

}


interface ActivityInteraction{
	public void putSharedPreferences(String... values);
	public SharedPreferences getSharedPrefs();
}