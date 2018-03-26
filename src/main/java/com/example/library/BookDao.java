package com.example.library;

import java.sql.*;

public class BookDao {

    private static final String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASS = "admin";
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver: not found");
        } catch (SQLException e) {
            System.out.println("Connection: not established");
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection: not closed");
        }
    }

    public void save(Book book) {
        final String sql = "INSERT INTO books(title, author, year, isbn) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setLong(4, book.getIsbn());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Save: not executed");
        }
    }

    public Book read(long id) {

        final String sql = "SELECT * FROM books WHERE id = ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                Book book = new Book();
                book.setId(result.getLong("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getLong("isbn"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Read: not executed");
        }
        return null;
    }

    public void update(Book book) {
        final String sql = "UPDATE books SET title = ? , author = ? , year = ? , isbn = ? WHERE id = ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setLong(4, book.getIsbn());
            ps.setLong(5, book.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update: not executed");
        }
    }

    public void delete(long id) {
        final String sql = "DELETE FROM books WHERE id = ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete: not executed");
        }
    }


}
