package interfaces.themestatistics;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import tools.Tools;
import worldclasses.themes.Theme;

public class ThemeButton extends JButton {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -5958987373964342002L;

	private Theme theme;

	/* CONSTRUCTORS _________________________________________________________ */
	public ThemeButton(Theme theme) {
		this.theme = theme;

		this.initComponents();
		this.initEvents();
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		JLabel imageLabel;
		JLabel titleLabel;
		JLabel viewsLabel;

		// Set up Button -------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		imageLabel = new JLabel();

		titleLabel = new JLabel(this.theme.getTitle(), JLabel.CENTER);
		viewsLabel = new JLabel(this.theme.getViews() + " views", JLabel.CENTER);

		// ---------------------------------------------------------------------
		if (this.theme.getImage() != null) {
			imageLabel.setIcon(Tools.getAbsoluteImageIcon(this.theme.getImage(), 20, 20));
		}

		// ---------------------------------------------------------------------
		this.add(imageLabel, BorderLayout.WEST);
		this.add(titleLabel, BorderLayout.CENTER);
		this.add(viewsLabel, BorderLayout.EAST);
	}

	/* ______________________________________________________________________ */
	private void initEvents() {

	}

	/* GETTERS ______________________________________________________________ */
	public Theme getTheme() {
		return theme;
	}

	/* SETTERS ______________________________________________________________ */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
