package com.mission1.mission1.db;

import com.mission1.mission1.Haversine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class WifiService {

    public void dbCreate() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        String SQL = "CREATE TABLE WIFI (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "X_SWIFI_MGR_NO TEXT, " +
                "X_SWIFI_WRDOFC TEXT, " +
                "X_SWIFI_MAIN_NM TEXT, " +
                "X_SWIFI_ADRES1 TEXT, " +
                "X_SWIFI_ADRES2 TEXT, " +
                "X_SWIFI_INSTL_FLOOR TEXT, " +
                "X_SWIFI_INSTL_TY TEXT, " +
                "X_SWIFI_INSTL_MBY TEXT, " +
                "X_SWIFI_SVC_SE TEXT, " +
                "X_SWIFI_CMCWR TEXT, " +
                "X_SWIFI_CNSTC_YEAR INTEGER, " +
                "X_SWIFI_INOUT_DOOR TEXT, " +
                "X_SWIFI_REMARS3 TEXT, " +
                "LAT REAL, " +
                "LNT REAL, " +
                "WORK_DTTM TEXT)";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS WIFI ");
            statement.executeUpdate(SQL);
            System.out.println("WIFI DB 생성 완료");
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

    public void dbInsert(Wifi wifi) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO WIFI\n" +
                "    (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,\n" +
                "     X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR,\n" +
                "     X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
            preparedStatement.setInt(11, wifi.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
            preparedStatement.setDouble(14, wifi.getLAT());
            preparedStatement.setDouble(15, wifi.getLNT());
            preparedStatement.setString(16, wifi.getWORK_DTTM());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("0행 저장");
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

    public List<Wifi> get20Wifi(double latitude, double longitude) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Wifi> wifis = new ArrayList<>();

        String SQL = "SELECT * FROM wifi";
        PriorityQueue<Wifi> pq = new PriorityQueue<>(
                (x, y) ->
                Haversine.distance(latitude, longitude, y.getLAT(), y.getLNT()) - Haversine.distance(latitude, longitude, x.getLAT(), x.getLNT()) > 0 ? 1 : -1);
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(rs.getInt("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(rs.getFloat("LAT"));
                wifi.setLNT(rs.getFloat("LNT"));
                wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));

                pq.add(wifi);
                while (pq.size() > 20) {
                    pq.remove();
                }
            }
            while (!pq.isEmpty()) {
                wifis.add(pq.remove());
            }
            Collections.reverse(wifis);
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
        return wifis;
    }

    public Wifi detail(String mgrNo) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:wifi";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Wifi wifi = new Wifi();

        String SQL = "SELECT * FROM wifi WHERE X_SWIFI_MGR_NO=?";
        try {
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setQueryTimeout(30);

            preparedStatement.setString(1, mgrNo);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(rs.getInt("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(rs.getFloat("LAT"));
                wifi.setLNT(rs.getFloat("LNT"));
                wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
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
        return wifi;
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
        String SQL = "DROP TABLE WIFI";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate(SQL);
            System.out.println("WIFI DB 삭제 완료");
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