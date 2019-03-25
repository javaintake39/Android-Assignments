package com.example.notesapp.Model;

public class NoteDAO
{
    private String NoteTitle;
    private String NoteBody;

    public NoteDAO()
    {}
    public NoteDAO(String noteTitle, String noteBody) {
        NoteTitle = noteTitle;
        NoteBody = noteBody;
    }

    public String getNoteTitle()
    {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNoteBody() {
        return NoteBody;
    }

    public void setNoteBody(String noteBody) {
        NoteBody = noteBody;
    }
}
