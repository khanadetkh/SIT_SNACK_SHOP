
package sit.snak.shop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProduct extends JFrame {

    JPanel panel;
    JLabel idlbl, namelbl, pricelbl, datelbl, piclbl,
            cnamelbl, cadslbl, blanklbl;
    JTextField idtxt, nametxt, pricetxt;
    JButton rptbtn, savebtn, resetbtn;
    Icon ani;
    product prod = new product();
    product pr[] = new product[50];
    AddPanel p;
    Font fn = new Font("TH Sarabun New", Font.PLAIN, 24);

    public AddProduct(String title) {
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
        pricelbl = new JLabel("Price / Unit");
        idtxt = new JTextField(15);
        nametxt = new JTextField(15);
        pricetxt = new JTextField(15);
        ani = new ImageIcon("newspaper.png");
        rptbtn = new JButton("Report", ani);
        ani = new ImageIcon("save.png");
        savebtn = new JButton("Save", ani);
        ani = new ImageIcon("reset.png");
        resetbtn = new JButton("Reset", ani);
        rptbtn.addActionListener(new ButtonListener());
        savebtn.addActionListener(new ButtonListener());

        piclbl.setFont(fn);
        cnamelbl.setFont(fn);
        cadslbl.setFont(fn);
        datelbl.setFont(fn);
        idlbl.setFont(fn);
        idtxt.setFont(fn);
        namelbl.setFont(fn);
        nametxt.setFont(fn);
        pricelbl.setFont(fn);
        pricetxt.setFont(fn);
        rptbtn.setFont(fn);
        savebtn.setFont(fn);
        resetbtn.setFont(fn);

        p = new AddPanel();
        p.addItem(panel, piclbl, 0, 0, 1, 3, GridBagConstraints.WEST);
        p.addItem(panel, cnamelbl, 1, 0, 2, 1, GridBagConstraints.WEST);
        p.addItem(panel, cadslbl, 1, 1, 2, 1, GridBagConstraints.WEST);
        p.addItem(panel, datelbl, 1, 2, 3, 1, GridBagConstraints.EAST);
        p.addItem(panel, idlbl, 0, 3, 1, 1, GridBagConstraints.WEST);
        p.addItem(panel, idtxt, 1, 3, 2, 1, GridBagConstraints.EAST);
        p.addItem(panel, namelbl, 0, 4, 1, 1, GridBagConstraints.WEST);
        p.addItem(panel, nametxt, 1, 4, 2, 1, GridBagConstraints.EAST);
        p.addItem(panel, pricelbl, 0, 5, 1, 1, GridBagConstraints.WEST);
        p.addItem(panel, pricetxt, 1, 5, 2, 1, GridBagConstraints.EAST);
        p.addItem(panel, rptbtn, 0, 6, 1, 1, GridBagConstraints.WEST);
        p.addItem(panel, savebtn, 1, 6, 1, 1, GridBagConstraints.WEST);
        p.addItem(panel, resetbtn, 2, 6, 1, 1, GridBagConstraints.WEST);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == rptbtn) {
                SQLSelectAllTable t = new SQLSelectAllTable("product");
            }
            if (e.getSource() == savebtn) {
                prod.id = idtxt.getText();
                prod.name = nametxt.getText();
                prod.price = new Float(pricetxt.getText());
                SQLInsertTable i = new SQLInsertTable("product", prod.id, prod.name, prod.price);
                idtxt.setText("");
                nametxt.setText("");
                pricetxt.setText("");
                idtxt.requestFocus();
            }
            if (e.getSource() == resetbtn) {
                idtxt.setText("");
                nametxt.setText("");
                pricetxt.setText("");
            }
        }
    }

}
