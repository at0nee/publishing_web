package com.example.demo.dao;

import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDAO implements IMyDAO {
    private List<Genre> genres = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Publisher> publishers = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Magazine> magazines = new ArrayList<>();

    // Genres
    @Override
    public void addGenre(String name) {
        int newId = genres.size() + 1;
        genres.add(new Genre(newId, name));
    }

    @Override
    public void updateGenre(int genreId, String newName) {
        for (Genre genre : genres) {
            if (genre.getId() == genreId) {
                genre.setName(newName);
            }
        }
    }

    @Override
    public void deleteGenre(int genreId) {
        genres.removeIf(genre -> genre.getId() == genreId);
    }

    @Override
    public List<Genre> searchGenres(String keyword) {
        List<Genre> result = new ArrayList<>();
        for (Genre genre : genres) {
            if (genre.getName().contains(keyword)) {
                result.add(genre);
            }
        }
        return result;
    }

    @Override
    public List<Genre> displayAllGenres() {
        return genres;
    }

    // Books
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                b.setTitle(book.getTitle());
                b.setPublisherId(book.getPublisherId());
            }
        }
    }

    @Override
    public void deleteBook(int bookId) {
        books.removeIf(book -> book.getId() == bookId);
    }

    @Override
    public List<Book> searchBooks(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> displayAllBooks() {
        return books;
    }

    // Publishers
    @Override
    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        for (Publisher p : publishers) {
            if (p.getId() == publisher.getId()) {
                p.setName(publisher.getName());
                p.setLocation(publisher.getLocation());
            }
        }
    }

    @Override
    public void deletePublisher(int publisherId) {
        publishers.removeIf(publisher -> publisher.getId() == publisherId);
    }

    @Override
    public List<Publisher> searchPublishers(String keyword) {
        List<Publisher> result = new ArrayList<>();
        for (Publisher publisher : publishers) {
            if (publisher.getName().contains(keyword)) {
                result.add(publisher);
            }
        }
        return result;
    }

    @Override
    public List<Publisher> displayAllPublishers() {
        return publishers;
    }

    // Authors
    @Override
    public void addAuthor(String name, int birthYear) {
        int newId = authors.size() + 1;
        authors.add(new Author(newId, name, birthYear));
    }

    @Override
    public void updateAuthor(int authorId, String newName, int newBirthYear) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                author.setName(newName);
                author.setBirthYear(newBirthYear);
            }
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        authors.removeIf(author -> author.getId() == authorId);
    }

    @Override
    public List<Author> searchAuthors(String name) {
        List<Author> result = new ArrayList<>();
        for (Author author : authors) {
            if (author.getName().contains(name)) {
                result.add(author);
            }
        }
        return result;
    }

    @Override
    public List<Author> displayAllAuthors() {
        return authors;
    }

    // Magazines
    @Override
    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        for (Magazine m : magazines) {
            if (m.getId() == magazine.getId()) {
                m.setTitle(magazine.getTitle());
                m.setPublisherId(magazine.getPublisherId());
            }
        }
    }

    @Override
    public void deleteMagazine(int magazineId) {
        magazines.removeIf(magazine -> magazine.getId() == magazineId);
    }

    @Override
    public List<Magazine> searchMagazines(String keyword) {
        List<Magazine> result = new ArrayList<>();
        for (Magazine magazine : magazines) {
            if (magazine.getTitle().contains(keyword)) {
                result.add(magazine);
            }
        }
        return result;
    }

    @Override
    public List<Magazine> displayAllMagazines() {
        return magazines;
    }
}
