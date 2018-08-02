package sit.snak.shop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    JPanel panel;
    Icon ani;
    JButton addbtn, salebtn, closebtn;
    AddPanel p;
    Font fn = new Font("TH Sarabun New", Font.PLAIN, 24);

    public MainWindow(String title) {
        SQLConnection MyCon = new SQLConnection();
        MyCon.CreateDB("JAVA");
        setTitle(title);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(panel);
        setVisible(true);
    }

    private void buildPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        ani = new ImageIcon("add.png");
        addbtn = new JButton(" Add Product", ani);
        ani = new ImageIcon("sale.png");
        salebtn = new JButton(" Product Sale", ani);
        ani = new ImageIcon("cancel.png");
        closebtn = new JButton("Close", ani);

        addbtn.setFont(fn);
        salebtn.setFont(fn);
        closebtn.setFont(fn);

        p = new AddPanel();
        p.addItem(panel, addbtn, 0, 4, 3, 1, GridBagConstraints.EAST);
        p.addItem(panel, salebtn, 0, 5, 3, 1, GridBagConstraints.EAST);
        p.addItem(panel, closebtn, 0, 6, 3, 1, GridBagConstraints.CENTER);

        addbtn.addActionListener(new ButtonListener());
        salebtn.addActionListener(new ButtonListener());
        closebtn.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addbtn) {
                AddProduct s = new AddProduct("เพิ่มสินค้า");
            }
            if (e.getSource() == salebtn) {
                ProductSale s = new ProductSale("ขายสินค้า");
            }
            if (e.getSource() == closebtn) {
                System.exit(0);
            }
        }
    }
}
