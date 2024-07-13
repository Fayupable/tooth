package Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ItemPage extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private JButton itemButton;
    private JButton chargeButton;
    private DefaultTableModel tableModel;

    public ItemPage() {
        initFrame();
    }
    private void initFrame(){
        setTitle("Item Page");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        topPanel.add(searchField);

        searchButton = new JButton("Search");
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        itemButton = new JButton("Item");
        bottomPanel.add(itemButton);
        chargeButton = new JButton("Charge");
        bottomPanel.add(chargeButton);

        add(bottomPanel, BorderLayout.SOUTH);



    }

}
