package com.example.demo.model;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private int publisherId;
    private int authorId;
    private int genreId;
    private LocalDate releaseDate;

    // Конструктор
    public Book(int id, String title, int publisherId, int authorId, int genreId, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.publisherId = publisherId;
        this.authorId = authorId;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
