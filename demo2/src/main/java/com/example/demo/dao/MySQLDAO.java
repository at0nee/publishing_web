package com.example.demo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLDAO implements IMyDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/publishingdb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ===== Genres =====
    @Override
    public void addGenre(String name) {
        String sql = "INSERT INTO Genres (name) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Жанр додано успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при додаванні жанру: " + e.getMessage());
        }
    }

    @Override
    public void updateGenre(int genreId, String newName) {
        String sql = "UPDATE Genres SET name = ? WHERE GenreID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, genreId);
            statement.executeUpdate();
            System.out.println("Жанр оновлено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при оновленні жанру: " + e.getMessage());
        }
    }

    @Override
    public void deleteGenre(int genreId) {
        String sql = "DELETE FROM Genres WHERE GenreID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, genreId);
            statement.executeUpdate();
            System.out.println("Жанр видалено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при видаленні жанру: " + e.getMessage());
        }
    }

    @Override
    public List<Genre> searchGenres(String keyword) {
        String sql = "SELECT * FROM Genres WHERE name LIKE ?";
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Створюємо новий об'єкт Genre, передаючи параметри в конструктор
                Genre genre = new Genre(
                        resultSet.getInt("GenreID"),
                        resultSet.getString("name")
                );
                genres.add(genre); // Додаємо об'єкт до списку
            }

            if (genres.isEmpty()) {
                System.out.println("Не знайдено жанрів, що відповідають критерію.");
            }

        } catch (SQLException e) {
            System.out.println("Помилка при пошуку жанрів: " + e.getMessage());
        }

        return genres;
    }

    @Override
    public List<Genre> displayAllGenres() {
        String sql = "SELECT * FROM Genres";
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Проходимо по результатах і створюємо об'єкти Genre
            while (resultSet.next()) {
                int genreId = resultSet.getInt("GenreID");
                String name = resultSet.getString("name");

                // Створюємо новий об'єкт Genre і додаємо його до списку
                Genre genre = new Genre(genreId, name);
                genres.add(genre);
            }

        } catch (SQLException e) {
            System.out.println("Помилка при перегляді жанрів: " + e.getMessage());
        }
        return genres; // Повертаємо список жанрів
    }


    // ===== Books =====
    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO Books (title, PublisherID, AuthorID, GenreID, release_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPublisherId());
            statement.setInt(3, book.getAuthorId());
            statement.setInt(4, book.getGenreId());
            statement.setDate(5, java.sql.Date.valueOf(book.getReleaseDate()));

            statement.executeUpdate();
            System.out.println("Книга успішно додана: " + book.getTitle());
        } catch (SQLException e) {
            System.out.println("Помилка при додаванні книги: " + e.getMessage());
        }
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, PublisherID = ?, AuthorID = ?, GenreID = ?, release_date = ? WHERE BookID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPublisherId());
            statement.setInt(3, book.getAuthorId());
            statement.setInt(4, book.getGenreId());
            statement.setDate(5, book.getReleaseDate() != null ? java.sql.Date.valueOf(book.getReleaseDate()) : null);
            statement.setInt(6, book.getId());

            statement.executeUpdate();
            System.out.println("Книга успішно оновлена: ID = " + book.getId());
        } catch (SQLException e) {
            System.out.println("Помилка при оновленні книги: " + e.getMessage());
        }
    }


    @Override
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
            System.out.println("Книгу видалено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при видаленні книги: " + e.getMessage());
        }
    }

    @Override
    public List<Book> searchBooks(String title) {
        String sql = "SELECT * FROM Books WHERE title LIKE ?";
        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("BookID"),
                        resultSet.getString("title"),
                        resultSet.getInt("PublisherID"),
                        resultSet.getInt("AuthorID"),
                        resultSet.getInt("GenreID"),
                        resultSet.getDate("release_date").toLocalDate()
                );
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Помилка при пошуку книг: " + e.getMessage());
        }

        return books;
    }



    @Override
    public List<Book> displayAllBooks() {
        String sql = "SELECT * FROM Books";
        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("BookID"),
                        resultSet.getString("title"),
                        resultSet.getInt("PublisherID"),
                        resultSet.getInt("AuthorID"),
                        resultSet.getInt("GenreID"),
                        resultSet.getDate("release_date").toLocalDate()
                );
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Помилка при перегляді книг: " + e.getMessage());
        }

        return books;
    }


    // ===== Publishers =====
    @Override
    public void addPublisher(Publisher publisher) {
        String sql = "INSERT INTO Publishers (name, location) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getLocation());
            statement.executeUpdate();
            System.out.println("Видавець доданий успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при додаванні видавця: " + e.getMessage());
        }
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        String sql = "UPDATE Publishers SET name = ?, location = ? WHERE PublisherID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getLocation());
            statement.setInt(3, publisher.getId());
            statement.executeUpdate();
            System.out.println("Видавець оновлений успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при оновленні видавця: " + e.getMessage());
        }
    }

    @Override
    public void deletePublisher(int publisherId) {
        String sql = "DELETE FROM Publishers WHERE PublisherID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, publisherId);
            statement.executeUpdate();
            System.out.println("Видавця видалено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при видаленні видавця: " + e.getMessage());
        }
    }

    @Override
    public List<Publisher> searchPublishers(String keyword) {
        String sql = "SELECT * FROM Publishers WHERE name LIKE ?";
        List<Publisher> publishers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                publishers.add(new Publisher(
                        resultSet.getInt("PublisherID"),
                        resultSet.getString("name"),
                        resultSet.getString("location")
                ));
            }

            if (publishers.isEmpty()) {
                System.out.println("Не знайдено видавців, що відповідають критерію.");
            }

        } catch (SQLException e) {
            System.out.println("Помилка при пошуку видавців: " + e.getMessage());
        }

        return publishers;
    }



    @Override
    public List<Publisher> displayAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        String sql = "SELECT * FROM Publishers";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                publishers.add(new Publisher(
                        resultSet.getInt("PublisherID"),
                        resultSet.getString("name"),
                        resultSet.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Помилка при перегляді видавців: " + e.getMessage());
        }
        return publishers;
    }

    @Override
    public void addAuthor(String name, int birthYear) {
        String sql = "INSERT INTO Authors (name, birth_year) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, birthYear);
            statement.executeUpdate();
            System.out.println("Автора додано успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при додаванні автора: " + e.getMessage());
        }
    }


    @Override
    public void updateAuthor(int authorId, String newName, int newBirthYear) {
        String sql = "UPDATE Authors SET name = ?, birth_year = ? WHERE AuthorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, newBirthYear);
            statement.setInt(3, authorId);
            statement.executeUpdate();
            System.out.println("Автора оновлено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при оновленні автора: " + e.getMessage());
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        String sql = "DELETE FROM Authors WHERE AuthorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorId);
            statement.executeUpdate();
            System.out.println("Автора видалено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при видаленні автора: " + e.getMessage());
        }
    }

    @Override
    public List<Author> searchAuthors(String name) {
        String sql = "SELECT * FROM Authors WHERE name LIKE ?";
        List<Author> authors = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int authorId = resultSet.getInt("AuthorID");
                String authorName = resultSet.getString("name");
                int birthYear = resultSet.getInt("birth_year");
                authors.add(new Author(authorId, authorName, birthYear)); // Створення об'єкта Author
            }

            if (authors.isEmpty()) {
                System.out.println("Не знайдено авторів, що відповідають критерію.");
            }

        } catch (SQLException e) {
            System.out.println("Помилка при пошуку авторів: " + e.getMessage());
        }

        return authors;
    }

    @Override
    public List<Author> displayAllAuthors() {
        String sql = "SELECT * FROM Authors";
        List<Author> authors = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int authorId = resultSet.getInt("AuthorID");
                String name = resultSet.getString("name");
                int birthYear = resultSet.getInt("birth_year");
                authors.add(new Author(authorId, name, birthYear)); // Додавання об'єкта Author
            }

        } catch (SQLException e) {
            System.out.println("Помилка при перегляді авторів: " + e.getMessage());
        }

        return authors;
    }


    @Override
    public void addMagazine(Magazine magazine) {
        String sql = "INSERT INTO Magazines (title, PublisherID, release_date) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, magazine.getTitle());
            statement.setInt(2, magazine.getPublisherId());
            statement.setDate(3, Date.valueOf(magazine.getReleaseDate())); // Перетворення LocalDate в SQL Date
            statement.executeUpdate();
            System.out.println("Журнал додано успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при додаванні журналу: " + e.getMessage());
        }
    }


    @Override
    public void updateMagazine(Magazine magazine) {
        String sql = "UPDATE Magazines SET title = ?, PublisherID = ?, release_date = ? WHERE MagazineID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, magazine.getTitle());
            statement.setInt(2, magazine.getPublisherId());
            statement.setDate(3, Date.valueOf(magazine.getReleaseDate())); // Перетворення LocalDate в SQL Date
            statement.setInt(4, magazine.getId());
            statement.executeUpdate();
            System.out.println("Журнал оновлено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при оновленні журналу: " + e.getMessage());
        }
    }


    @Override
    public void deleteMagazine(int magazineId) {
        String sql = "DELETE FROM Magazines WHERE MagazineID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, magazineId);
            statement.executeUpdate();
            System.out.println("Журнал видалено успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при видаленні журналу: " + e.getMessage());
        }
    }

    @Override
    public List<Magazine> searchMagazines(String keyword) {
        String sql = "SELECT * FROM Magazines WHERE title LIKE ?";
        List<Magazine> magazines = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazines.add(new Magazine(
                        resultSet.getInt("MagazineID"),
                        resultSet.getString("title"),
                        resultSet.getInt("PublisherID"),
                        resultSet.getDate("release_date").toLocalDate()
                ));
            }

            if (magazines.isEmpty()) {
                System.out.println("Не знайдено журналів, що відповідають критерію.");
            }

        } catch (SQLException e) {
            System.out.println("Помилка при пошуку журналів: " + e.getMessage());
        }

        return magazines;
    }



    @Override
    public List<Magazine> displayAllMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        String sql = "SELECT * FROM Magazines"; // Переконайтесь, що таблиця дійсно має правильні стовпці

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                magazines.add(new Magazine(
                        resultSet.getInt("MagazineID"),
                        resultSet.getString("title"),
                        resultSet.getInt("PublisherID"),
                        resultSet.getDate("release_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }


}
