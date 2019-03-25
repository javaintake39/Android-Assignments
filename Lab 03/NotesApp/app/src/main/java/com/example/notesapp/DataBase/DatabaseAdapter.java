package com.example.notesapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.example.notesapp.Model.NoteDAO;

public class DatabaseAdapter {

    private DatabaseHelper databaseHelper;

    public DatabaseAdapter(Context context)
    {
        databaseHelper = new DatabaseHelper(context);
    }

    // insert New Note in DataBase
    public long insertNote(NoteDAO noteDAO) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // write in DataBase
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_NOTE_TITLE, noteDAO.getNoteTitle());
        contentValues.put(DatabaseHelper.KEY_NOTE_BODY, noteDAO.getNoteBody());
        long result = db.insert(DatabaseHelper.TABLE_NANE, null, contentValues);
        db.close();
        return result;
    }

    public NoteDAO getNote(String noteTitle) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase(); // read From DataBae
        Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NANE + " Where " + DatabaseHelper.KEY_NOTE_TITLE + " like '" + noteTitle + "'", null);
        NoteDAO noteDAO = new NoteDAO();
        while (cursor.moveToNext()) {
            noteDAO.setNoteTitle(cursor.getString(1));
            noteDAO.setNoteBody(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return noteDAO;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "NoteApp.db";

        // Notes table name
        private static final String TABLE_NANE = "notes";

        // Notes Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_NOTE_TITLE = "Note_Title";
        private static final String KEY_NOTE_BODY = "Note_Body";


        DatabaseHelper(@Nullable Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NANE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + KEY_NOTE_TITLE + " TEXT," + KEY_NOTE_BODY + " TEXT" + ")";
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}