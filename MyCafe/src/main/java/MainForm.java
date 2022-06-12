import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.InternationalFormatter;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class MainForm extends JFrame {
    private JPanel mainPane;
    private JPanel leftPane;
    private JPanel rightPane;
    private JSplitPane splitMainPane;
    private JPanel leftMainPane;
    private JPanel leftEditProPane;
    private JLabel picDown;
    private JPanel leftChangePasswordPane;
    private JPanel leftProductsPane;
    private JPanel rightMainPane;
    private JPanel rightProductsPane;
    private JPanel rightProfilePane;
    private JPanel rightPasswordPane;
    private JPanel rightEditInfoPane;
    private JPanel leftOrdersPane;
    private JPanel leftEditInfoPane;
    private JLabel rightMainLbIdentity;
    private JLabel rightMainLbPosition;
    private JPanel picDownPnl;

    private JPasswordField pfChangePassCur;
    private JPasswordField pfChangePassNew;
    private JPasswordField pfChangePassNewCon;
    private JButton changePasswordButton;
    private JTextField rightTfMail;
    private JTextField rightTfName;
    private JTextField rightTfSurname;
    private JTextField rightTfPhone;
    private JComboBox rightCBoxMail;
    private JComboBox rightCBoxPosition;
    private JButton applyChangesButton;
    private JPanel rightChangePasswordPane;
    private JPanel rightEditProfileInfoPane;
    private JComboBox cBProEdit;
    private JButton changeProductInfoButton;
    private JLabel lbProductChange;
    private JComboBox cBProEditName;
    private JTextField tfProChangeName;
    private JComboBox cBProChange;
    private JPanel rightProductAddPane;
    private JTextField tfProAddDescription;
    private JComboBox cBProAdd;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JPanel rightOrdersPane;
    private JPanel picDownProductPnl;
    private JLabel picDownProduct;
    private JPanel leftAddProPnl;
    private JPanel leftEditProPnl;
    private JPanel closePnl;
    private JPanel rightProductMainPane;
    private JLabel lblEmployee;
    private JComboBox cBProAddType;
    private JTextField tfProAddName;
    private JFormattedTextField tfProAddPrice;
    private JTextField tfProAddIngredients;
    private JComboBox cBProChangeType;
    private JTextField tfProChangeIngredients;
    private JFormattedTextField tfProChangePrice;
    private JTextField tfProChangeDescription;
    private JTable tblOrders;
    private JTextField tfOrdersSearch;
    private JTextField tfOrderId;
    private JList listFoods;
    private JList listDrinks;
    private JTextField tfCustomerName;
    private JTextField tfCustomerMail;
    private JTextField tfCustomerAddress;
    private JTextField tfOrderNote;
    private JFormattedTextField tfTotalPrice;
    private JButton btnSubmitOrder;
    private JButton btnPrintOrder;
    private JPanel leftSubmitOrder;
    private JPanel leftReviewOrder;
    private JLabel picDownOrder;
    private JPanel picDownOrderPnl;
    private JPanel rightOrderReview;
    private JLabel lblQRCustomer;
    private JButton btnOrderReady;
    private JButton btnReadQR;
    private JTable tblQR;
    private JLabel lblQRNote;
    private JLabel iconPosition;

    private CardLayout c1 = (CardLayout) rightPane.getLayout();

    //private Color sideBarColorHover = new Color(255, 248, 235);
    private Color sideBarColorHover = new Color(85, 85, 85);
    //private Color sideBarBottomLine = new Color(157, 217, 210);
    private Color sideBarBottomLine = new Color(254, 88, 0);
    //private Color backgroundMain = new Color(255, 248, 240);
    private Color backgroundMain = new Color(51, 51, 51);
    private DatabaseManager manager;
    private Employee loggedEmployee;
    private boolean isRefreshing = false;
    private String authPass = "";

    public MainForm(Employee loggedEmployee) {
        manager = DatabaseManager.getInstance();
        this.loggedEmployee = loggedEmployee;
        lblEmployee.setText(loggedEmployee.getName() + " " + loggedEmployee.getSurname());
        rightMainLbIdentity.setText(loggedEmployee.getName() + " " + loggedEmployee.getSurname());
        rightMainLbPosition.setText("Your Position at This Amazing Cafe is " + loggedEmployee.getPosition());
        leftPane.setBackground(backgroundMain);
        leftEditInfoPane.setBackground(backgroundMain);
        leftChangePasswordPane.setBackground(backgroundMain);
        leftMainPane.setBackground(backgroundMain);
        leftOrdersPane.setBackground(backgroundMain);
        leftProductsPane.setBackground(backgroundMain);
        leftEditProPane.setBackground(backgroundMain);
        rightMainPane.setBackground(backgroundMain);
        rightEditInfoPane.setBackground(backgroundMain);
        rightPasswordPane.setBackground(backgroundMain);
        rightProductsPane.setBackground(backgroundMain);
        rightProfilePane.setBackground(backgroundMain);
        picDownPnl.setBackground(backgroundMain);
        picDownOrderPnl.setBackground(backgroundMain);
        leftAddProPnl.setBackground(backgroundMain);
        leftEditProPnl.setBackground(backgroundMain);
        rightProductAddPane.setBackground(backgroundMain);
        picDownProductPnl.setBackground(backgroundMain);
        closePnl.setBackground(backgroundMain);
        leftSubmitOrder.setBackground(backgroundMain);
        leftReviewOrder.setBackground(backgroundMain);

        setContentPane(mainPane);
        setSize(1300, 700);
        setTitle("My Cafe");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(1280, 500));
        setLocationRelativeTo(null);
        splitMainPane.setDividerSize(2);
        splitMainPane.setDividerLocation(0.2);

        rightCBoxMail.addItem("gmail.com");
        rightCBoxMail.addItem("hotmail.com");
        rightCBoxMail.addItem("outlook.com");
        rightCBoxPosition.addItem("Manager");
        rightCBoxPosition.addItem("Deputy Manager");
        rightCBoxPosition.addItem("Cashier");
        rightCBoxPosition.addItem("Waiter");
        rightCBoxPosition.addItem("Waitress");
        rightCBoxPosition.addItem("Head Chef");
        rightCBoxPosition.addItem("Chef");
        rightCBoxPosition.addItem("Assistant Chef");
        rightCBoxPosition.addItem("Barista");
        cBProEdit.addItem("Food");
        cBProEdit.addItem("Drink");
        cBProAdd.addItem("Food");
        cBProAdd.addItem("Drink");
        cBProChange.addItem("Food");
        cBProChange.addItem("Drink");
        cBProAddType.addItem("Hot");
        cBProAddType.addItem("Cold");
        cBProChangeType.addItem("Hot");
        cBProChangeType.addItem("Cold");
        setPositionIcons();
        JFormattedTextField.AbstractFormatterFactory abstractFormatterFactory = new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(0.0);
                formatter.setMaximum(Double.MAX_VALUE);
                return formatter;
            }
        };
        tfProAddPrice.setFormatterFactory(abstractFormatterFactory);
        tfProChangePrice.setFormatterFactory(abstractFormatterFactory);
        tfTotalPrice.setFormatterFactory(abstractFormatterFactory);
        tfProAddPrice.setText("0.00");
        tfProChangePrice.setText("0.00");
        tfTotalPrice.setText("0.00");

        tblQR.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rightEditProfileInfoPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, sideBarBottomLine));
        rightChangePasswordPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, sideBarBottomLine));
        leftChangePasswordPane.setVisible(false);
        leftEditInfoPane.setVisible(false);
        leftAddProPnl.setVisible(false);
        leftEditProPnl.setVisible(false);
        leftSubmitOrder.setVisible(false);
        leftReviewOrder.setVisible(false);

        leftMainPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card1");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftMainPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftMainPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        leftEditProPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card3");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftEditProPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftEditProPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        picDownProduct.addMouseListener(new MouseAdapter() {
            boolean clickNum2 = true;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickNum2) {
                    Icon sourceIcon2 = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Close.png")));
                    picDownProduct.setIcon(sourceIcon2);
                    leftAddProPnl.setVisible(true);
                    leftEditProPnl.setVisible(true);
                    clickNum2 = !clickNum2;

                    //setSize(1350,700);

                } else {
                    Icon sourceIcon2 = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Down.png")));
                    picDownProduct.setIcon(sourceIcon2);
                    leftAddProPnl.setVisible(false);
                    leftEditProPnl.setVisible(false);

                    //setSize(1200,700);
                    clickNum2 = !clickNum2;
                }
            }
        });
        picDownProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftProductsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftProductsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        picDownOrder.addMouseListener(new MouseAdapter() {
            boolean clickNum = true;

            @Override
            public void mouseClicked(MouseEvent e) {


                if (clickNum) {
                    Icon sourceIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Close.png")));
                    picDownOrder.setIcon(sourceIcon);
                    leftSubmitOrder.setVisible(true);
                    leftReviewOrder.setVisible(true);
                    clickNum = !clickNum;
                } else {
                    Icon sourceIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Down.png")));
                    picDownOrder.setIcon(sourceIcon);
                    leftSubmitOrder.setVisible(false);
                    leftReviewOrder.setVisible(false);
                    clickNum = !clickNum;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftOrdersPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftOrdersPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });
        picDown.addMouseListener(new MouseAdapter() {
            boolean clickNum = true;

            @Override
            public void mouseClicked(MouseEvent e) {


                if (clickNum) {
                    Icon sourceIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Close.png")));
                    picDown.setIcon(sourceIcon);
                    leftChangePasswordPane.setVisible(true);
                    leftEditInfoPane.setVisible(true);
                    clickNum = !clickNum;
                } else {
                    Icon sourceIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/Down.png")));
                    picDown.setIcon(sourceIcon);
                    leftChangePasswordPane.setVisible(false);
                    leftEditInfoPane.setVisible(false);
                    clickNum = !clickNum;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftEditProPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftEditProPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });

        leftChangePasswordPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card4");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftChangePasswordPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftChangePasswordPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        leftEditInfoPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setEditProfile();
                c1.show(rightPane, "Card5");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftEditInfoPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftEditInfoPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        leftProductsPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshProducts();
                c1.show(rightPane, "Card2");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftProductsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftProductsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });
        leftOrdersPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Order> orders = manager.getOrders();
                OrdersTableModel otm = new OrdersTableModel(orders);
                tblOrders.setModel(otm);
                setOrderTableColours();
                c1.show(rightPane, "Card7");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftOrdersPane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftOrdersPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));
            }
        });


        rightChangePasswordPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card4");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rightChangePasswordPane.setBackground(sideBarColorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightChangePasswordPane.setBackground(backgroundMain);

            }
        });
        rightEditProfileInfoPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setEditProfile();
                c1.show(rightPane, "Card5");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rightEditProfileInfoPane.setBackground(sideBarColorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightEditProfileInfoPane.setBackground(backgroundMain);

            }
        });

        leftEditProPnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshProducts();
                c1.show(rightPane, "Card2");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftEditProPnl.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftEditProPnl.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });
        leftAddProPnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card6");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftAddProPnl.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftAddProPnl.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });
        leftReviewOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(rightPane, "Card9");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftReviewOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftReviewOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });
        leftSubmitOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Order> orders = manager.getOrders();
                OrdersTableModel otm = new OrdersTableModel(orders);
                tblOrders.setModel(otm);
                setOrderTableColours();
                c1.show(rightPane, "Card7");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                leftSubmitOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, sideBarBottomLine));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftSubmitOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, sideBarBottomLine));

            }
        });
        closePnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainForm.this.dispose();
                new LoginForm();
            }
        });


        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pfChangePassCur.getText().equals("") || !pfChangePassNew.getText().equals("") || !pfChangePassNewCon.getText().equals("")) {
                    if (loggedEmployee.getPassword().equals(pfChangePassCur.getText())) {
                        if (pfChangePassNew.getText().equals(pfChangePassNewCon.getText())) {
                            String temp = loggedEmployee.getPassword();
                            loggedEmployee.setPassword(pfChangePassNew.getText());
                            boolean isUpdated = manager.updateEmployee(loggedEmployee);
                            if (isUpdated) {
                                JOptionPane.showMessageDialog(null, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                pfChangePassCur.setText("");
                                pfChangePassNew.setText("");
                                pfChangePassNewCon.setText("");
                                return;
                            } else {
                                loggedEmployee.setPassword(temp);
                                JOptionPane.showMessageDialog(null, "There was an error while changing password!", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "New password and confirm password are not the same", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Current password is not correct", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        applyChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rightTfMail.getText().equals("") || rightTfName.getText().equals("") || rightTfSurname.getText().equals("") || rightTfPhone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    String tempMail = loggedEmployee.getMail();
                    String tempName = loggedEmployee.getName();
                    String tempSurname = loggedEmployee.getSurname();
                    String tempPhone = loggedEmployee.getPhone();
                    int tempPosition = rightCBoxPosition.getSelectedIndex();
                    loggedEmployee.setMail(rightTfMail.getText() + "@" + rightCBoxMail.getSelectedItem().toString());
                    loggedEmployee.setName(rightTfName.getText());
                    loggedEmployee.setSurname(rightTfSurname.getText());
                    loggedEmployee.setPhone(rightTfPhone.getText());
                    loggedEmployee.setPosition(rightCBoxPosition.getSelectedItem().toString());
                    boolean isUpdated = manager.updateEmployee(loggedEmployee);
                    if (isUpdated) {
                        JOptionPane.showMessageDialog(null, "Changes applied successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        rightTfMail.setText("");
                        rightTfName.setText("");
                        rightTfSurname.setText("");
                        rightTfPhone.setText("");
                        return;
                    } else {
                        loggedEmployee.setMail(tempMail);
                        loggedEmployee.setName(tempName);
                        loggedEmployee.setSurname(tempSurname);
                        loggedEmployee.setPhone(tempPhone);
                        loggedEmployee.setPosition(rightCBoxPosition.getItemAt(tempPosition).toString());
                        JOptionPane.showMessageDialog(null, "There was an error while applying changes!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                if (cBProAdd.getSelectedItem().toString().equals("")) {
                    if (tfProAddDescription.getText().toString().equals("")) check = false;
                    else check = true;
                } else {
                    check = true;
                }
                if (tfProAddName.getText().equals("") || !check || tfProAddPrice.getText().equals("") || tfProAddIngredients.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    Date date = new Date();
                    long id = date.getTime();
                    boolean isAdded = false;
                    if (cBProAdd.getSelectedItem().toString().equals("Food")) {
                        Food food = new Food(id, tfProAddName.getText(), Float.parseFloat(tfProAddPrice.getText()), tfProAddIngredients.getText(), cBProAddType.getSelectedItem().toString(), tfProAddDescription.getText());
                        isAdded = manager.addFood(food);
                    } else if (cBProAdd.getSelectedItem().toString().equals("Drink")) {
                        Drink drink = new Drink(id, tfProAddName.getText(), Float.parseFloat(tfProAddPrice.getText()), tfProAddIngredients.getText(), cBProAddType.getSelectedItem().toString());
                        isAdded = manager.addDrink(drink);
                    }
                    if (isAdded) {
                        JOptionPane.showMessageDialog(null, "Product added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        tfProAddName.setText("");
                        tfProAddDescription.setText("");
                        tfProAddPrice.setText("0.00");
                        tfProAddIngredients.setText("");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error while adding product!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });
        cBProAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cBProAdd.getSelectedItem().toString().equals("Food")) {
                    tfProAddDescription.setEnabled(true);
                } else {
                    tfProAddDescription.setEnabled(false);
                }
            }
        });
        cBProEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshProducts();
            }
        });
        cBProEditName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRefreshing) return;
                else refreshNames();
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDeleted = false;
                if (cBProEdit.getSelectedItem().equals("Food")) {
                    Food food = (Food) cBProEditName.getSelectedItem();
                    if (food != null) isDeleted = manager.deleteFood(food);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(null, "Food deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        refreshProducts();
                        refreshNames();
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error while deleting food!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Drink drink = (Drink) cBProEditName.getSelectedItem();
                    if (drink != null) isDeleted = manager.deleteDrink(drink);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(null, "Drink deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        refreshProducts();
                        refreshNames();
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error while deleting drink!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        changeProductInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cBProEdit.getSelectedItem().equals("Food")) {
                    Food food = (Food) cBProEditName.getSelectedItem();
                    if (food != null) {
                        food.setName(tfProChangeName.getText());
                        food.setDescription(tfProChangeDescription.getText());
                        food.setPrice(Float.parseFloat(tfProChangePrice.getText()));
                        food.setIngredients(tfProChangeIngredients.getText());
                        food.setType(cBProChangeType.getSelectedItem().toString());
                        boolean isUpdated = manager.updateFood(food);
                        if (isUpdated) {
                            JOptionPane.showMessageDialog(null, "Changes applied successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            refreshProducts();
                        } else {
                            JOptionPane.showMessageDialog(null, "There was an error while applying changes!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    Drink drink = (Drink) cBProEditName.getSelectedItem();
                    if (drink != null) {
                        drink.setName(tfProChangeName.getText());
                        drink.setPrice(Float.parseFloat(tfProChangePrice.getText()));
                        drink.setIngredients(tfProChangeIngredients.getText());
                        drink.setType(cBProChangeType.getSelectedItem().toString());
                        boolean isUpdated = manager.updateDrink(drink);
                        if (isUpdated) {
                            JOptionPane.showMessageDialog(null, "Changes applied successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            refreshProducts();
                            refreshNames();
                        } else {
                            JOptionPane.showMessageDialog(null, "There was an error while applying changes!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        tfOrdersSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                setOrdersList(tfOrdersSearch.getText());
            }
        });
        tblOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedRow = tblOrders.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                } else {
                    long orderId = (long) tblOrders.getValueAt(selectedRow, 0);
                    ArrayList<Order> orders = manager.getOrders();
                    for (Order order : orders) {
                        if (order.getId() == orderId) {
                            tfOrderId.setText(String.valueOf(order.getId()));
                            tfCustomerName.setText(order.getCustomerName() + " " + order.getCustomerSurname());
                            tfCustomerMail.setText(order.getCustomerMail());
                            tfCustomerAddress.setText(order.getCustomerAddress());
                            order.setList(manager.getFoods(), manager.getDrinks());
                            DefaultListModel foodListModel = new DefaultListModel();
                            for (Food food : order.getFoodList()) {
                                foodListModel.addElement(food);
                                listFoods.setModel(foodListModel);
                            }
                            DefaultListModel drinkListModel = new DefaultListModel();
                            for (Drink drink : order.getDrinkList()) {
                                drinkListModel.addElement(drink);
                                listDrinks.setModel(drinkListModel);
                            }
                            calculatePrice();
                            return;
                        }
                    }
                }
            }
        });
        listFoods.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = listFoods.getSelectedIndex();
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this food?", "Delete", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        DefaultListModel model = (DefaultListModel) listFoods.getModel();
                        Food food = (Food) model.getElementAt(selectedRow);
                        model.remove(selectedRow);
                    }
                    calculatePrice();
                }
            }
        });
        listDrinks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = listDrinks.getSelectedIndex();
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this drink?", "Delete", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        DefaultListModel model = (DefaultListModel) listDrinks.getModel();
                        Drink drink = (Drink) model.getElementAt(selectedRow);
                        model.remove(selectedRow);
                    }
                    calculatePrice();
                }
            }
        });
        btnSubmitOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfCustomerName.getText().isEmpty() || tfCustomerMail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill the name and mail fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    boolean isUpdated = manager.updateOrderStatus(Long.parseLong(tfOrderId.getText()));
                    if (isUpdated) {
                        String content = tfOrderId.getText() + "," + tfCustomerName.getText();
                        for (int i = 0; i < listFoods.getModel().getSize(); i++) {
                            Food food = (Food) listFoods.getModel().getElementAt(i);
                            content += ",f" + food.getId();
                        }
                        for (int i = 0; i < listDrinks.getModel().getSize(); i++) {
                            Drink drink = (Drink) listDrinks.getModel().getElementAt(i);
                            content += ",d" + drink.getId();
                        }
                        content += ",note:" + tfOrderNote.getText();
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Save Order QR-Code");
                        fileChooser.setCurrentDirectory(new File("src/main/resources/orders/"));
                        fileChooser.setSelectedFile(new File(tfOrderId.getText() + ".jpg"));
                        fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg"));
                        int result = fileChooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            try {
                                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 640, 640);
                                MatrixToImageWriter.writeToFile(bitMatrix, "jpg", selectedFile);
                                int option = JOptionPane.showConfirmDialog(null, "Do you want to send the receipt?", "Send", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    sendReceipt(selectedFile, null, false);
                                }
                            } catch (IOException | WriterException exception) {
                                exception.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error while submitting order!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        setOrdersList("");
                        setOrderTableColours();
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error while submitting order!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnPrintOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder content = new StringBuilder();
                int option = JOptionPane.showConfirmDialog(null, "Do you want to print the receipt?", "Print", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    content.append("**********************************************************\n");
                    content.append("               Marmara University Café Bistro               \n");
                    content.append("**********************************************************\n");
                    content.append("Order ID: " + tfOrderId.getText() + "\n");
                    content.append("Customer Name: " + tfCustomerName.getText() + "\n");
                    content.append("Customer Mail: " + tfCustomerMail.getText() + "\n");
                    content.append("Customer Address: " + tfCustomerAddress.getText() + "\n");
                    content.append("**********************************************************\n");
                    content.append("Foods:\n");
                    for (int i = 0; i < listFoods.getModel().getSize(); i++) {
                        Food food = (Food) listFoods.getModel().getElementAt(i);
                        content.append(food.getName() + ": " + food.getPrice() + "$\n");
                    }
                    content.append("**********************************************************\n");
                    content.append("Drinks:\n");
                    for (int i = 0; i < listDrinks.getModel().getSize(); i++) {
                        Drink drink = (Drink) listDrinks.getModel().getElementAt(i);
                        content.append(drink.getName() + ": " + drink.getPrice() + "$\n");
                    }
                    content.append("**********************************************************\n");
                    content.append("Total Price: " + tfTotalPrice.getText() + "$\n");
                    content.append("**********************************************************\n");
                    content.append("Order Note: " + tfOrderNote.getText() + "\n");
                    content.append("**********************************************************\n");
                    content.append("Thank you for your order!\n");
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    printerJob.setPrintable(new Printable() {
                        @Override
                        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                            if (pageIndex > 0) {
                                return Printable.NO_SUCH_PAGE;
                            }
                            Graphics2D graphics2D = (Graphics2D) graphics;
                            graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                            graphics2D.scale(0.75, 0.75);
                            int y = 30;
                            for (String str : content.toString().split("\n")) {
                                graphics2D.drawString(str, 0, y += graphics2D.getFontMetrics().getHeight());
                            }
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    if (printerJob.printDialog()) {
                        try {
                            printerJob.print();
                        } catch (PrinterException exception) {
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error while printing!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                int sendMail = JOptionPane.showConfirmDialog(null, "Do you want to send the receipt?", "Send", JOptionPane.YES_NO_OPTION);
                if (sendMail == JOptionPane.YES_OPTION) {
                    sendReceipt(null, content.toString(), true);
                }
            }
        });
        btnReadQR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Read Order QR-Code");
                fileChooser.setCurrentDirectory(new File("src/main/resources/orders/"));
                fileChooser.setSelectedFile(new File(""));
                fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(selectedFile);
                        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                        Result qrResult = new MultiFormatReader().decode(binaryBitmap);
                        String contents = qrResult.getText();
                        String[] lines = contents.split(",");
                        ArrayList<QRObject> qrObjects = new ArrayList<QRObject>();
                        long orderId = Long.parseLong(lines[0]);
                        lblQRCustomer.setText(lines[1]);
                        for (int i = 2; i < lines.length; i++) {
                            if (lines[i].startsWith("f")) {
                                long foodID = Long.parseLong(lines[i].substring(1));
                                Food food = manager.searchFood(foodID);
                                if (food != null)
                                    qrObjects.add(new QRObject("Food", food.getName(), food.getIngredients(), food.getType()));
                            } else if (lines[i].startsWith("d")) {
                                long drinkID = Long.parseLong(lines[i].substring(1));
                                Drink drink = manager.searchDrink(drinkID);
                                if (drink != null)
                                    qrObjects.add(new QRObject("Drink", drink.getName(), drink.getIngredients(), drink.getType()));
                            } else if (lines[i].startsWith("note:")) {
                                lblQRNote.setText("Customer Note: " + lines[i].substring(5));
                            }
                            QRTableModel qrTableModel = new QRTableModel(qrObjects, orderId);
                            tblQR.setModel(qrTableModel);
                            btnOrderReady.setEnabled(false);
                            btnReadQR.setEnabled(false);
                        }

                    } catch (IOException | NotFoundException exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error while reading QR-Code!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tblQR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                QRTableModel qrTableModel = (QRTableModel) tblQR.getModel();
                QRObject qrObject = qrTableModel.getObject(tblQR.getSelectedRow());
                qrObject.setReady(!qrObject.isReady());
                qrTableModel.fireTableDataChanged();
                if (qrTableModel.isAllReady()) btnOrderReady.setEnabled(true);
                else btnOrderReady.setEnabled(false);
            }
        });
        btnOrderReady.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int isOkay = JOptionPane.showConfirmDialog(null, "Are you sure you want to mark this order as ready?", "Order Management", JOptionPane.YES_NO_OPTION);
                if (isOkay == JOptionPane.YES_OPTION) {
                    QRTableModel qrTableModel = (QRTableModel) tblQR.getModel();
                    boolean isCleared = manager.clearOrder(qrTableModel.getOrderID());
                    if (isCleared) {
                        lblQRCustomer.setText("");
                        lblQRNote.setText("");
                        btnReadQR.setEnabled(true);
                        btnOrderReady.setEnabled(false);
                        qrTableModel.clear();
                        JOptionPane.showMessageDialog(null, "Order is marked as ready!", "Order Management", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void setEditProfile() {
        rightTfMail.setText(loggedEmployee.getMail().split("@")[0]);
        for (int i = 0; i < rightCBoxMail.getItemCount(); i++) {
            if (rightCBoxMail.getItemAt(i).toString().equals(loggedEmployee.getMail().split("@")[1])) {
                rightCBoxMail.setSelectedIndex(i);
                break;
            }
        }
        rightTfName.setText(loggedEmployee.getName());
        rightTfSurname.setText(loggedEmployee.getSurname());
        for (int i = 0; i < rightCBoxPosition.getItemCount(); i++) {
            if (rightCBoxPosition.getItemAt(i).equals(loggedEmployee.getPosition())) {
                rightCBoxPosition.setSelectedIndex(i);
                break;
            }
        }
        rightTfPhone.setText(loggedEmployee.getPhone());
    }

    private void refreshProducts() {
        isRefreshing = true;
        if (cBProEdit.getSelectedItem().toString().equals("Food")) {
            tfProChangeDescription.setEnabled(true);
            ArrayList<Food> foodList = manager.getFoods();
            cBProEditName.removeAllItems();
            for (Food food : foodList) {
                cBProEditName.addItem(food);
            }
        } else {
            tfProChangeDescription.setEnabled(false);
            ArrayList<Drink> drinkList = manager.getDrinks();
            cBProEditName.removeAllItems();
            for (Drink drink : drinkList) {
                cBProEditName.addItem(drink);
            }
        }
        isRefreshing = false;
        refreshNames();
    }

    private void refreshNames() {
        tfProChangeName.setText("");
        tfProChangePrice.setText("0.00");
        tfProChangeIngredients.setText("");
        tfProChangeDescription.setText("");
        if (cBProEdit.getSelectedItem().toString().equals("Food")) {
            Food selectedFood = (Food) cBProEditName.getSelectedItem();
            if (selectedFood != null) {
                cBProChange.setSelectedIndex(cBProEdit.getSelectedIndex());
                tfProChangeName.setText(selectedFood.getName());
                for (int i = 0; i < cBProChangeType.getItemCount(); i++) {
                    if (cBProChangeType.getItemAt(i).toString().equals(selectedFood.getType())) {
                        cBProChangeType.setSelectedIndex(i);
                        break;
                    }
                }
                tfProChangeIngredients.setText(selectedFood.getIngredients());
                tfProChangeDescription.setText(selectedFood.getDescription());
                tfProChangePrice.setText(String.valueOf(selectedFood.getPrice()));
            }
        } else {
            Drink selectedDrink = (Drink) cBProEditName.getSelectedItem();
            if (selectedDrink != null) {
                cBProChange.setSelectedIndex(cBProEdit.getSelectedIndex());
                tfProChangeName.setText(selectedDrink.getName());
                for (int i = 0; i < cBProChangeType.getItemCount(); i++) {
                    if (cBProChangeType.getItemAt(i).toString().equals(selectedDrink.getType())) {
                        cBProChangeType.setSelectedIndex(i);
                        break;
                    }
                }
                tfProChangeIngredients.setText(selectedDrink.getIngredients());
                tfProChangePrice.setText(String.valueOf(selectedDrink.getPrice()));
            }
        }
    }

    private void setOrdersList(String search) {
        ArrayList<Order> orders = manager.getOrders();
        ArrayList<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            String fullName = order.getCustomerName() + " " + order.getCustomerSurname();
            if (fullName.toLowerCase().contains(search.toLowerCase())) {
                filteredOrders.add(order);
            }
        }
        OrdersTableModel otm = new OrdersTableModel(filteredOrders);
        tblOrders.setModel(otm);
        setOrderTableColours();
    }

    private void calculatePrice() {
        double price = 0;
        for (int i = 0; i < listFoods.getModel().getSize(); i++) {
            Food food = (Food) listFoods.getModel().getElementAt(i);
            price += food.getPrice();
        }
        for (int i = 0; i < listDrinks.getModel().getSize(); i++) {
            Drink drink = (Drink) listDrinks.getModel().getElementAt(i);
            price += drink.getPrice();
        }
        tfTotalPrice.setText(String.valueOf(price));
    }

    private void sendReceipt(File selectedFile, String receipt, boolean isPrint) {
        try {
            String from = "marun.cse2062@gmail.com";
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("marun.cse2062@gmail.com", authPass);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tfCustomerMail.getText()));
            message.setSubject("Order receipt");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            MimeBodyPart orderText = new MimeBodyPart();
            if (!isPrint) {
                File attachmentFile = selectedFile;
                attachment.attachFile(attachmentFile);
                orderText.setText("Dear " + tfCustomerName.getText() + ",\n\n"
                        + "Thank you for your order.\n"
                        + "Your order ID is " + tfOrderId.getText() + ".\n"
                        + "Total Price is " + tfTotalPrice.getText() + "$.\n"
                        + "You can find your order's QR code in the attachment.\n"
                        + "Good day,\n"
                        + "Marmara University CSE2062 Final Project");
                multipart.addBodyPart(attachment);
            } else orderText.setText(receipt);
            multipart.addBodyPart(orderText);
            message.setContent(multipart);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Receipt sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sending receipt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setOrderTableColours() {
        tblOrders.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                OrdersTableModel otm = (OrdersTableModel) table.getModel();
                Order curOrder = otm.getObject(row);
                if (!curOrder.isPaid()) {
                    c.setBackground(Color.GREEN);
                } else {
                    c.setBackground(Color.RED);
                }
                return c;
            }
        });
    }

    private void setPositionIcons() {
        String position = loggedEmployee.getPosition();
        if (position.equals("Assistant Chef")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/assistantchef.png")));
        else if (position.equals("Barista")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/barista.png")));
        else if (position.equals("Cashier")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/cashier.png")));
        else if (position.equals("Chef")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/chef.png")));
        else if (position.equals("Deputy Manager")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/deputy.png")));
        else if (position.equals("Head Chef")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/headchef.png")));
        else if (position.equals("Manager")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/manager.png")));
        else if (position.equals("Waiter")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/waiter.png")));
        else if (position.equals("Waitress")) iconPosition.setIcon(new ImageIcon(getClass().getResource("/images/waitress.png")));
    }
}
