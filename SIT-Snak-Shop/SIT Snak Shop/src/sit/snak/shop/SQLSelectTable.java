package sit.snak.shop;

import java.sql.*;

public class SQLSelectTable {

    public SQLSelectTable(product prod) {
        Connection theConn = null;
        String SQL;
        String n = prod.id;
        try {
            SQLConnection MyCon = new SQLConnection();
            theConn = MyCon.getConnection("JAVA");
            Statement stmt = theConn.createStatement();
            SQL = "select pdetail.*, product.pname from pdetail, product "
                    + "where pdetail.pid= product.pid and product.pid='" + n
                    + "' order by pdetail.pid";
            System.out.println(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String x = rs.getString("pid") + ";" + rs.getString("pname") + ";"
                        + rs.getString("pprice");
                String[] y = x.split(";", 3);
                prod.id = y[0];
                prod.name = y[1];
                prod.price = new Float(y[2]);
            }
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
