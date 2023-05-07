package com.mission1.mission1.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {
    public void dbCreate() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        String SQL = "CREATE TABLE IF NOT EXISTS bookmarkGroup ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "bookmarkName TEXT UNIQUE NOT NULL, " +
                "orderId INTEGER, " +
                "registrationDate TEXT, " +
                "editDate TEXT)";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

//            statement.executeUpdate("DROP TABLE IF EXISTS bookmarkGroup");
            statement.executeUpdate(SQL);
            System.out.println("bookmarkGroup DB 생성 (IF NOT EXISTS) 완료");
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

    public List<BookmarkGroup> dbSelect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM bookmarkGroup";
        List<BookmarkGroup> bookmarkGroups = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getInt("id"));
                bookmarkGroup.setBookmarkName(rs.getString("bookmarkName"));
                bookmarkGroup.setOrderId(rs.getInt("orderId"));
                bookmarkGroup.setRegistrationDate(rs.getString("registrationDate"));
                bookmarkGroup.setEditDate(rs.getString("editDate"));
                bookmarkGroups.add(bookmarkGroup);
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
        return bookmarkGroups;
    }

    public BookmarkGroup dbSelectById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String SQL = String.format("SELECT * FROM bookmarkGroup WHERE id=%d", id);
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            if (rs.next()) {
                bookmarkGroup.setId(rs.getInt("id"));
                bookmarkGroup.setBookmarkName(rs.getString("bookmarkName"));
                bookmarkGroup.setOrderId(rs.getInt("orderId"));
                bookmarkGroup.setRegistrationDate(rs.getString("registrationDate"));
                bookmarkGroup.setEditDate(rs.getString("editDate"));
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
        return bookmarkGroup;
    }

    public int dbInsert(BookmarkGroup bookmarkGroup) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO bookmarkGroup " +
                "(bookmarkName, orderId, registrationDate, editDate) " +
                "values (?, ?, ?, ?)";
        int affected = 0;
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, bookmarkGroup.getBookmarkName());
            preparedStatement.setInt(2, bookmarkGroup.getOrderId());
            preparedStatement.setString(3, bookmarkGroup.getRegistrationDate());
            preparedStatement.setString(4, bookmarkGroup.getEditDate());

            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmarkGroup 저장 성공");
            } else {
                System.out.println("bookmarkGroup 0행 저장");
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
        return affected;
    }

    public int dbUpdate(int id, String bookmarkName, int orderId, String editDate) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE bookmarkGroup " +
                "SET bookmarkName=?, orderId=?, editDate=? " +
                "WHERE id=?";
        int affected = 0;
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setString(3, editDate);
            preparedStatement.setInt(4, id);

            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("bookmarkGroup 수정 성공");
            } else {
                System.out.println("bookmarkGroup 0행 수정");
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
        return affected;
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
        String SQL = "DELETE FROM bookmarkGroup WHERE id=?";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setInt(1, id);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 0행 삭제 ");
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
        String SQL = "DROP TABLE bookmarkGroup";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate(SQL);
            System.out.println("bookmarkGroup DB 삭제 완료");
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
