package com.Rai.studycenter.settings;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.Rai.studycenter.R;
import com.Rai.studycenter.settings.fragments.Faq;
import com.Rai.studycenter.settings.fragments.Support;
import com.Rai.studycenter.settings.fragments.about;
import com.Rai.studycenter.utils.Utils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_holder);
        switch (getIntent().getStringExtra("EXTRA")) {
            case "openFragment":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new SettingsFragment())
                        .commit();
                break;
            case "openFragment2":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new about())
                        .commit();
                break;
            case "openFragment3":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new Support())
                        .commit();
                break;
            case "openFragment4":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new Faq())
                        .commit();
                break;
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.settings_preferences, rootKey);
            final Preference myPref = (Preference) findPreference("theme");
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {


                    return true;
                }
            });
        }
    }
}