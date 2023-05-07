package com.mission1.mission1.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    public void dbCreate() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        String SQL = "CREATE TABLE IF NOT EXISTS bookmark ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "bookmarkName TEXT, " +
                "wifiName TEXT, " +
                "registrationDate TEXT)";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

//            statement.executeUpdate("DROP TABLE IF EXISTS bookmark");
            statement.executeUpdate(SQL);
            System.out.println("bookmark DB 생성 (IF NOT EXISTS) 완료");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public List<Bookmark> bookmarkList() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM bookmark";
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getInt("id"));
                bookmark.setBookmarkName(rs.getString("bookmarkName"));
                bookmark.setWifiName(rs.getString("wifiName"));
                bookmark.setRegistrationDate(rs.getString("registrationDate"));
                bookmarks.add(bookmark);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return bookmarks;
    }

    public Bookmark bookmarkById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String SQL = String.format("SELECT * FROM bookmark WHERE id=%d", id);
        Bookmark bookmark = new Bookmark();
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                bookmark.setId(rs.getInt("id"));
                bookmark.setBookmarkName(rs.getString("bookmarkName"));
                bookmark.setWifiName(rs.getString("wifiName"));
                bookmark.setRegistrationDate(rs.getString("registrationDate"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return bookmark;
    }

    public void dbInsert(Bookmark bookmark) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO bookmark " +
                "(bookmarkName, wifiName, registrationDate) " +
                "values (?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, bookmark.getBookmarkName());
            preparedStatement.setString(2, bookmark.getWifiName());
            preparedStatement.setString(3, bookmark.getRegistrationDate());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmark 저장 성공");
            } else {
                System.out.println("bookmark 0행 저장");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void updateByBookmarkName(String oldName, String newName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE bookmark " +
                "SET bookmarkName=? " +
                "WHERE bookmarkName=?";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmark 수정 성공");
            } else {
                System.out.println("bookmark 0행 수정");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void dbDelete(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM bookmark WHERE id=?";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setInt(1, id);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmark 삭제 성공 ");
            } else {
                System.out.println("bookmark 0행 삭제 ");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void deleteByBookmarkName(String bookmarkName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM bookmark WHERE bookmarkName=?";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, bookmarkName);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmark 삭제 성공 ");
            } else {
                System.out.println("bookmark 0행 삭제 ");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void dbDrop() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        String SQL = "DROP TABLE bookmark";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate(SQL);
            System.out.println("bookmark DB 삭제 완료");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
