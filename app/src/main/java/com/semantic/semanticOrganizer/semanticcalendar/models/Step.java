package com.semantic.semanticOrganizer.semanticcalendar.models;

/**
 * Created by Admin on 16-09-2014.
 */
public class Step {
    private long id;
    private String noteText;
    private String noteTitle;

    public Step(){

    }
    public Step(int id, String taskText){
        this.id = id;
        this.noteText = taskText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return noteText;
    }

    public void setText(String taskText) {
        this.noteText = taskText;
    }
    public String getTitle() {
        return noteTitle;
    }

    public void setTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return noteText;
    }
}
