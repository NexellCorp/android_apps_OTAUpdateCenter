/*
 * Copyright (C) 2012 OTA Update Center
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may only use this file in compliance with the license and provided you are not associated with or are in co-operation anyone by the name 'X Vanderpoel'.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.otaupdater;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class UpdaterAbout extends PreferenceActivity {
    private Preference teamPref;
    private Preference licensePref;
    private Preference sitePref;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.about);

        Preference versionPref = findPreference("about_version");
        try {
            versionPref.setSummary(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            versionPref.setSummary(R.string.about_version_unknown);
        }

        sitePref = findPreference("about_pref");
        teamPref = findPreference("team_pref");
        licensePref = findPreference("license_pref");
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == teamPref) {
            startActivity(new Intent(UpdaterAbout.this, Contributors.class));
        } else if (preference == licensePref) {
            startActivity(new Intent(UpdaterAbout.this, License.class));
        } else if (preference == sitePref) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.otaupdatecenter.pro")));
        } else {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        return true;
    }
}
