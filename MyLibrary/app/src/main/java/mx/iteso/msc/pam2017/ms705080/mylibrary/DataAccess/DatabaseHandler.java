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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mario_Contreras on 3/25/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // Meta
    public static final String DATABASE_NAME = "MyLibrary";
    public static final int DATABASE_VERSION = 1;
    // Tables
    public static final String TABLE_AUTHOR = "authors";
    public static final String TABLE_PUBLISHER = "publishers";
    public static final String TABLE_BOOK = "books";
    // Columns: Authors
    public static final String COL_AUTHOR_ID = "AuthorId";
    public static final String COL_AUTHOR_NAME = "AuthorName";
    public static final String COL_AUTHOR_COUNTRY = "AuthorCountry";
    public static final String COL_AUTHOR_EXTRA = "AuthorExtra";
    // Columns: Publishers
    public static final String COL_PUBLISHER_ID = "PublisherId";
    public static final String COL_PUBLISHER_NAME = "PublisherName";
    // Columns: Books
    public static final String COL_BOOK_ID = "BookId";
    public static final String COL_BOOK_AUTHOR_ID = "AuthorId";
    public static final String COL_BOOK_PUBLISHER_ID = "PublisherId";
    public static final String COL_BOOK_TITLE = "BookTitle";
    public static final String COL_BOOK_PUBLICATION = "BookPublication";
    public static final String COL_BOOK_PUBLISHED = "BookPublished";
    public static final String COL_BOOK_COUNTRY = "BookCountry";
    // Local instance
    private static DatabaseHandler dataBaseHandler;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (dataBaseHandler == null) {
            dataBaseHandler = new DatabaseHandler(context.getApplicationContext());
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //// Create tables
        // Authors
        String CREATE_TABLE_AUTHOR = "CREATE TABLE " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + COL_AUTHOR_NAME + " TEXT NOT NULL, "
                + COL_AUTHOR_COUNTRY + " TEXT NULL DEFAULT '', "
                + COL_AUTHOR_EXTRA + " TEXT NULL DEFAULT '');";
        db.execSQL(CREATE_TABLE_AUTHOR);
        // Publishers
        String CREATE_TABLE_PUBLISHER = "CREATE TABLE " + TABLE_PUBLISHER + " ("
                + COL_PUBLISHER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + COL_PUBLISHER_NAME + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_PUBLISHER);
        // Books
        String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "("
                + COL_BOOK_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COL_BOOK_AUTHOR_ID + " INTEGER NOT NULL, "
                + COL_BOOK_PUBLISHER_ID + " INTEGER NOT NULL, "
                + COL_BOOK_TITLE + " TEXT NOT NULL, "
                + COL_BOOK_PUBLICATION + " TEXT NOT NULL, "
                + COL_BOOK_PUBLISHED + " INTEGER NOT NULL, "
                + COL_BOOK_COUNTRY + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_BOOK);

        //region Initial values
        // Authors
        db.execSQL("INSERT INTO " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_NAME + ", " + COL_AUTHOR_COUNTRY + ", " + COL_AUTHOR_EXTRA + ") " +
                "VALUES ('Arthur Conan Doyle', 'England', '');");
        db.execSQL("INSERT INTO " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_NAME + ", " + COL_AUTHOR_COUNTRY + ", " + COL_AUTHOR_EXTRA + ") " +
                "VALUES ('Herman Hesse', 'Germany', '');");
        db.execSQL("INSERT INTO " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_NAME + ", " + COL_AUTHOR_COUNTRY + ", " + COL_AUTHOR_EXTRA + ") " +
                "VALUES ('J. K. Rowling', 'England', '');");
        db.execSQL("INSERT INTO " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_NAME + ", " + COL_AUTHOR_COUNTRY + ", " + COL_AUTHOR_EXTRA + ") " +
                "VALUES ('Michael Crichton', 'United States', '');");
        db.execSQL("INSERT INTO " + TABLE_AUTHOR + " ("
                + COL_AUTHOR_NAME + ", " + COL_AUTHOR_COUNTRY + ", " + COL_AUTHOR_EXTRA + ") " +
                "VALUES ('Stephen King', 'United States', '');");
        // Publishers
        db.execSQL("INSERT INTO " + TABLE_PUBLISHER + " (" + COL_PUBLISHER_NAME + ") " +
                "VALUES ('Pearson');");
        db.execSQL("INSERT INTO " + TABLE_PUBLISHER + " (" + COL_PUBLISHER_NAME + ") " +
                "VALUES ('ThompsonReuters');");
        db.execSQL("INSERT INTO " + TABLE_PUBLISHER + " (" + COL_PUBLISHER_NAME + ") " +
                "VALUES ('RELX Group');");
        db.execSQL("INSERT INTO " + TABLE_PUBLISHER + " (" + COL_PUBLISHER_NAME + ") " +
                "VALUES ('Wolters Kluwer');");
        db.execSQL("INSERT INTO " + TABLE_PUBLISHER + " (" + COL_PUBLISHER_NAME + ") " +
                "VALUES ('Penguin Random House');");
        // Books
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (1, 1, 'The Adventures of Sherlock Holmes', " +
                "'Publication 1', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (1, 2, 'The Hound of the Baskervilles', " +
                "'Publication 2', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (1, 3, 'A Study in Scarlet', " +
                "'Publication 3', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (2, 1, 'Steppenwolf', " +
                "'Publication 4', " + (new Date(1976, 06, 12)).getTime() + ", 'Germany');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (2, 2, 'Demian', " +
                "'Publication 5', " + (new Date(1976, 06, 12)).getTime() + ", 'Germany');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (3, 1, 'Harry Potter and the Philosopher''s Stone', " +
                "'Publication 6', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (3, 2, 'Fantastic Beasts and Where to Find Them', " +
                "'Publication 7', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (3, 3, 'The Tales of Beedle the Bard', " +
                "'Publication 8', " + (new Date(1976, 06, 12)).getTime() + ", 'England');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (4, 1, 'Jurassic Park', " +
                "'Publication 9', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (4, 2, 'Timeline', " +
                "'Publication 10', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (4, 3, 'The Andromeda Strain', " +
                "'Publication 11', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (5, 1, 'The Shining', " +
                "'Publication 12', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (5, 2, 'Carrie', " +
                "'Publication 13', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (5, 3, 'Cujo', " +
                "'Publication 14', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " ("
                + COL_BOOK_AUTHOR_ID + ", " + COL_BOOK_PUBLISHER_ID + ", " + COL_BOOK_TITLE + ", "
                + COL_BOOK_PUBLICATION + ", " + COL_BOOK_PUBLISHED + ", " + COL_BOOK_COUNTRY
                + ") " +
                "VALUES (5, 1, 'It', " +
                "'Publication 15', " + (new Date(1976, 06, 12)).getTime() + ", 'United States');");
        //endregion
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not yet required
    }

    //region Publishers
    // Adding new publisher
    public void addPublisher(Publisher publisher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_PUBLISHER_NAME, publisher.getName());

        db.insert(TABLE_PUBLISHER, null, values);
        db.close();
    }

    // Getting single publisher
    public Publisher getPublisher(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PUBLISHER, new String[]{COL_PUBLISHER_ID,
                        COL_PUBLISHER_NAME
                }, COL_PUBLISHER_ID + " = ?",
                new String[]{
                        String.valueOf(id)
                }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Publisher publisher = new Publisher(cursor.getInt(0),
                cursor.getString(1));
        return publisher;
    }

    // Getting All Publishers
    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PUBLISHER + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Publisher publisher = new Publisher();
                publisher.setId(cursor.getInt(0));
                publisher.setName(cursor.getString(1));
                publishers.add(publisher);
            } while (cursor.moveToNext());
        }
        return publishers;
    }

    // Getting publishers Count
    public int getPublishersCount() {
        String countQuery = "SELECT " + COL_PUBLISHER_ID + " FROM " + TABLE_PUBLISHER + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single publisher
    public int updatePublisher(Publisher publisher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_PUBLISHER_NAME, publisher.getName());

        return db.update(TABLE_PUBLISHER, values, COL_PUBLISHER_ID + " = ?",
                new String[]{String.valueOf(publisher.getId())});
    }

    // Deleting single publisher
    public void deletePublisher(Publisher publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUBLISHER, COL_PUBLISHER_ID + " = ?",
                new String[]{String.valueOf(publisher.getId())});
        db.close();
    }
    //endregion

    //region Authors
    // Adding new author
    public void addAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_AUTHOR_NAME, author.getName());
        values.put(COL_AUTHOR_COUNTRY, author.getCountry());
        values.put(COL_AUTHOR_EXTRA, author.getExtra());

        db.insert(TABLE_AUTHOR, null, values);
        db.close();
    }

    // Getting single author
    public Author getAuthor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AUTHOR, new String[]{COL_AUTHOR_ID,
                        COL_AUTHOR_NAME,
                        COL_AUTHOR_COUNTRY,
                        COL_AUTHOR_EXTRA
                }, COL_AUTHOR_ID + " = ?",
                new String[]{
                        String.valueOf(id)
                }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Author author = new Author(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return author;
    }

    // Getting All Authors
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_AUTHOR + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Author author = new Author();
                author.setId(cursor.getInt(0));
                author.setName(cursor.getString(1));
                author.setCountry(cursor.getString(2));
                author.setExtra(cursor.getString(3));

                authors.add(author);
            } while (cursor.moveToNext());
        }
        return authors;
    }

    // Getting authors Count
    public int getAuthorsCount() {
        String countQuery = "SELECT " + COL_AUTHOR_ID + " FROM " + TABLE_AUTHOR + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single author
    public int updateAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_AUTHOR_ID, author.getId());
        values.put(COL_AUTHOR_NAME, author.getName());
        values.put(COL_AUTHOR_COUNTRY, author.getCountry());
        values.put(COL_AUTHOR_EXTRA, author.getExtra());

        return db.update(TABLE_AUTHOR, values, COL_AUTHOR_ID + " = ?",
                new String[]{String.valueOf(author.getId())});
    }

    // Deleting single author
    public void deleteAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUTHOR, COL_AUTHOR_ID + " = ?",
                new String[]{String.valueOf(author.getId())});
        db.close();
    }
    //endregion

    //region Books
    // Adding new book
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_BOOK_AUTHOR_ID, book.getAuthorId());
        values.put(COL_BOOK_PUBLISHER_ID, book.getPublisherId());
        values.put(COL_BOOK_TITLE, book.getTitle());
        values.put(COL_BOOK_PUBLICATION, book.getPublication());
        values.put(COL_BOOK_PUBLISHED, book.getPublished());
        values.put(COL_BOOK_COUNTRY, book.getCountry());

        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    // Getting single book
    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOK, new String[]{COL_BOOK_ID,
                        COL_BOOK_AUTHOR_ID,
                        COL_BOOK_PUBLISHER_ID,
                        COL_BOOK_TITLE,
                        COL_BOOK_PUBLICATION,
                        COL_BOOK_PUBLISHED,
                        COL_BOOK_COUNTRY
                }, COL_BOOK_ID + " = ?",
                new String[]{
                        String.valueOf(id)
                }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6));
        return book;
    }

    // Getting All Books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BOOK + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setAuthorId(cursor.getInt(1));
                book.setPublisherId(cursor.getInt(2));
                book.setTitle(cursor.getString(3));
                book.setPublication(cursor.getString(4));
                book.setPublished(cursor.getLong(5));
                book.setCountry(cursor.getString(6));
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }

    // Getting All Books by Author Id
    public List<Book> getBooksByAuthorId(int authorId) {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOK, new String[]{COL_BOOK_ID,
                        COL_BOOK_AUTHOR_ID,
                        COL_BOOK_PUBLISHER_ID,
                        COL_BOOK_TITLE,
                        COL_BOOK_PUBLICATION,
                        COL_BOOK_PUBLISHED,
                        COL_BOOK_COUNTRY
                }, COL_AUTHOR_ID + " = ?",
                new String[]{
                        String.valueOf(authorId)
                }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setAuthorId(cursor.getInt(1));
                book.setPublisherId(cursor.getInt(2));
                book.setTitle(cursor.getString(3));
                book.setPublication(cursor.getString(4));
                book.setPublished(cursor.getLong(5));
                book.setCountry(cursor.getString(6));
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }

    // Getting books Count
    public int getBooksCount() {
        String countQuery = "SELECT " + COL_BOOK_ID + " FROM " + TABLE_BOOK + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single book
    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_BOOK_AUTHOR_ID, book.getAuthorId());
        values.put(COL_BOOK_PUBLISHER_ID, book.getPublisherId());
        values.put(COL_BOOK_TITLE, book.getTitle());
        values.put(COL_BOOK_PUBLICATION, book.getPublication());
        values.put(COL_BOOK_PUBLISHED, book.getPublished());
        values.put(COL_BOOK_COUNTRY, book.getCountry());

        return db.update(TABLE_BOOK, values, COL_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getId())});
    }

    // Deleting single book
    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, COL_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getId())});
        db.close();
    }
    //endregion
}

// EOF
