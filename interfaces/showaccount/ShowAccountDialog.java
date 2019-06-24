package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.binaryfilemanager.BinaryFileManager;
import tools.components.Dialog;
import tools.components.DialogPane;

import worldclasses.accounts.Account;

public abstract class ShowAccountDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    protected JLabel imageLabel;
    protected AccountPanel accountPanel;

    protected JPanel buttonsPanel;

    protected JButton backButton;
    protected JButton removeButton;
    protected JButton signoutButton;
    protected JButton editButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowAccountDialog(Account account) {
        super();
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    protected void initComponents() {

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(535, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));

        this.backButton = new JButton("Volver");
        this.removeButton = new JButton("Eliminar");
        this.signoutButton = new JButton("Cerrar sesion");
        this.editButton = new JButton("Editar");

        this.buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    }

    /* ______________________________________________________________________ */
    protected void initEvents() {
        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });

        this.removeButton.addActionListener(ae -> {
            this.removeAction();
        });

        this.signoutButton.addActionListener(ae -> {
            int option = DialogPane.yesNoOption("Cerrar Sesion?");
            if (option == DialogPane.YES_OPTION) {
                this.dispose();
                this.okAction();
            }
        });

        this.editButton.addActionListener(ae -> {
            this.editAction();
        });
    }

    /* ______________________________________________________________________ */
    public void removeAction() {
        int option = DialogPane.yesNoOption("Eliminar cuenta?");

        if (option == DialogPane.YES_OPTION) {
            this.dispose();
            this.okAction();
            removeAccount(this.getAccount());
        }
    }

    /* ______________________________________________________________________ */
    public abstract void editAction();

    /* ______________________________________________________________________ */
    public void removeAccount(Account account) {
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
        ArrayList<Object> objects = manager.read();
        manager.clear();

        for (Object object : objects) {
            Account _account = (Account) object;
            if (!_account.getNickname().equals(account.getNickname())) {
                manager.add(_account);
            }
        }
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }
}
