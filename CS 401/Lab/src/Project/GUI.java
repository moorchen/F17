package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.ceil;
import static java.lang.Math.log;

public class GUI {

    private JButton startSystemButton;
    private JPanel panelMain;
    private JComboBox searchField;
    private JButton terminateSystemButton;
    private JTextField inputSite;
    private JTextField inputPeriod;
    private JTextField inputRate;
    private JTextField inputPrice;
    private JTextField inputName;
    private JComboBox inputStatus;
    private JButton addASingleCouponButton;
    private JButton addCouponsBundleButton;
    private JComboBox listField;
    private JButton listButton;
    private JTextField inputFile;
    private JScrollPane scrollPaneForTable;
    private JButton searchButton;
    private JScrollPane scrollPaneForSearch;
    private JTextField keyWord;
    private JTable ListTable;
    private JTable SearchTable;
    private JLabel searchOutput;
    private final Coupons[] myCoupons = {null};

    private JTable createTable() {
        Object[][] tableInfo = new Object[myCoupons[0].countNodes(myCoupons[0].ref)][7];
        ACoupon pos = myCoupons[0].ref;
        int i = 0;
        /*
        tableInfo[0][0] = "Coupon Provider";
        tableInfo[0][1] = "Product";
        tableInfo[0][2] = "Price";
        tableInfo[0][3] = "Rate";
        tableInfo[0][4] = "Period of Expiration";
        tableInfo[0][5] = "Status";
        tableInfo[0][6] = "Final Price";
        */
        while (pos != null) {
            tableInfo[i][0] = pos.site;
            //System.out.println(tableInfo[i][0]);
            tableInfo[i][1] = pos.name;
            tableInfo[i][2] = pos.price;
            tableInfo[i][3] = pos.rate;
            tableInfo[i][4] = pos.period;
            tableInfo[i][5] = pos.status.toString();
            tableInfo[i][6] = pos.finalP;
            i++;
            pos = pos.link;
        }
        String[] titles = {"Name of Provider", "Name of Product", "Price of Product", "Rate of Discount", "Expiration Period", "Coupon Status", "Final Price"};
        JTable table1 = new JTable(tableInfo, titles);
        table1.setFont(new Font("Menu.font", Font.PLAIN, 20));
        table1.setRowHeight(25);
        return table1;
    }

    private JTable createSearch(String key, fieldType field) {
        SearchedCoupons sc = myCoupons[0].searchByField(key, field);
        if (sc.n == 0) {
            int nnn = myCoupons[0].countNodes(myCoupons[0].ref);
            int nnnn = (int) ceil(log(nnn)) + 1;
            JOptionPane.showMessageDialog(null, "No Coupon is found " + nnn + " by linear search and " + nnnn + " by BST.");
        }
        Object[][] tableInfo = new Object[sc.n][9];
        SearchedCoupons.SearchedACoupon pos = sc.ref;
        int i = 0;
        while (pos != null) {
            tableInfo[i][0] = pos.site;
            tableInfo[i][1] = pos.name;
            tableInfo[i][2] = pos.price;
            tableInfo[i][3] = pos.rate;
            tableInfo[i][4] = pos.period;
            tableInfo[i][5] = pos.status.toString();
            tableInfo[i][6] = pos.finalP;
            tableInfo[i][7] = pos.linear;
            tableInfo[i][8] = pos.bst;
            i++;
            pos = pos.link;
        }
        String[] titles = {"Name of Provider", "Name of Product", "Price of Product", "Rate of Discount", "Expiration Period", "Coupon Status", "Final Price", "Linear", "BST"};
        JTable table1 = new JTable(tableInfo, titles);
        table1.setFont(new Font("Menu.font", Font.PLAIN, 20));
        table1.setRowHeight(25);
        return table1;
    }

