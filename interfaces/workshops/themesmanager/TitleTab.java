package interfaces.workshops.createtheme;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class TitleTab extends JComponent {

	/* ATTRIBUTTES __________________________________________________________ */
	private static final long serialVersionUID = -8413540237895211978L;
	
    private JLabel textLabel;
    private JButton closeButton;
//    private JPopupMenu popupMenu;

//    private MenuItem shiftRightMenuItem;
//    private MenuItem shiftLeftMenuItem;
//    private MenuItem closeTabMenuItem;
//    private MenuItem closeAllTabsMenuItem;
//    private MenuItem closeOtherTabsMenuItem;
//    private MenuItem cloneTabMenuItem;
    /* CONSTRUCTORS _________________________________________________________ */
    public TitleTab(String text) {
        initComponents();
        initEvents();

        textLabel.setText(text);
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set Up Panel
        setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));

        // Set Up Components
        textLabel = new JLabel("");
        closeButton = new JButton("X");

        closeButton.setBorder(null);
        closeButton.setContentAreaFilled(false);
        add(textLabel);
        add(closeButton);

        // Set Up Menu Items
//        shiftLeftMenuItem = new MenuItem("Shift Left", 'L', KeyEvent.VK_LEFT);
//        shiftRightMenuItem = new MenuItem("Shift Right", 'R', KeyEvent.VK_RIGHT);
//        closeTabMenuItem = new MenuItem("Close Tab", 'C', 'W', "/images/closered.png");
//        closeAllTabsMenuItem = new MenuItem("Close All Tabs", 'A', 'W', ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
//        closeOtherTabsMenuItem = new MenuItem("Close Other Tabs", 'O');
//        cloneTabMenuItem = new MenuItem("Clone Tab", 'T');
        // Set Up Popup Menu
//        popupMenu = new JPopupMenu();
//        popupMenu.add(shiftLeftMenuItem);
//        popupMenu.add(shiftRightMenuItem);
//        popupMenu.addSeparator();
//        popupMenu.add(closeTabMenuItem);
//        popupMenu.add(closeAllTabsMenuItem);
//        popupMenu.add(closeOtherTabsMenuItem);
//        popupMenu.addSeparator();
//        popupMenu.add(cloneTabMenuItem);
//        setComponentPopupMenu(popupMenu);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                closeButton.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                closeButton.setForeground(Color.black);
            }
        });
    }

    /* ______________________________________________________________________ */
    public void addCloseAction(ActionListener actionListener) {
        closeButton.addActionListener(actionListener);
    }

    /* GETTERS ______________________________________________________________ */
    public String getText() {
        return textLabel.getText();
    }

    /* ______________________________________________________________________ */
//    public MenuItem getShiftRightMenuItem() {
//        return shiftRightMenuItem;
//    }

    /* ______________________________________________________________________ */
//    public MenuItem getShiftLeftMenuItem() {
//        return shiftLeftMenuItem;
//    }

    /* ______________________________________________________________________ */
//    public MenuItem getCloseTabMenuItem() {
//        return closeTabMenuItem;
//    }

    /* ______________________________________________________________________ */
//    public MenuItem getCloseAllTabsMenuItem() {
//        return closeAllTabsMenuItem;
//    }

    /* ______________________________________________________________________ */
//    public MenuItem getCloseOtherTabsMenuItem() {
//        return closeOtherTabsMenuItem;
//    }

    /* ______________________________________________________________________ */
//    public MenuItem getCloneTabMenuItem() {
//        return cloneTabMenuItem;
//    }

    /* SETTERS ______________________________________________________________ */
    public void setText(String text) {
        textLabel.setText(text);
    }

    /* ______________________________________________________________________ */
    public void setSaved(boolean saved) {
        textLabel.setFont(new Font("Dialog", saved ? Font.PLAIN : Font.BOLD, 11));
    }
}
