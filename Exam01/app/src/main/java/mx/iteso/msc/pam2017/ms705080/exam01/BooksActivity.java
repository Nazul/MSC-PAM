/*
 * Copyright 2017 Mario Contreras <marioc@nazul.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package mx.iteso.msc.pam2017.ms705080.exam01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Mario_Contreras on 3/4/2017.
 */

public class BooksActivity extends AppCompatActivity {
    private EditText etBookName;
    private Spinner spAuthors;
    private Spinner spPublishers;
    private EditText etPublicationName;
    private Spinner spCountries;
    private EditText etYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        initUI();
    }

    private void initUI() {
        etBookName = (EditText) findViewById(R.id.etBookName);
        spAuthors = (Spinner) findViewById(R.id.spAuthors);
        spPublishers = (Spinner) findViewById(R.id.spPublishers);
        etPublicationName = (EditText) findViewById(R.id.etPublicationName);
        spCountries = (Spinner) findViewById(R.id.spCountries);
        etYear = (EditText) findViewById(R.id.etYear);

        String[] authors = getResources().getStringArray(R.array.Authors);
        String[] publishers = getResources().getStringArray(R.array.Publishers);
        String[] countries = getResources().getStringArray(R.array.Countries);

        HintAdapter haAuthors = new HintAdapter(this, android.R.layout.simple_list_item_1, authors);
        HintAdapter haPublishers = new HintAdapter(this, android.R.layout.simple_list_item_1, publishers);
        HintAdapter haCountries = new HintAdapter(this, android.R.layout.simple_list_item_1, countries);

        spAuthors.setAdapter(haAuthors);
        spPublishers.setAdapter(haPublishers);
        spCountries.setAdapter(haCountries);

        spAuthors.setSelection(haAuthors.getCount());
        spPublishers.setSelection(haPublishers.getCount());
        spCountries.setSelection(haCountries.getCount());
    }

    public void btnSave(View view) {
        if (dataValid()) {
            AppGlobals.getInstance().getBooks().add(spAuthors.getSelectedItemPosition() + "|" + etBookName.getText());
            Toast.makeText(BooksActivity.this, R.string.toastBookSaved, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(BooksActivity.this, R.string.toastInvalidData, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean dataValid() {
        boolean retval = true;

        retval = retval && !etBookName.getText().toString().isEmpty();
        retval = retval && spAuthors.getSelectedItemId() < spAuthors.getCount();
        retval = retval && spPublishers.getSelectedItemId() < spPublishers.getCount();
        retval = retval && !etPublicationName.getText().toString().isEmpty();
        retval = retval && spCountries.getSelectedItemId() < spCountries.getCount();
        retval = retval && !etYear.getText().toString().isEmpty();

        return retval;
    }
}

// EOF
