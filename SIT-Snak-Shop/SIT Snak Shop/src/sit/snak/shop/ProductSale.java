package sit.snak.shop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductSale extends JFrame {

    JPanel panel;
    JLabel idlbl, namelbl, pricelbl, qlbl, anslbl;
    JLabel datelbl, piclbl, cnamelbl, cadslbl, blanklbl;
    JTextField idtxt, nametxt, pricetxt, qtxt;
    JPasswordField pwtxt;
    Icon ani;
    JButton rptbtn, savebtn, resetbtn;
    product prod = new product();
    product pr[] = new product[50];
    Font fn = new Font("TH Sarabun New", Font.PLAIN, 18);
    Font tfn = new Font("TH Sarabun New", Font.BOLD, 24);
    AddPanel y;
    int count = 0;
    String pid, pname;
    float pprice, pqty, total;

    public ProductSale(String title) {
        setTitle(title);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildPanel();
        add(panel);
        setVisible(true);
    }

    private void buildPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        ani = new ImageIcon("popcorn.png");
        piclbl = new JLabel(ani, SwingConstants.LEFT);
        cnamelbl = new JLabel("SIT SANCK SHOP");
        cadslbl = new JLabel("SIT,KMUTT TEL : 0 2470 9850");
        Date DateName = new Date();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
        String dd = df.format(DateName.getTime());
        datelbl = new JLabel("Date : " + dd + "   ");

        idlbl = new JLabel("Product ID");
        namelbl = new JLabel("Product Name");
        pricelbl = new JLabel("Price/Unit");
        qlbl = new JLabel("Q'ty");
        idtxt = new JTextField(15);
        nametxt = new JTextField(15);
        nametxt.setEnabled(false);
        pricetxt = new JTextField(15);
        pricetxt.setEnabled(false);
        qtxt = new JTextField(15);

        piclbl.setFont(fn);
        cnamelbl.setFont(fn);
        cadslbl.setFont(fn);
        datelbl.setFont(fn);
        idlbl.setFont(fn);
        namelbl.setFont(fn);
        pricelbl.setFont(fn);
        qlbl.setFont(fn);
        idtxt.setFont(fn);
        nametxt.setFont(fn);
        pricetxt.setFont(fn);
        qtxt.setFont(fn);
        ani = new ImageIcon("newspaper.png");
        rptbtn = new JButton("Report", ani);
        ani = new ImageIcon("save.png");
        savebtn = new JButton("Save", ani);
        ani = new ImageIcon("reset.png");
        resetbtn = new JButton("Reset", ani);
        anslbl = new JLabel("", SwingConstants.CENTER);
        anslbl.setOpaque(true);
        anslbl.setBackground(Color.blue);
        anslbl.setForeground(Color.white);
        anslbl.setPreferredSize(new Dimension(350, 30));
        rptbtn.setFont(fn);
        savebtn.setFont(fn);
        resetbtn.setFont(fn);
        anslbl.setFont(tfn);
        idtxt.addActionListener(new TextListener());
        qtxt.addActionListener(new TextListener());
        rptbtn.addActionListener(new ButtonListener());
        savebtn.addActionListener(new ButtonListener());
        resetbtn.addActionListener(new ButtonListener());

        y = new AddPanel();
        y.addItem(panel, piclbl, 0, 0, 1, 3, GridBagConstraints.WEST);
        y.addItem(panel, cnamelbl, 1, 0, 2, 1, GridBagConstraints.WEST);
        y.addItem(panel, cadslbl, 1, 1, 2, 1, GridBagConstraints.WEST);

        y.addItem(panel, datelbl, 1, 2, 3, 1, GridBagConstraints.EAST);

        y.addItem(panel, idlbl, 0, 3, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, idtxt, 1, 3, 2, 1, GridBagConstraints.EAST);

        y.addItem(panel, namelbl, 0, 4, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, nametxt, 1, 4, 2, 1, GridBagConstraints.EAST);

        y.addItem(panel, pricelbl, 0, 5, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, pricetxt, 1, 5, 2, 1, GridBagConstraints.EAST);

        y.addItem(panel, qlbl, 0, 6, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, qtxt, 1, 6, 2, 1, GridBagConstraints.EAST);

        y.addItem(panel, rptbtn, 0, 7, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, savebtn, 1, 7, 1, 1, GridBagConstraints.WEST);
        y.addItem(panel, resetbtn, 2, 7, 1, 1, GridBagConstraints.WEST);

        y.addItem(panel, anslbl, 0, 8, 4, 1, GridBagConstraints.CENTER);
    }

    private class TextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id;
            if (e.getSource() == idtxt) {
                id = idtxt.getText();
                prod.id = id;
                SQLSelectTable s = new SQLSelectTable(prod);
                if ((prod.name) == null) {
                    JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลของรหัสสินค้า " + id + " !!!");
                    idtxt.setText("");
                    nametxt.setText("");
                    pricetxt.setText("");
                    qtxt.setText("");
                    anslbl.setText("");
                    idtxt.requestFocus();
                }
                idtxt.setText(prod.id);
                nametxt.setText(prod.name);
                pricetxt.setText(Float.toString(prod.price));
                qtxt.requestFocus();
            }
            if (e.getSource() == qtxt) {
                pqty = new Float(qtxt.getText());
                total = pqty * prod.price;
                DecimalFormat fm = new DecimalFormat("#,##0.00");
                String ans = "รวมเป็นเงิน " + fm.format(total) + " บาท ";
                anslbl.setText(ans);
                savebtn.requestFocus();
            }
        }

    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == rptbtn) {
                SQLSelectAllTable t = new SQLSelectAllTable("psale");
            }
            if (e.getSource() == savebtn) {
                System.out.println("I am Save");
//                SQLCreateTable t = new SQLCreateTable("psale");
                SQLInsertTable i = new SQLInsertTable("psale", prod.id,
                        Float.toString(pqty), total);
                idtxt.setText("");
                nametxt.setText("");
                pricetxt.setText("");
                qtxt.setText("");
                anslbl.setText("");
                idtxt.requestFocus();
            }
            if (e.getSource() == resetbtn) {
                idtxt.setText("");
                nametxt.setText("");
                pricetxt.setText("");
                qtxt.setText("");
                anslbl.setText("");
                idtxt.requestFocus();
            }
        }
    }
}
