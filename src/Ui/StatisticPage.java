package Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatisticPage extends JFrame {
    private JTextField st_searchField;
    private JButton st_searchButton;
    private JTable st_table;
    private JButton st_itemButton;
    private JButton st_chargeButton;
    private DefaultTableModel st_tableModel;

    public StatisticPage() {
        initFrame();
    }
    private void initFrame(){
        setTitle("Statistic Page");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        st_searchField = new JTextField(20);
        topPanel.add(st_searchField);

        st_searchButton = new JButton("Search");
        topPanel.add(st_searchButton);

        add(topPanel, BorderLayout.NORTH);

        st_tableModel = new DefaultTableModel();
        st_table = new JTable(st_tableModel);
        JScrollPane scrollPane = new JScrollPane(st_table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        st_itemButton = new JButton("Item");
        bottomPanel.add(st_itemButton);
        st_chargeButton = new JButton("Charge");
        bottomPanel.add(st_chargeButton);

        add(bottomPanel, BorderLayout.SOUTH);



    }

}
