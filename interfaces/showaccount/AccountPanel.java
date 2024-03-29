package interfaces.showaccount;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import tools.components.TextField;

import worldclasses.accounts.Account;

public abstract class AccountPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 5300459726918405095L;

    protected Account account;

    protected TextField usernameField;
    protected TextField nicknameField;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountPanel(Account account) {
        this.account = account;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    protected void initComponents() {
        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.usernameField = new TextField(this.getAccount().getUsername());
        this.nicknameField = new TextField(this.getAccount().getNickname());

        // ---------------------------------------------------------------------
        this.usernameField.setEditable(false);
        this.nicknameField.setEditable(false);
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setUsername(String username) {
        this.getAccount().setUsername(username);
        this.usernameField.setText(username);
    }

    /* ______________________________________________________________________ */
    public void setNickname(String nickname) {
        this.getAccount().setNickname(nickname);
        this.nicknameField.setText(nickname);
    }
}
