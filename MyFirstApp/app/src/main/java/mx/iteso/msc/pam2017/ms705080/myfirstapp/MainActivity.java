/*
 * Copyright 2017 Mario Contreras - marioc@nazul.net.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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
package mx.iteso.msc.pam2017.ms705080.myfirstapp;

import android.icu.text.MessageFormat;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private String mName, mPhone;
    private EditText etName;
    private EditText etPhone;
    private final String NAME_FIELD = "nameField";
    private final String PHONE_FIELD = "phoneField";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call the superclass first
        super.onCreate(savedInstanceState);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mName = savedInstanceState.getString(NAME_FIELD);
            mPhone = savedInstanceState.getString(PHONE_FIELD);
        }

        setContentView(R.layout.activity_main);
        Log.d("lifecycle","onCreate invoked");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Get a reference for each EditText
        etName = (EditText)findViewById(R.id.editName);
        etPhone = (EditText)findViewById(R.id.editPhone);

        // Save the user's current state
        savedInstanceState.putString(NAME_FIELD, etName.getText().toString());
        savedInstanceState.putString(PHONE_FIELD, etPhone.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Get a reference for each EditText
        etName = (EditText)findViewById(R.id.editName);
        etPhone = (EditText)findViewById(R.id.editPhone);

        // Restore state members from saved instance
        mName = savedInstanceState.getString(NAME_FIELD);
        mPhone = savedInstanceState.getString(PHONE_FIELD);
        etName.setText(mName);
        etPhone.setText(mPhone);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    public void guardar(View view) {
        // Get a reference for each EditText
        etName = (EditText)findViewById(R.id.editName);
        etPhone = (EditText)findViewById(R.id.editPhone);

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        if (name.length() == 0 || phone.length() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.errMissingData), Toast.LENGTH_LONG).show();
            return;
        }
        if (name.equals(etName.getHint()) || phone.equals(etPhone.getHint())) {
            Toast.makeText(getApplicationContext(), getString(R.string.errInvalidData), Toast.LENGTH_LONG).show();
            return;
        }
        // Save
        String msg = java.text.MessageFormat.format(getString(R.string.toastDataSaved), name, phone);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        etName.setText("");
        etPhone.setText("");
    }
}

// EOF
