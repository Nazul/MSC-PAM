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

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        ArrayList<String> authors = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Authors)));
        authors.remove(authors.size() - 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                authors);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //String item = (String) getListAdapter().getItem(position);
        AuthorsDetailsFragment fragment = (AuthorsDetailsFragment) getFragmentManager().findFragmentById(R.id.authorsDetailsFragment);
        ArrayList<String> books = new ArrayList<>();
        for (String book : AppGlobals.getInstance().getBooks()) {
            String[] item = book.split("\\|");
            if (Integer.parseInt(item[0]) == position) {
                books.add(item[1]);
            }
        }
        if (fragment != null && fragment.isInLayout()) {
            fragment.setList(books);
        } else {
            Intent intent = new Intent(getActivity().getApplicationContext(), AuthorsDetailsActivity.class);
            intent.putExtra("books", books);
            startActivity(intent);
        }
    }
}

// EOF
