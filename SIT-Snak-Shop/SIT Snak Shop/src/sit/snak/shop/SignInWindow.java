package sit.snak.shop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class product {

    String id;
    String name;
    float price;
    int rec;
}

public class SignInWindow extends JFrame {

    JPanel panel;
    JLabel welcomeLabel,ulbl, pwlbl;
    JTextField usertxt;
    JPasswordField pwtxt;
    JButton welcomebtn,signbtn, resetbtn;
    Icon ani;
    AddPanel p;
    Font fn = new Font("TH Sarabun New", Font.PLAIN, 24);

    public SignInWindow(String title) {
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

        ulbl = new JLabel("username");
        pwlbl = new JLabel("password");
        usertxt = new JTextField(10);
        pwtxt = new JPasswordField(10);


        ani = new ImageIcon("login.png");
        signbtn = new JButton("sign in", ani);
        ani = new ImageIcon("reset.png");
        resetbtn = new JButton("     reset    ", ani);


        ulbl.setFont(fn);
        usertxt.setFont(fn);
        pwlbl.setFont(fn);
        pwtxt.setFont(fn);
        signbtn.setFont(fn);
        resetbtn.setFont(fn);

        p = new AddPanel();
        p.addItem(panel, ulbl, 0, 0, 0, 1, GridBagConstraints.WEST);
        p.addItem(panel, usertxt, 3, 0, 3, 1, GridBagConstraints.WEST);
        p.addItem(panel, pwlbl, 0, 1, 3, 1, GridBagConstraints.WEST);
        p.addItem(panel, pwtxt, 3, 1, 3, 1, GridBagConstraints.WEST);
        p.addItem(panel, signbtn, 0, 3, 3, 1, GridBagConstraints.WEST);
        p.addItem(panel, resetbtn, 3, 3, 3, 1, GridBagConstraints.WEST);

        signbtn.addActionListener(new ButtonListener());
        resetbtn.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String u, p;
            if (e.getSource() == signbtn) {
                u = usertxt.getText();
                p = pwtxt.getText();
                if (u.equals("sit") && p.equals("105")) {
                    MainWindow s = new MainWindow("หน้าหลัก");
                } else {
                    JOptionPane.showMessageDialog(null, "กรุณาเข้าสู่ระบบอีกครั้ง \n Login Fail; Try again !!!");
                }
                usertxt.setText("");
                pwtxt.setText("");
            }
            if (e.getSource() == resetbtn) {
                usertxt.setText("");
                pwtxt.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SignInWindow s = new SignInWindow("เข้าสู่ระบบ");
    }
}
