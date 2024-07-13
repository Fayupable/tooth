package Ui;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    public MainPage() {
        initFrame();
    }
    private void initFrame(){
        setTitle("Main Page");
        setSize(200,200);
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


    }






}
