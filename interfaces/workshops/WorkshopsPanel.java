package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import tools.Tools;

import tools.components.Panel;

import worldclasses.Settings;
import worldclasses.themes.Tip;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;
import worldclasses.themes.Theme;

public class WorkshopsPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -2890938347215601719L;

    private Account account;
    private ArrayList<Theme> themes;

    private JTree themesTree;
    private JPanel centerPanel = new JPanel();

    private JButton backButton;
    private JButton createButton;
    private JButton removeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsPanel(Account account) {
        this.account = account;
        this.themes = new ArrayList<>();

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        AccountPanel accountPanel;
        JScrollPane treeScrollPane;

        JPanel westPanel;
        JPanel southPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.themesTree = new JTree(this.initTree());
        this.centerPanel = new JPanel();

        this.backButton = new JButton("Volver");
        this.createButton = new JButton("Nuevo");
        this.removeButton = new JButton("Eliminar");

        accountPanel = new AccountPanel(this.getAccount());
        treeScrollPane = new JScrollPane(this.themesTree);

        westPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.themesTree.setCellRenderer(new DefaultTreeCellRenderer() {
            private static final long serialVersionUID = 5756325787925811601L;

            @Override
            public Component getTreeCellRendererComponent(JTree t, Object v, boolean s, boolean e, boolean l, int r, boolean h) {
                super.getTreeCellRendererComponent(t, v, s, e, l, r, h);

                Settings settings = Settings.getCurrentSettings();
                setFont(settings.getFont());

                String theme = settings.getTheme();
                if (theme.equals(Settings.LIGHT_THEME)) {
                    setForeground(Color.black);
                } else if (theme.equals(Settings.DARK_THEME)) {
                    setForeground(Color.white);
                }
                return this;
            }
        });

        // ---------------------------------------------------------------------
        westPanel.add(accountPanel, BorderLayout.NORTH);
        westPanel.add(treeScrollPane, BorderLayout.CENTER);
        westPanel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.EAST);

        southPanel.add(this.backButton);

        this.add(westPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.themesTree.addTreeSelectionListener((TreeSelectionEvent tse) -> {

            Object[] path = tse.getNewLeadSelectionPath().getPath();
            System.out.println(path);

            Theme theme = this.searchTheme(path[path.length - 2] + "");
            Theme tip = this.searchTheme(path[path.length - 1] + "");

            System.out.println("Theme=" + theme);
            System.out.println("Tip=" + tip);

            if (theme != null && tip != null) {
                if (tip instanceof Tip) {

                    theme = this.searchTheme(path[path.length - 2] + "");
                    this.remove(this.centerPanel);

                    if (this.getAccount() instanceof AdminAccount) {
                        this.centerPanel = new TipAdminPanel(theme, (Tip) tip);
                    } else {
                        this.centerPanel = new TipUserPanel(theme, (Tip) tip);
                    }

                    this.add(this.centerPanel, BorderLayout.CENTER);
                    this.centerPanel.updateUI();
                    this.updateUI();
                }
            }
        });

        this.createButton.addActionListener(ae -> {
        });

        this.removeButton.addActionListener(ae -> {
        });
    }

    /* ______________________________________________________________________ */
    private DefaultTreeModel initTree() {
        DefaultMutableTreeNode root;
        DefaultTreeModel defaultTreeModel;

        String themesDirectoryPath;
        File themesDirectory;

        // ---------------------------------------------------------------------
        themesDirectoryPath = Settings.getResource() + "src/files";

        themesDirectory = new File(themesDirectoryPath);

        // ---------------------------------------------------------------------
        root = new DefaultMutableTreeNode("Temas");
        defaultTreeModel = new DefaultTreeModel(root);

        // ---------------------------------------------------------------------
        if (themesDirectory.exists()) {
            for (File listFile : themesDirectory.listFiles()) {
                root.add(this.initFiles(listFile.getAbsolutePath()));
            }
        }

        this.setThemes(this.initTheme(themesDirectoryPath).getFiles());

        return defaultTreeModel;
    }

    /* ______________________________________________________________________ */
    private DefaultMutableTreeNode initFiles(String path) {
        File file = new File(path);
        DefaultMutableTreeNode defaultMutableTreeNode;
        defaultMutableTreeNode = new DefaultMutableTreeNode(file.getName());

        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                defaultMutableTreeNode.add(this.initFiles(
                        listFile.getAbsolutePath()
                ));
            }
        }
        return defaultMutableTreeNode;
    }

    /* ______________________________________________________________________ */
    private Theme initTheme(String path) {
        File file = new File(path);
        Object[] themeData;
        Theme theme;

        try {
            themeData = this.getThemeData(path + "/descripcion.txt");
            theme = new Theme(
                    null,
                    file.getName(),
                    themeData[0] + "",
                    Double.parseDouble(themeData[1] + ""),
                    Integer.parseInt(themeData[2] + ""),
                    new ArrayList<>()
            );
        } catch (FileNotFoundException | NumberFormatException e) {
            if (file.isDirectory()) {
                theme = new Theme(file.getName(), "");
            } else {
                theme = new Tip(file.getName(), Tools.getFileText(file));
            }
        }

        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                if (!listFile.getName().equals("descripcion.txt")) {
                    theme.getFiles().add(this.initTheme(
                            listFile.getAbsolutePath()
                    ));
                }
            }
        }
        return theme;
    }

    /* ______________________________________________________________________ */
    private Object[] getThemeData(String themeDirectoryPath) throws FileNotFoundException {
        File descriptionFile = new File(themeDirectoryPath);

        if (descriptionFile.exists()) {
            String text = Tools.getFileText(descriptionFile);

            int start = text.indexOf('=') + 1;
            int end = text.indexOf('\n');
            String description = text.substring(start, end);

            start = text.indexOf('=', start) + 1;
            end = text.indexOf('\n', end + 1);
            String value = text.substring(start, end);

            start = text.indexOf('=', start) + 1;
            end = text.indexOf('\n', end + 1);
            String views = text.substring(start, end);

            return new Object[]{
                description,
                Double.parseDouble(value),
                Integer.parseInt(views)
            };
        } else {
            System.out.println("description file do not exists");
            throw new FileNotFoundException("description file do no exists");
        }
    }

    /* ______________________________________________________________________ */
    private Theme searchTheme(String nameTheme) {
        Theme theme = new Theme("Temas", "");
        theme.setFiles(this.getThemes());
        return this.searchTheme(theme, nameTheme);
    }

    private Theme searchTheme(Theme theme, String nameTheme) {
        Theme _theme;

        for (Theme subTheme : theme.getFiles()) {
            if (subTheme.getTitle().equals(nameTheme)) {
                return subTheme;

            } else {
                _theme = this.searchTheme(subTheme, nameTheme);
                if (_theme != null) {
                    return _theme;
                }
            }
        }
        return null;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    /* ______________________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /*  MAIN ________________________________________________________________ */
    public static void main(String[] args) {
        new WorkshopsPanel(new UserAccount(
                "Alejandro",
                "413J0c",
                "passwd",
                "profile/image-31"
        )).showTestDialog();
    }
}
