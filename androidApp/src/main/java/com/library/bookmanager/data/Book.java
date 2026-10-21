package com.library.bookmanager.data;

import java.io.Serializable;

public class Book implements Serializable {

    public enum Status {
        UNREAD("Chưa đọc"),
        READING("Đang đọc"),
        READ("Đã đọc");

        public final String label;

        Status(String label) {
            this.label = label;
        }
    }

    private final String title;
    private final String author;
    private final String genre;
    private final String note;
    private Status status;
    private float rating;

    public Book(String title, String author, String genre, Status status, float rating, String note) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.rating = rating;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getNote() {
        return note;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
