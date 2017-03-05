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

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mario_Contreras on 3/4/2017.
 */

public class AppGlobals {
    private static final AppGlobals ourInstance = new AppGlobals();
    private ArrayList<String> books;

    public static AppGlobals getInstance() {
        return ourInstance;
    }

    private AppGlobals() {
        books = new ArrayList<>(Arrays.asList(App.getContext().getResources().getStringArray(R.array.Books)));
    }

    public ArrayList<String> getBooks() {
        return this.books;
    }
}

// EOF
