package sit.snak.shop;

import java.sql.*;

public class SQLInsertTable {

    public static int A = 100;
    public static int autoID = (++A) + (int)((Math.random() * 100 ));
            
    public SQLInsertTable(String tname, String n, String t, float m) {
        Connection theConn = null;
        String SQL;
        try {
            SQLConnection MyCon = new SQLConnection();
            theConn = MyCon.getConnection("JAVA");
            Statement stmt = theConn.createStatement();
            if (tname.equals("product")) {
                SQL = "insert into product (pid,pname) values " + "('" + n + "','" + t + "')";
                stmt.executeUpdate(SQL);
                // ในที่นี้กำหนดให้ จำนวนสินค้า pin=1000 ราคาขายต่อหน่วย pprice=25% ของราคาต้นทุนต่อหน่วย   
                SQL = "insert into pdetail (pid,pin,pcost,pprice) values "
                        + "('" + n + "'," + 1000 + "," + m / 1.25f + "," + m + ")";
                stmt.executeUpdate(SQL);
            }
            if (tname.equals("psale")) {
                System.out.println("I am sale");
                SQL = "insert into psale (pno, pid,pqty,ptotal) values "
                        + "('" + (++autoID) + "','" + n + "','" + new Float(t) + "','" + m + "')";
                System.out.println(SQL);
                stmt.executeUpdate(SQL);
            }
            SQLSelectAllTable all = new SQLSelectAllTable(tname);
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (theConn != null) {
                    theConn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
