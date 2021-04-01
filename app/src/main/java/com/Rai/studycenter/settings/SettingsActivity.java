package com.Rai.studycenter.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.Rai.studycenter.R;
import com.Rai.studycenter.settings.fragments.Faq;
import com.Rai.studycenter.settings.fragments.Support;
import com.Rai.studycenter.settings.fragments.about;
import com.Rai.studycenter.utils.Utils;

import static com.Rai.studycenter.constant.Constant.CollegeKey;
import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_holder);
        sharedPreferences=getSharedPreferences("font", Context.MODE_PRIVATE);
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
        SharedPreferences sharedPreferences;
        Preference myPref;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.settings_preferences, rootKey);
            myPref = (Preference) findPreference("set_font");
            sharedPreferences=getActivity().getSharedPreferences("font", Context.MODE_PRIVATE);
            getDefaultValue();
            myPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setSummary(newValue.toString());
                    Toast.makeText(getContext(), "Value is : "+newValue.toString(), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("fontKey",newValue.toString());
                    //editor.commit();
                    editor.apply();
                    return false;
                }
            });
            /*myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(getContext(), preference.getSummary().toString(), Toast.LENGTH_SHORT).show();

                    return true;
                }
            });*/
        }
        void getDefaultValue(){
            String key=sharedPreferences.getString("fontKey","not found");
            if(key.equals("not found")){
            }
            else {
                myPref.setSummary(key);
                myPref.setDefaultValue(key);
            }

        }
    }
}