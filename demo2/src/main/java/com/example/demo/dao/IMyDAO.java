package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.*;

public interface IMyDAO {
    // Genres
    void addGenre(String name);
    void updateGenre(int genreId, String newName);
    void deleteGenre(int genreId);
    List<Genre> searchGenres(String keyword);
    public List<Genre> displayAllGenres();

    // Books
    public void addBook(Book book);
    public void updateBook(Book book);
    void deleteBook(int bookId);
    public List<Book> searchBooks(String title);
    public List<Book> displayAllBooks();

    // Publishers
    public void addPublisher(Publisher publisher);
    public void updatePublisher(Publisher publisher);
    void deletePublisher(int publisherId);
    public List<Publisher> searchPublishers(String keyword);
    public List<Publisher> displayAllPublishers();

    // Authors
    void addAuthor(String name, int birthYear);
    void updateAuthor(int authorId, String newName, int newBirthYear);
    void deleteAuthor(int authorId);
    public List<Author> searchAuthors(String name);
    public List<Author> displayAllAuthors();

    // Magazines
    public void addMagazine(Magazine magazine);
    public void updateMagazine(Magazine magazine);
    void deleteMagazine(int magazineId);
    public List<Magazine> searchMagazines(String keyword);
    public List<Magazine> displayAllMagazines();
}
