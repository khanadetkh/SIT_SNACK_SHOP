package sit.snak.shop;


import java.sql.*;
import javax.swing.*;

public class SQLConnection {

    Connection c;
    SQLConnection MyCon;
    Statement stmt;
    String SQL;
    DatabaseMetaData md;
    ResultSet rs;
    boolean found = false;
    String msg = "";

    public void CreateDB(String fn) {
        try {
            MyCon = new SQLConnection();
            c = MyCon.getConnection("");
            stmt = c.createStatement();
            SQL = "show databases";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString(1).toUpperCase().equals(fn)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                SQL = "create database " + fn;
                stmt.executeUpdate(SQL);
                SQL = "show databases";
                rs = stmt.executeQuery(SQL);
                msg = "DataBase >>> ";
                while (rs.next()) {
                    if (rs.getString(1).toUpperCase().equals(fn)) {
                        msg = msg + rs.getString(1).toUpperCase();
                        break;
                    }
                }
                msg = msg + " is Created";
                JOptionPane.showMessageDialog(null, msg);
            } else {
                msg = "DataBase >>> " + fn + " is already Created";
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection(String fn) {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            String url = "jdbc:db2://10.4.53.14:50000/SAMPLE";
            c = DriverManager.getConnection(url, "st105027", "int105");
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
}
