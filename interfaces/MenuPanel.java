package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Tools;
import tools.components.Border;
import tools.components.Panel;

import worldclasses.Settings;
import worldclasses.accounts.Account;

public class MenuPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -9007803544585262467L;

    private Account account;

    private JLabel logoLabel;
    private JButton settingsButton;
    private JButton aboutButton;

    private JButton accountButton;
    private JButton workshopsButton;
    private JButton testButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public MenuPanel(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String theme;
        String logo = null;

        JPanel northPanel;
        JPanel eastPanel;

        theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            logo = Settings.DARK_LOGO;
        }

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.settingsButton = new JButton();
        this.aboutButton = new JButton();

        this.accountButton = new JButton();
        this.workshopsButton = new JButton();
        this.testButton = new JButton();

        this.logoLabel = new JLabel(Tools.getImageIcon(logo), JLabel.CENTER);
        northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        eastPanel = new JPanel(new GridLayout(4, 1));

        // ---------------------------------------------------------------------
        this.settingsButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.settingsButton.setIcon(Tools.getImageIcon("settings", 30, 30));

        this.aboutButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.aboutButton.setIcon(Tools.getImageIcon("about", 30, 30));

        this.accountButton.setBorder(new Border(new EtchedBorder(), new EmptyBorder(5, 5, 5, 5)));

        if (this.getAccount() != null) {
            this.accountButton.setIcon(Tools.getImageIcon(this.getAccount().getImage(), 80, 80));
        } else {
            this.accountButton.setIcon(Tools.getImageIcon("profile/image-00", 80, 80));
        }

        this.workshopsButton.setBorder(new Border(new EtchedBorder(), new EmptyBorder(5, 5, 5, 5)));
        this.workshopsButton.setIcon(Tools.getImageIcon("learn", 70, 70));

        this.testButton.setBorder(new Border(new EtchedBorder(), new EmptyBorder(5, 5, 5, 5)));
        this.testButton.setIcon(Tools.getImageIcon("test", 70, 70));

        northPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        eastPanel.setBorder(new EtchedBorder());

        // ---------------------------------------------------------------------
        northPanel.add(new JLabel());
        northPanel.add(this.settingsButton);
        northPanel.add(this.aboutButton);

        eastPanel.add(northPanel);
        eastPanel.add(this.accountButton);
        eastPanel.add(this.workshopsButton);
        eastPanel.add(this.testButton);

        this.add(this.logoLabel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.aboutButton.addActionListener(ae -> {
            new AboutDialog().showDialog();
        });
    }

    /* ______________________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return null;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return account;
    }

    /* ______________________________________________________________________ */
    public void setLogo(int width) {
        String logo = null;
        String theme = Settings.getCurrentSettings().getTheme();

        if (theme.equals(Settings.LIGHT_THEME)) {
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            logo = Settings.DARK_LOGO;
        }

        this.logoLabel.setIcon(Tools.getImageIcon(logo, width, width));
    }

    /* ______________________________________________________________________ */
    public JButton getSettingsButton() {
        return this.settingsButton;
    }

    /* ______________________________________________________________________ */
    public JButton getAccountButton() {
        return this.accountButton;
    }

    /* ______________________________________________________________________ */
    public JButton getWorkshopsButton() {
        return this.workshopsButton;
    }

    /* ______________________________________________________________________ */
    public JButton getTestButton() {
        return this.testButton;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }
}
