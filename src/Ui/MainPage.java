package Ui;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Db.DbFunctions;
import Db.Exception.DbConnectionException;
import Item.Enum.ItemType;
import Item.Item;
import User.User;
import Item.Charge;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pc
 */
public class MainPage extends javax.swing.JFrame {
    public DbFunctions dbFunctions;
    public JTabbedPane tabbedPane;
    DefaultTableModel model;
    public User user;
    public Item item;
    public Charge charge;


    /**
     * Creates new form MainPage
     */
    public MainPage() {
        this.dbFunctions = new DbFunctions();
        this.tabbedPane = new JTabbedPane();
        this.model = new DefaultTableModel();
        this.user = new User();
        this.item = new Item();
        this.charge = new Charge();

        initComponents();
        initializeComboBox();
//        disableSomeFields();
        lbl_item_use_id.setText("Id");
        lbl_item_use_date.setText("Date");
        lbl_item_use_time.setText("Time");
        txtf_item_use_date.setText(LocalDate.now().toString());

//        try {
//            List<User> users = dbFunctions.getAllUsers();
//            for(User user: users){
//                System.out.println(user);
//            }
//        } catch (DbConnectionException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

    private void disableSomeFields() {
        txtf_id.setEnabled(false);
        txtf_item_id.setEnabled(false);
        txtf_item_use_id.setEnabled(false);
    }

    public void loadItem() throws SQLException, DbConnectionException {
        List<Item> items = dbFunctions.getAllItems();
        for (Item item : items) {
            model.addRow(new Object[]{item.getId(), item.getName(), item.getType()});
        }

    }

    public void loadUser() throws SQLException, DbConnectionException {
        List<User> users = dbFunctions.getAllUsers();
        for (User user : users) {
            model.addRow(new Object[]{user.getId(), user.getName(), user.getSurname()});
        }
    }

    private void addItem() {
        String item_name = txtf_item_name.getText();
        String item_type_string = (String) cmbx_item_type.getSelectedItem();
        int selectedIndex = tbp_tooth.getSelectedIndex();

        if (selectedIndex != -1) {
            String tabTitle = tbp_tooth.getTitleAt(selectedIndex);
            if (tabTitle.equals("Item")) {
                Item item = new Item();
                item.setName(item_name);
                item.setType(ItemType.valueOf(item_type_string));
                try {
                    dbFunctions.insertItem(item);
                    JOptionPane.showMessageDialog(null, "Item added successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding item: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the 'Item' tab to add an item.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a tab.");
        }
    }

    private void addCharge() {
        String item_use_id = txtf_item_use_id.getText();
        String item_use_date = txtf_item_use_date.getText();
        String item_use_time = txtf_item_use_time.getText();
        int selectedIndex = tbp_tooth.getSelectedIndex();

        if (selectedIndex != -1) {
            String tabTitle = tbp_tooth.getTitleAt(selectedIndex);
            if (tabTitle.equals("Charge")) {
                Charge charge = new Charge();
                charge.setUser_id(user.getId());
                charge.setItem_id(item.getId());
                charge.setCharge_date(LocalDate.parse(item_use_date));
                charge.setCharge_time(Integer.parseInt(item_use_time));
                try {
                    dbFunctions.insertCharge(charge);
                    JOptionPane.showMessageDialog(null, "Charge added successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding charge: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the 'Charge' tab to add a charge.");
            }
            }
    }
    private void clearFields() {
        txtf_id.setText("");
        txtf_name.setText("");
        txtf_item_id.setText("");
        txtf_item_name.setText("");
        cmbx_item_type.setSelectedIndex(0);
        txtf_item_use_id.setText("");
        txtf_item_use_date.setText("");
        txtf_item_use_time.setText("");
        txtf_item_battery.setText("");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        pnl_tab = new javax.swing.JPanel();
        tbp_tooth = new javax.swing.JTabbedPane();
        pnl_use = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_use = new javax.swing.JTable();
        pnl_charge = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_charge = new javax.swing.JTable();
        pnl_item = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_item = new javax.swing.JTable();
        pnl_statistic = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_statistic = new javax.swing.JTable();
        pnl_tooth = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_tooth = new javax.swing.JTable();
        pnl_btn = new javax.swing.JPanel();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        pnl_info = new javax.swing.JPanel();
        txtf_id = new javax.swing.JTextField();
        lbl_name = new javax.swing.JLabel();
        txtf_item_id = new javax.swing.JTextField();
        lbl_id = new javax.swing.JLabel();
        lbl_item_id = new javax.swing.JLabel();
        txtf_name = new javax.swing.JTextField();
        lbl_item_name = new javax.swing.JLabel();
        lbl_item_type = new javax.swing.JLabel();
        cmbx_item_type = new javax.swing.JComboBox<>();
        lbl_item_use_id = new javax.swing.JLabel();
        txtf_item_use_id = new javax.swing.JTextField();
        lbl_item_use_date = new javax.swing.JLabel();
        txtf_item_use_date = new javax.swing.JTextField();
        lbl_item_use_time = new javax.swing.JLabel();
        txtf_item_use_time = new javax.swing.JTextField();
        lbl_item_battery = new javax.swing.JLabel();
        txtf_item_battery = new javax.swing.JTextField();
        txtf_item_name = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbp_tooth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbp_toothStateChanged(evt);
            }
        });

        tbp_tooth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbp_toothMouseClicked(evt);
            }
        });

        tbl_use.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        tbl_use.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_useMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_use);

        javax.swing.GroupLayout pnl_useLayout = new javax.swing.GroupLayout(pnl_use);
        pnl_use.setLayout(pnl_useLayout);
        pnl_useLayout.setHorizontalGroup(
                pnl_useLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        pnl_useLayout.setVerticalGroup(
                pnl_useLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_useLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbp_tooth.addTab("Use", pnl_use);

        tbl_charge.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        tbl_charge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_chargeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_charge);

        javax.swing.GroupLayout pnl_chargeLayout = new javax.swing.GroupLayout(pnl_charge);
        pnl_charge.setLayout(pnl_chargeLayout);
        pnl_chargeLayout.setHorizontalGroup(
                pnl_chargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        pnl_chargeLayout.setVerticalGroup(
                pnl_chargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_chargeLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbp_tooth.addTab("Charge", pnl_charge);

        tbl_item.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        tbl_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_itemMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_item);

        javax.swing.GroupLayout pnl_itemLayout = new javax.swing.GroupLayout(pnl_item);
        pnl_item.setLayout(pnl_itemLayout);
        pnl_itemLayout.setHorizontalGroup(
                pnl_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        pnl_itemLayout.setVerticalGroup(
                pnl_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_itemLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbp_tooth.addTab("Item", pnl_item);

        tbl_statistic.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        tbl_statistic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_statisticMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_statistic);

        javax.swing.GroupLayout pnl_statisticLayout = new javax.swing.GroupLayout(pnl_statistic);
        pnl_statistic.setLayout(pnl_statisticLayout);
        pnl_statisticLayout.setHorizontalGroup(
                pnl_statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        pnl_statisticLayout.setVerticalGroup(
                pnl_statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_statisticLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbp_tooth.addTab("Statistic", pnl_statistic);

        tbl_tooth.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        tbl_tooth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_toothMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_tooth);

        javax.swing.GroupLayout pnl_toothLayout = new javax.swing.GroupLayout(pnl_tooth);
        pnl_tooth.setLayout(pnl_toothLayout);
        pnl_toothLayout.setHorizontalGroup(
                pnl_toothLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        pnl_toothLayout.setVerticalGroup(
                pnl_toothLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_toothLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbp_tooth.addTab("Tooth", pnl_tooth);

        javax.swing.GroupLayout pnl_tabLayout = new javax.swing.GroupLayout(pnl_tab);
        pnl_tab.setLayout(pnl_tabLayout);
        pnl_tabLayout.setHorizontalGroup(
                pnl_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tbp_tooth)
        );
        pnl_tabLayout.setVerticalGroup(
                pnl_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tbp_tooth, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_btnLayout = new javax.swing.GroupLayout(pnl_btn);
        pnl_btn.setLayout(pnl_btnLayout);
        pnl_btnLayout.setHorizontalGroup(
                pnl_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_btnLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_btnLayout.setVerticalGroup(
                pnl_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_btnLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(pnl_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        lbl_name.setText("Name");

        txtf_item_id.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_id.setText("Id");

        lbl_item_id.setText("Item Id");

        txtf_name.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_item_name.setText("Item Name");

        lbl_item_type.setText("Item Type");

        cmbx_item_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        lbl_item_use_id.setText("Use Id");

        txtf_item_use_id.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_item_use_date.setText("Use Date");

        txtf_item_use_date.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_item_use_time.setText("Use Time");

        txtf_item_use_time.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_item_battery.setText("Battery");

        txtf_item_battery.setPreferredSize(new java.awt.Dimension(64, 25));

        txtf_item_name.setPreferredSize(new java.awt.Dimension(64, 25));
        txtf_item_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_item_nameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_infoLayout = new javax.swing.GroupLayout(pnl_info);
        pnl_info.setLayout(pnl_infoLayout);
        pnl_infoLayout.setHorizontalGroup(
                pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbl_name, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtf_id)
                                                        .addComponent(txtf_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_type, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(cmbx_item_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_use_id, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_use_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_use_date, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_use_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_use_time, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_use_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                                .addComponent(lbl_item_battery, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtf_item_battery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pnl_infoLayout.setVerticalGroup(
                pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_infoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(17, 17, 17)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_type, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbx_item_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_use_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_use_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_use_date, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_use_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_use_time, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_use_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_item_battery, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtf_item_battery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pnl_tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnl_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnl_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnl_tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnl_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(pnl_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void initializeComboBox() {
        cmbx_item_type.removeAllItems();
        for (ItemType itemType : ItemType.values()) {
            cmbx_item_type.addItem(itemType.toString());
        }
    }

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedIndex = tbp_tooth.getSelectedIndex();

        if (selectedIndex != -1) {
            String tabTitle = tbp_tooth.getTitleAt(selectedIndex);

            if (tabTitle.equals("Item")) {
                addItem();
            } else if (tabTitle.equals("Charge")) {
                addCharge();
            } else {
                JOptionPane.showMessageDialog(null, "Please select the 'Item' or 'Charge' tab to add an entry.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a tab.");
        }

    }

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedIndex = tbp_tooth.getSelectedIndex();
        if (selectedIndex != -1) {
            String tabTitle = tbp_tooth.getTitleAt(selectedIndex);
            if (tabTitle.equals("Item")) {
                int id = Integer.parseInt(txtf_item_id.getText());
                String item_name = txtf_item_name.getText();
                String item_type_string = (String) cmbx_item_type.getSelectedItem();
                Item item = new Item(id, item_name, ItemType.valueOf(item_type_string));
                try {
                    dbFunctions.updateItem(item);
                    JOptionPane.showMessageDialog(null, "Item updated successfully!");

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating item: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the 'Item' tab to update an item.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a tab.");
        }

    }

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        txtf_id.setText("");
        txtf_name.setText("");
        txtf_item_id.setText("");
        txtf_item_name.setText("");
        cmbx_item_type.setSelectedIndex(0);
        txtf_item_use_id.setText("");
        txtf_item_use_date.setText("");
        txtf_item_use_time.setText("");
        txtf_item_battery.setText("");
    }

    private void tbl_toothMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_statisticMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void tbp_toothMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

    }

    private void tbl_chargeMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

    }

    private void tbl_useMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_itemMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        clearFields();
        int selectedIndex = tbl_item.getSelectedRow();
        if (selectedIndex != -1) {
            txtf_item_id.setText(model.getValueAt(selectedIndex, 0).toString());
            txtf_item_name.setText(model.getValueAt(selectedIndex, 1).toString());
            cmbx_item_type.setSelectedItem(model.getValueAt(selectedIndex, 2).toString());
        }
    }

    private void txtf_item_nameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbp_toothStateChanged(javax.swing.event.ChangeEvent evt) {
        JTabbedPane tabbedPane = (JTabbedPane) evt.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();
        String tabTitle = tabbedPane.getTitleAt(selectedIndex);
        System.out.println("Selected Index: " + selectedIndex + " Tab Title: " + tabTitle);
        if (tabTitle.equals("Item")) {
            try {
                List<Item> items = dbFunctions.getAllItems();
                model = new DefaultTableModel();
                model.setColumnIdentifiers(new Object[]{"Id", "Name", "Type"});
                for (Item item : items) {
                    model.addRow(new Object[]{item.getId(), item.getName(), item.getType()});
                }
                tbl_item.setModel(model);
            } catch (DbConnectionException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading items: " + e.getMessage());
            }
        } else if (tabTitle.equals("Charge")) {
            try {
                List<Charge> charges = dbFunctions.getAllCharges();
                model = new DefaultTableModel();
                model.setColumnIdentifiers(new Object[]{"charge id", "user id", "item id", "charge time", "charge date"});
                for (Charge charge : charges) {
                    model.addRow(new Object[]{charge.getCharge_id(), charge.getUser_id(), charge.getItem_id(), charge.getCharge_time(), charge.getCharge_date()});
                }
                tbl_charge.setModel(model);
            } catch (DbConnectionException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading charges: " + e.getMessage());
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmbx_item_type;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_item_battery;
    private javax.swing.JLabel lbl_item_id;
    private javax.swing.JLabel lbl_item_name;
    private javax.swing.JLabel lbl_item_type;
    private javax.swing.JLabel lbl_item_use_date;
    private javax.swing.JLabel lbl_item_use_id;
    private javax.swing.JLabel lbl_item_use_time;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JPanel pnl_btn;
    private javax.swing.JPanel pnl_charge;
    private javax.swing.JPanel pnl_info;
    private javax.swing.JPanel pnl_item;
    private javax.swing.JPanel pnl_statistic;
    private javax.swing.JPanel pnl_tab;
    private javax.swing.JPanel pnl_tooth;
    private javax.swing.JPanel pnl_use;
    private javax.swing.JTable tbl_charge;
    private javax.swing.JTable tbl_item;
    private javax.swing.JTable tbl_statistic;
    private javax.swing.JTable tbl_tooth;
    private javax.swing.JTable tbl_use;
    private javax.swing.JTabbedPane tbp_tooth;
    private javax.swing.JTextField txtf_id;
    private javax.swing.JTextField txtf_item_battery;
    private javax.swing.JTextField txtf_item_id;
    private javax.swing.JTextField txtf_item_name;
    private javax.swing.JTextField txtf_item_use_date;
    private javax.swing.JTextField txtf_item_use_id;
    private javax.swing.JTextField txtf_item_use_time;
    private javax.swing.JTextField txtf_name;
    // End of variables declaration
}
