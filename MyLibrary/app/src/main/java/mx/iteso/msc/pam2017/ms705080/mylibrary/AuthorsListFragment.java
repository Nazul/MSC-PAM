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

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import mx.iteso.msc.pam2017.ms705080.mylibrary.DataAccess.*;

import static java.util.Arrays.asList;

/**
 * Created by Mario_Contreras on 3/4/2017.
 */

public class AuthorsListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Author> authors = DatabaseHandler.getInstance(getActivity().getBaseContext())
                .getAllAuthors();
        ArrayAdapter<Author> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                authors);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Author author = (Author) l.getItemAtPosition(position);
        if (author == null)
            return;
        AuthorsDetailsFragment fragment = (AuthorsDetailsFragment) getFragmentManager()
                .findFragmentById(R.id.authorsDetailsFragment);
        List<Book> books = DatabaseHandler.getInstance(getActivity().getBaseContext()).getBooksByAuthorId(author.getId());
        if (fragment != null && fragment.isInLayout()) {
            fragment.setList(books);
        } else {
            Intent intent = new Intent(getActivity().getApplicationContext(), AuthorsDetailsActivity.class);
            intent.putExtra("authorId", author.getId());
            startActivity(intent);
        }
    }
}

// EOF
