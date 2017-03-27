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
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Mario_Contreras on 3/25/2017.
 */

public class Book {
    private int id;
    private int authorId;
    private int publisherId;
    private String title;
    private String publication;
    private long published;
    private String country;

    public Book() {
        // Empty
    }

    public Book(int id, int authorId, int publisherId, String title, String publication,
                long published, String country) {
        this.id = id;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.title = title;
        this.publication = publication;
        this.published = published;
        this.country = country;
    }

    public Book(int authorId, int publisherId, String title, String publication,
                long published, String country) {
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.title = title;
        this.publication = publication;
        this.published = published;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}.- {1}", id, title);
    }
}

// EOF
