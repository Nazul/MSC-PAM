/*
 *
 * Copyright 2017 Mario Contreras <marioc@nazul.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package mx.iteso.msc.pam2017.ms705080.guitest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner -> Scholarship
        Spinner scholarship = (Spinner) findViewById(R.id.spinScholarship);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.scholarship, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        scholarship.setAdapter(adapter);

        // AutoCompleteTextView -> Favorite Book
        adapter = ArrayAdapter.createFromResource(this, R.array.books, android.R.layout.select_dialog_item);
        AutoCompleteTextView fb = (AutoCompleteTextView) findViewById(R.id.actvFavBook);
        fb.setThreshold(1);
        fb.setAdapter(adapter);

        CheckedTextView ctvSports = (CheckedTextView) findViewById(R.id.ctvSportsPractice);
        ctvSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView sports = (CheckedTextView) v;
                sports.setChecked(!sports.isChecked());
            }
        });

        Button clearButton = (Button) findViewById(R.id.btnClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("")
                        .setMessage(getString(R.string.textConfirmClear))
                        .setPositiveButton(R.string.textClear, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                clearFields();
                            }
                        })
                        .setNegativeButton(R.string.textCancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void clearFields() {
        EditText etName = (EditText) findViewById(R.id.editName);
        EditText etPhone = (EditText) findViewById(R.id.editPhone);
        Spinner spScholarship = (Spinner) findViewById(R.id.spinScholarship);
        RadioButton rbMale = (RadioButton) findViewById(R.id.rbMale);
        AutoCompleteTextView actvFavBook = (AutoCompleteTextView) findViewById(R.id.actvFavBook);
        CheckedTextView ctvSports = (CheckedTextView) findViewById(R.id.ctvSportsPractice);

        etName.setText("");
        etPhone.setText("");
        spScholarship.setSelection(0);
        rbMale.setChecked(true);
        actvFavBook.setText("");
        ctvSports.setChecked(false);

        Toast.makeText(getApplicationContext(), getString(R.string.textAllFieldsCleared), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                EditText etName = (EditText) findViewById(R.id.editName);
                EditText etPhone = (EditText) findViewById(R.id.editPhone);
                Spinner spScholarship = (Spinner) findViewById(R.id.spinScholarship);
                RadioButton rbMale = (RadioButton) findViewById(R.id.rbMale);
                AutoCompleteTextView actvFavBook = (AutoCompleteTextView) findViewById(R.id.actvFavBook);
                CheckedTextView ctvSports = (CheckedTextView) findViewById(R.id.ctvSportsPractice);
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String scholarship = spScholarship.getSelectedItem().toString();
                String gender = rbMale.isChecked() ? getString(R.string.textMale) : getString(R.string.textFemale);
                String favBook = actvFavBook.getEditableText().toString().isEmpty() ? getString(R.string.textNoFavBook) : actvFavBook.getEditableText().toString();
                String sports = ctvSports.isChecked() ? getString(R.string.textYes) : getString(R.string.textNo);

                String msg = java.text.MessageFormat.format(getString(R.string.textDataSaved), "\n", name, phone, scholarship, gender, favBook, sports);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

// EOF
