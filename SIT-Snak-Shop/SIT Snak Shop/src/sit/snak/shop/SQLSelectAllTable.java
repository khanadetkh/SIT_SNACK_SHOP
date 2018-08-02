package sit.snak.shop;

import java.sql.*;

public class SQLSelectAllTable {

    public SQLSelectAllTable(String tname) {
        Connection theConn = null;
        String SQL;
        try {
            SQLConnection MyCon = new SQLConnection();
            theConn = MyCon.getConnection("JAVA");
            Statement stmt = theConn.createStatement();
            if (tname.equals("product")) {
                SQL = "select pdetail.*,product.pname from pdetail,product "
                        + "where pdetail.pid=product.pid order by pdetail.pid";
                ResultSet rs = stmt.executeQuery(SQL);
                System.out.println("=====================================");
                System.out.printf("             Product List             \n");
                System.out.println("=====================================");
                while (rs.next()) {
                    System.out.printf("%-10s%-10s%12.2f\n", rs.getString("pid"),
                            rs.getString("pname"), new Float(rs.getString("pprice")));
                }
            }
            if (tname.equals("psale")) {
                SQL = "SELECT psale.*, product.pname  FROM psale INNER JOIN product ON psale.pid = product.pid ORDER by psale.pid";
                ResultSet rs = stmt.executeQuery(SQL);
                System.out.println("=====================================");
                System.out.printf("          Product Sale List           \n");
                System.out.println("=====================================");
                while (rs.next()) {
                    System.out.printf("%-8s%-8s%8.0f%,12.2f\n", rs.getString("pid"),
                            rs.getString("pname"), new Float(rs.getString("pqty")),
                            new Float(rs.getString("ptotal")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (theConn != null) {
                    theConn.close();
                }
                System.out.println("=====================================");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
