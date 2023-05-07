package com.mission1.mission1.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    public void dbCreate() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        String SQL = "CREATE TABLE IF NOT EXISTS history ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lat REAL, " +
                "lnt REAL, " +
                "dt TEXT)";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

//            statement.executeUpdate("DROP TABLE IF EXISTS history");
            statement.executeUpdate(SQL);
            System.out.println("HISTORY DB 생성 (IF NOT EXISTS) 완료");
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

    public List<History> dbSelect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM history";
        List<History> histories = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setLat(rs.getDouble("lat"));
                history.setLnt(rs.getDouble("lnt"));
                history.setDt(rs.getString("dt"));
                histories.add(history);
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
        return histories;
    }

    public void dbInsert(History history) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO history " +
                "(lat, lnt, dt) " +
                "values (?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setDouble(1, history.getLat());
            preparedStatement.setDouble(2, history.getLnt());
            preparedStatement.setString(3, history.getDt());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("history 저장 성공");
            } else {
                System.out.println("history 0행 저장");
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

    public void dbUpdate(String SQL) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            int affected = statement.executeUpdate(SQL);

            if (affected > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("0행 수정");
            }
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

    public void dbDelete(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM history WHERE id=?";
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
        String SQL = "DROP TABLE history";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate(SQL);
            System.out.println("HISTORY DB 삭제 완료");
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
