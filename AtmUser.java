import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtmUser {
    private static int pin = 2024;
    private static int balance = 5000;
    private static int addAmount = 0;
    private static int takeAmount = 0;
    private static String fddfname;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("ATM System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(3, 1));
        JLabel welcomeLabel = new JLabel("Enter Your Pin Number:", SwingConstants.CENTER);
        JTextField pinField = new JPasswordField();
        JButton pinSubmitButton = new JButton("Submit");
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(pinField);
        welcomePanel.add(pinSubmitButton);

        // Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1));
        JLabel menuLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit Money");
        JButton withdrawButton = new JButton("Withdraw Money");
        JButton receiptButton = new JButton("Print Receipt");
        JButton exitButton = new JButton("Exit");
        menuPanel.add(menuLabel);
        menuPanel.add(checkBalanceButton);
        menuPanel.add(depositButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(receiptButton);
        menuPanel.add(exitButton);

        // Add panels to frame
        frame.add(welcomePanel, "Welcome");
        frame.add(menuPanel, "Menu");

        // Card Layout manager
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();

        // Pin Submit Action
        pinSubmitButton.addActionListener(e -> {
            int enteredPin;
            try {
                enteredPin = Integer.parseInt(pinField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid PIN format. Please enter numbers only.");
                return;
            }

            if (enteredPin == pin) {
                name = JOptionPane.showInputDialog(frame, "Enter Your Name:");
                menuLabel.setText("Welcome, " + name + "!");
                cardLayout.show(frame.getContentPane(), "Menu");
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong PIN Number! Access Denied.");
            }
        });

        // Menu Actions
        checkBalanceButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Your Current Balance is: " + balance));

        depositButton.addActionListener(e -> {
            String amount = JOptionPane.showInputDialog(frame, "Enter the amount to deposit:");
            try {
                addAmount = Integer.parseInt(amount);
                balance += addAmount;
                JOptionPane.showMessageDialog(frame, "Successfully Deposited: " + addAmount +
                        "\nCurrent Balance: " + balance);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter numbers only.");
            }
        });

        withdrawButton.addActionListener(e -> {
            String amount = JOptionPane.showInputDialog(frame, "Enter the amount to withdraw:");
            try {
                takeAmount = Integer.parseInt(amount);
                if (takeAmount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient Balance! Try a smaller amount.");
                } else {
                    balance -= takeAmount;
                    JOptionPane.showMessageDialog(frame, "Successfully Withdrawn: " + takeAmount +
                            "\nCurrent Balance: " + balance);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter numbers only.");
            }
        });

        receiptButton.addActionListener(e -> {
            String receipt = "----- Receipt -----\n" +
                    "Name: " + name + "\n" +
                    "Balance: " + balance + "\n" +
                    "Amount Deposited: " + addAmount + "\n" +
                    "Amount Withdrawn: " + takeAmount + "\n" +
                    "--------------------";
            JOptionPane.showMessageDialog(frame, receipt);
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using our ATM! Goodbye.");
            frame.dispose();
        });

        // Show the frame
        frame.setVisible(true);
    }
}
