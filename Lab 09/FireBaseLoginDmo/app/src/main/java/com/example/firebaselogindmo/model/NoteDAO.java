package com.example.firebaselogindmo.model;

import java.io.Serializable;

public class NoteDAO implements Serializable
{
    private String title;
    private String body;

    public NoteDAO()
    {

    }
    public NoteDAO(String title, String body)
    {
        this.title = title;
        this.body = body;
    }
    public String getTitle()
    {
        return title;
    }
    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return title;
    }
}
