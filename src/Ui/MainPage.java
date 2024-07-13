package Ui;

import Db.DbFunctions;
import Db.Exception.DbConnectionException;
import Item.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainPage extends JFrame {
    public MainPage() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Main Page");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTextField txt_user_name = new JTextField(15);
        panel.add(txt_user_name);
        JButton btn_statistic = new JButton("Statistic");
        panel.add(btn_statistic);
        JButton btn_item = new JButton("Item");
        panel.add(btn_item);

        add(panel);

//        btn_statistic.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String user_name = txt_user_name.getText();
//                if (user_name.isEmpty()) {
//                    JOptionPane.showMessageDialog(txt_user_name, "Please enter user name");
//                    return;
//                }
//                try {
//                    List<Common> useResult = new DbFunctions().searchFunction(user_name);
//                    List<Common> chargeResult = new DbFunctions().searchFunctionCharge(user_name);
//
//                    if (useResult.isEmpty() && chargeResult.isEmpty()) {
//                        JOptionPane.showMessageDialog(txt_user_name, "No data found");
//                        return;
//                    } else {
//                        StringBuilder sb = new StringBuilder();
//                        for (Common common : useResult) {
//                            sb.append(common.toString());
//                        }
//                        for (Common common : chargeResult) {
//                            sb.append(common.toString());
//                        }
//                        JOptionPane.showMessageDialog(txt_user_name, sb.toString());
//
//                    }
//                } catch (DbConnectionException ex) {
//                    throw new RuntimeException(ex);
//                }
//                statisticPage();
//
//
//            }
//
//        });

        btn_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemPage itemPage = new ItemPage();
                itemPage.setVisible(true);
                dispose();
            }
        });


    }

    private void itemPage() {
        ItemPage itemPage = new ItemPage();
        itemPage.setVisible(true);
        dispose();
    }

    private void statisticPage() {
        StatisticPage statisticPage = new StatisticPage();
        statisticPage.setVisible(true);
        dispose();
    }


}
