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

package mx.iteso.msc.pam2017.ms705080.mylibrary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.iteso.msc.pam2017.ms705080.mylibrary.DataAccess.*;

/**
 * Created by Mario_Contreras on 3/4/2017.
 */

public class BooksActivity extends AppCompatActivity {
    private EditText etBookName;
    private Spinner spAuthors;
    private Spinner spPublishers;
    private EditText etPublicationName;
    private Spinner spCountries;
    private EditText etPublished;
    private Book book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        initUI();
        book = new Book();
    }

    private void initUI() {
        etBookName = (EditText) findViewById(R.id.etBookName);
        spAuthors = (Spinner) findViewById(R.id.spAuthors);
        spPublishers = (Spinner) findViewById(R.id.spPublishers);
        etPublicationName = (EditText) findViewById(R.id.etPublicationName);
        spCountries = (Spinner) findViewById(R.id.spCountries);
        etPublished = (EditText) findViewById(R.id.etPublished);

        List<Author> authors = DatabaseHandler.getInstance(getBaseContext()).getAllAuthors();
        List<Publisher> publishers = DatabaseHandler.getInstance(getBaseContext()).getAllPublishers();
        List<String> countries = Arrays.asList(getResources().getStringArray(R.array.Countries));

        authors.add(new Author(getResources().getString(R.string.spHintAuthorName), "", ""));
        publishers.add(new Publisher(getResources().getString(R.string.spHintPublisher)));

        HintAdapter haAuthors = new HintAdapter(this, android.R.layout.simple_list_item_1, authors);
        HintAdapter haPublishers = new HintAdapter(this, android.R.layout.simple_list_item_1, publishers);
        HintAdapter haCountries = new HintAdapter(this, android.R.layout.simple_list_item_1, countries);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setSubtitle(R.string.stBooks);

        spAuthors.setAdapter(haAuthors);
        spPublishers.setAdapter(haPublishers);
        spCountries.setAdapter(haCountries);

        spAuthors.setSelection(haAuthors.getCount());
        spPublishers.setSelection(haPublishers.getCount());
        spCountries.setSelection(haCountries.getCount());
    }

    public void btnSave(View view) {
        if (dataValid()) {
            book.setTitle(etBookName.getText().toString());
            book.setAuthorId(((Author)spAuthors.getSelectedItem()).getId());
            book.setPublisherId(((Publisher)spPublishers.getSelectedItem()).getId());
            book.setPublication(etPublicationName.getText().toString());
            book.setCountry(spCountries.getSelectedItem().toString());
            DatabaseHandler.getInstance(getBaseContext()).addBook(book);
            Toast.makeText(BooksActivity.this, R.string.toastBookSaved, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else {
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
        retval = retval && !etPublished.getText().toString().isEmpty();

        return retval;
    }

    public void showDatePickerDialog(View view) {
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(BooksActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                Date date = new Date(selectedYear, selectedMonth, selectedDay);
                book.setPublished(date.getTime());
                etPublished.setText(date.toString());
            }
        }, year, month, day);
        dpd.setTitle(getResources().getString(R.string.dpdTitle));
        dpd.show();
    }
}

// EOF