    private GUI() {

        startSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCoupons[0] = new Coupons();
                //scrollPaneForTable.setBounds(0, 0, 700, 1000);
                ListTable = createTable();
                ListTable.setBounds(scrollPaneForTable.getBounds());
                ListTable.setPreferredScrollableViewportSize(ListTable.getPreferredSize());
                scrollPaneForTable.add(ListTable);
                JOptionPane.showMessageDialog(null, "A new coupon management system has been created.\n\r copyright Bozhou Chen");
            }
        });

        terminateSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myCoupons[0] == null) {
                    JOptionPane.showMessageDialog(null, "No live system right now.");
                } else {
                    myCoupons[0] = null;
                    JOptionPane.showMessageDialog(null, "The coupon management system has been terminated.");
                }
            }
        });

        addASingleCouponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myCoupons[0] == null) {
                    try {
                        throw new noLiveSystemException();
                    } catch (noLiveSystemException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (Objects.equals(inputPrice.getText(), "") || Objects.equals(inputSite.getText(), "") || Objects.equals(inputName.getText(), "") || Objects.equals(inputRate.getText(), "") || Objects.equals(inputPeriod.getText(), "")) {
                        String msg = "Please specify:\n\r";
                        if (Objects.equals(inputSite.getText(), "")) {
                            msg += "Name of Coupon Provider\n\r";
                        }
                        if (Objects.equals(inputName.getText(), "")) {
                            msg += "Name of Coupon\n\r";
                        }
                        if (Objects.equals(inputPrice.getText(), "")) {
                            msg += "Price of Coupon\n\r";
                        }
                        if (Objects.equals(inputRate.getText(), "")) {
                            msg += "Rate of Coupon\n\r";
                        }
                        if (Objects.equals(inputPeriod.getText(), "")) {
                            msg += "Expiration Period\n\r";
                        }
                        JOptionPane.showMessageDialog(null, msg);
                    } else {
                        String newSite = inputSite.getText();
                        String newName = inputName.getText();
                        double newPrice = Double.valueOf(inputPrice.getText());
                        int newRate = Integer.valueOf(inputRate.getText());
                        int newPeriod = Integer.valueOf(inputPeriod.getText());
                        statusType newStatus = null;

                        JComboBox copy = (JComboBox) inputStatus;
                        String msg = (String) copy.getSelectedItem();
                        switch (msg) {
                            default: {
                                newStatus = statusType.UNUSED;
                                break;
                            }
                            case "REDEEMED": {
                                newStatus = statusType.REDEEMED;
                                break;
                            }
                        }

                        myCoupons[0].add(newSite, newName, newPrice, newRate, newPeriod, newStatus);
                        JOptionPane.showMessageDialog(null, "Cheers! A new coupon has been added to system.");
                    }

                }
            }
        });

        inputSite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSite = inputSite.getText();
            }
        });

        inputName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = inputName.getText();
            }
        });

        inputPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double newPrice = Double.parseDouble(inputPrice.getText());
            }
        });

        inputRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newRate = Integer.parseInt(inputRate.getText());
            }
        });

        inputPeriod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newPeriod = Integer.parseInt(inputPeriod.getText());
            }
        });

        inputStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == inputStatus) {
                    JComboBox copy = (JComboBox) e.getSource();
                    String msg = (String) copy.getSelectedItem();
                    statusType newStatus = null;
                    switch (msg) {
                        case "UNUSED": {
                            newStatus = statusType.UNUSED;
                            break;
                        }
                        case "REDEEMED": {
                            newStatus = statusType.REDEEMED;
                            break;
                        }
                    }
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myCoupons[0] == null) {
                    try {
                        throw new noLiveSystemException();
                    } catch (noLiveSystemException e1) {
                        e1.printStackTrace();
                    }
                } else {

                    fieldType newField = null;
                    String msg = (String) listField.getSelectedItem();
                    switch (msg) {
                        default: {
                            newField = fieldType.SITE;
                            break;
                        }
                        case "Name of Product": {
                            newField = fieldType.NAME;
                            break;
                        }
                        case "Price of Product": {
                            newField = fieldType.PRICE;
                            break;
                        }
                        case "Rate of Discount": {
                            newField = fieldType.RATE;
                            break;
                        }
                        case "Expiration Period": {
                            newField = fieldType.PERIOD;
                            break;
                        }
                        case "Coupon Status": {
                            newField = fieldType.STATUS;
                            break;
                        }
                        case "Final Price": {
                            newField = fieldType.FINAL;
                            break;
                        }
                    }
                    myCoupons[0].doSort(newField);
                    //listOutput.setText(myCoupons[0].toString());
                    ListTable = createTable();
                    //scrollPaneForTable.setBounds(0, 0, 700, 1000);
                    ListTable.setBounds(scrollPaneForTable.getBounds());
                    //scrollPaneForTable.add(ListTable);
                    scrollPaneForTable.setViewportView(ListTable);
                    scrollPaneForTable.revalidate();
                    System.out.println("done");
                }
            }
        });

        addCouponsBundleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myCoupons[0] == null) {
                    try {
                        throw new noLiveSystemException();
                    } catch (noLiveSystemException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        File emp = new File(inputFile.getText());
                        Scanner scanner0 = new Scanner(emp);
                        while (scanner0.hasNext()) {
                            String newSite = scanner0.next();
                            String newName = scanner0.next();
                            double newPrice = Double.valueOf(scanner0.next());
                            int newRate = Integer.valueOf(scanner0.next());
                            int newPeriod = Integer.valueOf(scanner0.next());
                            statusType newStatus = null;

                            String msg = (String) scanner0.next();
                            switch (msg) {
                                default: {
                                    newStatus = statusType.UNUSED;
                                    break;
                                }
                                case "REDEEMED": {
                                    newStatus = statusType.REDEEMED;
                                    break;
                                }
                            }

                            myCoupons[0].add(newSite, newName, newPrice, newRate, newPeriod, newStatus);

                        }
                        scanner0.close();
                        JOptionPane.showMessageDialog(null, "Cheers! A new coupon bundle has been added to system.");

                    } catch (FileNotFoundException e1) {
                        System.out.println("ERROR!");
                    }

                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myCoupons[0] == null) {
                    try {
                        throw new noLiveSystemException();
                    } catch (noLiveSystemException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    fieldType newField;
                    String msg = (String) searchField.getSelectedItem();
                    switch (msg) {
                        default: {
                            newField = fieldType.SITE;
                            SearchTable = createSearch(keyWord.getText(), newField);
                            break;
                        }
                        case "Name of Product": {
                            newField = fieldType.NAME;
                            SearchTable = createSearch(keyWord.getText(), newField);
                            break;
                        }
                        case "Price of Product": {
                            if (keyWord.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Please insert key word.");
                                throw new NullPointerException();
                            } else {
                                newField = fieldType.PRICE;
                                SearchTable = createSearch(keyWord.getText(), newField);
                            }
                            break;
                        }
                        case "Rate of Discount": {
                            if (keyWord.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Please insert key word.");
                                throw new NullPointerException();
                            } else {
                                newField = fieldType.RATE;
                                SearchTable = createSearch(keyWord.getText(), newField);
                            }
                            break;
                        }
                        case "Expiration Period": {
                            if (keyWord.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Please insert key word.");
                                throw new NullPointerException();
                            } else {
                                newField = fieldType.PERIOD;
                                SearchTable = createSearch(keyWord.getText(), newField);
                            }
                            break;
                        }
                        case "Coupon Status": {
                            if (keyWord.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Please insert key word.");
                                throw new NullPointerException();
                            } else {
                                newField = fieldType.STATUS;
                                SearchTable = createSearch(keyWord.getText(), newField);
                            }
                            break;
                        }
                    }
                    //SearchTable = createSearch(keyWord.getText(), newField);
                    SearchTable.setBounds(scrollPaneForSearch.getBounds());
                    scrollPaneForSearch.setViewportView(SearchTable);
                }
            }
        });

        scrollPaneForTable.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });

        scrollPaneForSearch.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });


    }

    private class noLiveSystemException extends Exception {
        noLiveSystemException() {
            System.out.println("ERROR! A system should be initialized to perform actions.");
            JOptionPane.showMessageDialog(null, "ERROR! A system should be initialized to perform actions.");
        }
    }

    public static void main(String args[]) {
        JFrame jframe = new JFrame("GUI");
        jframe.setContentPane(new GUI().panelMain);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
