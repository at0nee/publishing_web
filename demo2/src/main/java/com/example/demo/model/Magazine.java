package com.example.demo.model;

import java.time.LocalDate;

public class Magazine {
    private int id;
    private String title;
    private int publisherId;
    private LocalDate releaseDate;

    public Magazine(int id, String title, int publisherId, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.publisherId = publisherId;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
