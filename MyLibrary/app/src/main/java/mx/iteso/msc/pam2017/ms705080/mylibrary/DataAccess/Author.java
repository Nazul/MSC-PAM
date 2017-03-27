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

package mx.iteso.msc.pam2017.ms705080.mylibrary.DataAccess;

import java.text.MessageFormat;

/**
 * Created by Mario_Contreras on 3/25/2017.
 */

public class Author {
    private int id;
    private String name;
    private String country;
    private String extra;

    public Author() {
        // Empty
    }

    public Author(int id, String name, String country, String extra) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.extra = extra;
    }

    public Author(String name, String country, String extra) {
        this.name = name;
        this.country = country;
        this.extra = extra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        if (id == 0)
            return name;
        return MessageFormat.format("{0}.- {1}", id, name);
    }
}

// EOF
