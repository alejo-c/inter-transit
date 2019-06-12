package interfaces.registeraccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import utilities.Border;
import utilities.PasswordField;
import utilities.Utilities;

public class PasswordPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    protected static final ImageIcon SHOW_ICON = Utilities.getImageIcon("/images/show.png", 15, 15);
    protected static final ImageIcon HIDE_ICON = Utilities.getImageIcon("/images/hide.png", 15, 15);

    protected PasswordField passwordField;
    protected PasswordField confirmPasswordField;

    protected JToggleButton showPasswordButton;
    protected JLabel messageLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public PasswordPanel() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel passwordLabel;
        JLabel confirmPasswordLabel;

        JPanel westPanel;
        JPanel centerPanel;
        JPanel eastPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(new Border(new EmptyBorder(5, 5, 5, 5),
                new Border("Constraseña")));

        // Set up Components ---------------------------------------------------
        this.passwordField = new PasswordField();
        this.confirmPasswordField = new PasswordField();
        this.showPasswordButton = new JToggleButton(HIDE_ICON);
        this.messageLabel = new JLabel(" ", JLabel.RIGHT);

        passwordLabel = new JLabel("          Contraseña:", JLabel.RIGHT);
        confirmPasswordLabel = new JLabel("Confirmar:", JLabel.RIGHT);

        westPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        centerPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        eastPanel = new JPanel(new FlowLayout());

        // ---------------------------------------------------------------------
        this.showPasswordButton.setPreferredSize(new Dimension(24, 18));

        this.messageLabel.setForeground(Color.red);

        westPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 2));

        // ---------------------------------------------------------------------
        westPanel.add(passwordLabel);
        westPanel.add(confirmPasswordLabel);

        centerPanel.add(this.passwordField);
        centerPanel.add(this.confirmPasswordField);

        eastPanel.add(this.showPasswordButton);

        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(this.messageLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        char echoChar = this.passwordField.getEchoChar();

        this.showPasswordButton.addActionListener(ae -> {
            boolean selected = this.showPasswordButton.isSelected();
            this.showPasswordButton.setIcon(selected ? SHOW_ICON : HIDE_ICON);
            this.passwordField.setEchoChar(selected ? 0 : echoChar);
            this.confirmPasswordField.setEchoChar(selected ? 0 : echoChar);
        });

        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmPasswordField.requestFocus();
                }
            }
        });
    }

    /* GETTERS ______________________________________________________________ */
    public String getPassword() {
        return this.passwordField.getText();
    }

    /* ______________________________________________________________________ */
    public PasswordField getPasswordField() {
        return this.passwordField;
    }

    /* ______________________________________________________________________ */
    public PasswordField getConfirmPasswordField() {
        return this.confirmPasswordField;
    }

    /* ______________________________________________________________________ */
    public String getConfirmPassword() {
        return this.confirmPasswordField.getText();
    }

    /* SETTERS ______________________________________________________________ */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
}