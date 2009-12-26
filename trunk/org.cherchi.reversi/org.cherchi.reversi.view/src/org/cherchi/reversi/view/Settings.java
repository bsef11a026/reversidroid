package org.cherchi.reversi.view;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Settings manager
 * 
 * @author Fernando Cherchi
 *
 */
public class Settings extends PreferenceActivity {
	
	/**
	 * adding preferences from res
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.addPreferencesFromResource(R.xml.settings);
	}

}
