import com.google.zxing.common.BitMatrix;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class LoginForm extends JFrame{
    private String authPass = "";
    private JPanel mainPanel;
    private JPanel navPane;
    private JPanel mainPane;
    private JPanel welcomePane;
    private JPanel navSignPane;
    private JPanel navRegisterPane;
    private JPanel loginPane;
    private JPanel registerPane;
    private JSplitPane splitLoginPane;
    private JTextField tfSignMail;
    private JComboBox comboBoxSign;
    private JPasswordField passwordField1;
    private JButton signInButton;
    private JButton forgotPasswordButton;
    private JTextField tfRegisterMail;
    private JTextField tfRegisterName;
    private JTextField tfRegisterSurname;
    private JTextField tfRegisterPhone;
    private JPasswordField pfRegisterPassword;
    private JPasswordField pfRegisterConPassword;
    private JButton registerButton;
    private JComboBox comboBoxRegisterMail;
    private JComboBox comboBoxPosition;
    private JLabel lbNavSign;
    private JLabel lbNavRegister;

    private Color sideBarColorHover = new Color(96, 96, 96);
    private Color sideBarColorNormal = new Color(72, 72, 72);
    //private Color sideBarBottomLine = new Color(157, 217, 210);
  //  private Color backgroundMain = new Color(255, 248, 240);
  private Color backgroundMain = new Color(51, 51, 51);
    private Color sideBarBottomLine = new Color(254, 88, 0);

    private CardLayout c1 = (CardLayout) mainPane.getLayout();

    private static DatabaseManager manager;

    public LoginForm() {
        navPane.setBackground(backgroundMain);
        navRegisterPane.setBackground(backgroundMain);
        navSignPane.setBackground(backgroundMain);
        welcomePane.setBackground(backgroundMain);
        loginPane.setBackground(backgroundMain);
        registerPane.setBackground(backgroundMain);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        String m1 = "gmail.com";
        String m2 = "hotmail.com";
        String m3 = "outlook.com";

        comboBoxSign.addItem(m1);
        comboBoxSign.addItem(m2);
        comboBoxSign.addItem(m3);
        comboBoxRegisterMail.addItem(m1);
        comboBoxRegisterMail.addItem(m2);
        comboBoxRegisterMail.addItem(m3);
        comboBoxPosition.addItem("Cashier");
        comboBoxPosition.addItem("Manager");
        comboBoxPosition.addItem("Deputy Manager");
        comboBoxPosition.addItem("Waiter");
        comboBoxPosition.addItem("Waitress");
        comboBoxPosition.addItem("Head Chef");
        comboBoxPosition.addItem("Chef");
        comboBoxPosition.addItem("Assistant Chef");
        comboBoxPosition.addItem("Barista");
        setContentPane(mainPanel);
        setSize(1200,700);
        setTitle("My Cafe");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(700, 400));
        setLocationRelativeTo(null);
        splitLoginPane.setDividerSize(2);

        navSignPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                navSignPane.setBorder(BorderFactory.createMatteBorder(0,0,5,0,sideBarBottomLine));
                lbNavSign.setFont(new Font("Calibre",Font.ITALIC | Font.BOLD,16));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                navSignPane.setBorder(BorderFactory.createMatteBorder(0,0,0,0,sideBarBottomLine));
                lbNavSign.setFont(new Font("Calibre",Font.PLAIN | Font.BOLD,16));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(mainPane,"Card2");
            }
        });

        navRegisterPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                navRegisterPane.setBorder(BorderFactory.createMatteBorder(0,0,5,0,sideBarBottomLine));
                lbNavRegister.setFont(new Font("Calibre",Font.ITALIC | Font.BOLD,16));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                navRegisterPane.setBorder(BorderFactory.createMatteBorder(0,0,0,0,sideBarBottomLine));
                lbNavRegister.setFont(new Font("Calibre",Font.PLAIN | Font.BOLD,16));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(mainPane,"Card3");
            }
        });


        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfSignMail.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your mail!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ArrayList<Employee> employees = manager.getEmployees();
                    for (Employee i: employees) {
                        String mail = i.getMail();
                        String tempMail = tfSignMail.getText() + "@" + comboBoxSign.getSelectedItem();
                        if (mail.equals(tempMail)) {
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
                            //session.setDebug(true); //Used for debugging SMTP and 2-AUTH connections
                            try {
                                MimeMessage message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(from));
                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                                message.setSubject("Password Recovery");
                                message.setText("Your password is: " + i.getPassword());
                                Transport.send(message);
                                JOptionPane.showMessageDialog(null, "Your password has been sent to your mail!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (MessagingException exception) {
                                exception.printStackTrace();
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Mail not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfRegisterMail.getText().equals("") || comboBoxPosition.getSelectedItem().toString().equals("") || tfRegisterPhone.getText().equals("") ||tfRegisterName.getText().equals("") || tfRegisterSurname.getText().equals("") || tfRegisterMail.getText().equals("") || pfRegisterPassword.getText().equals("") || pfRegisterPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!pfRegisterPassword.getText().equals(pfRegisterPassword.getText())) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ArrayList<Employee> employees = manager.getEmployees();
                    for (Employee i: employees) {
                        String mail = i.getMail();
                        String tempMail = tfRegisterMail.getText() + "@" + comboBoxRegisterMail.getSelectedItem();
                        if (mail.equals(tempMail)) {
                            JOptionPane.showMessageDialog(null, "Mail already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    Date currentDate = new Date();
                    long id = currentDate.getTime();
                    Employee employee = new Employee(id, tfRegisterName.getText(), tfRegisterSurname.getText(),
                            tfRegisterMail.getText() + "@" + comboBoxRegisterMail.getSelectedItem(), tfRegisterPhone.getText(),
                            pfRegisterPassword.getText(), comboBoxPosition.getSelectedItem().toString(), true);
                    boolean isDone = manager.addEmployee(employee);
                    if (isDone) {
                        JOptionPane.showMessageDialog(null, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        tfRegisterMail.setText("");
                        tfRegisterName.setText("");
                        tfRegisterSurname.setText("");
                        tfRegisterPhone.setText("");
                        pfRegisterPassword.setText("");
                        pfRegisterConPassword.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee not added!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm();
        manager = new DatabaseManager();
    }
    private void login() {
        ArrayList<Employee> employees = manager.getEmployees();
        for (Employee i: employees) {
            String mail = i.getMail();
            String tempMail = tfSignMail.getText() + "@" + comboBoxSign.getSelectedItem();
            String password = i.getPassword();
            String tempPass = new String(passwordField1.getPassword());
            if (mail.equals(tempMail) && password.equals(tempPass)) {
                LoginForm.this.dispose();
                new MainForm(i);
                System.out.println("Employee (" + i.getName() + " " + i.getSurname() + ") logged in!");
                return;
            } else if (mail.equals(tempMail) && !password.equals(tempPass)) {
                JOptionPane.showMessageDialog(null, "Check your password please!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Wrong mail or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
