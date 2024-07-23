package Ui;

import Db.DbFunctions;
import Db.Exception.DbConnectionException;
import User.User;

import javax.swing.*;
import java.sql.SQLException;

public class LoginPage extends javax.swing.JFrame {
    DbFunctions dbFunctions;
    User loggedInUser;

    public LoginPage() {
        initComponents();
        this.dbFunctions = new DbFunctions();
        this.loggedInUser = new User();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        txtf_user_name = new javax.swing.JTextField();
        btn_login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtf_user_name.setText("User Name");

        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btn_loginActionPerformed(evt);
                } catch (SQLException | DbConnectionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(txtf_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addComponent(btn_login)))
                                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(txtf_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btn_login)
                                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, DbConnectionException {
        if (txtf_user_name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a user name");
        } else {
            DbFunctions userLogin = new DbFunctions();
            boolean isAuthenticated = userLogin.Login(txtf_user_name.getText());
            if (isAuthenticated) {
                JOptionPane.showMessageDialog(this, "Login successful");
                this.loggedInUser = userLogin.getUser(txtf_user_name.getText());
//                new MainPage(loggedInUser).setVisible(true);
                new MainPage().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }



        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btn_login;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtf_user_name;
}