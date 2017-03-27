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

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.iteso.msc.pam2017.ms705080.mylibrary.DataAccess.Book;
import mx.iteso.msc.pam2017.ms705080.mylibrary.DataAccess.DatabaseHandler;

/**
 * Created by Mario_Contreras on 3/4/2017.
 */

public class AuthorsDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If device orientation is not in landscape mode, do not continue
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.fragment_authors_details);
        initUI();
    }

    private void initUI() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setSubtitle(R.string.stAuthors);

        Bundle extras = getIntent().getExtras();
        ListView lv = (ListView) findViewById(R.id.lvBooks);
        List<Book> books = null;
        if (extras != null) {
            int authorId = extras.getInt("authorId");
            books = DatabaseHandler.getInstance(getBaseContext()).getBooksByAuthorId(authorId);
        }
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                books);
        lv.setAdapter(adapter);
    }
}

// EOF
