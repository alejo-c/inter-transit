package tools.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import worldclasses.Settings;

public class Border extends CompoundBorder {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 5537357247919596735L;

    /* CONSTRUCTORS _________________________________________________________ */
    public Border(AbstractBorder outsideBorder, AbstractBorder insideBorder) {
        super(outsideBorder, insideBorder);
    }

    public Border(AbstractBorder outsideBorder, AbstractBorder centerBorder, AbstractBorder insideBorder) {
        super(outsideBorder, new Border(centerBorder, insideBorder));
    }

    /* ______________________________________________________________________ */
    public Border(int top, int left, int bottom, int right) {
        this(new EmptyBorder(top, left, bottom, right), new EtchedBorder());
    }

    /* ______________________________________________________________________ */
    public Border(String string) {
        String theme;
        Color color = null;

        this.insideBorder = new TitledBorder(string);
        ((TitledBorder) this.insideBorder).setTitleFont(new Font("Dialog", Font.PLAIN, 12));
        ((TitledBorder) this.insideBorder).setBorder(new EtchedBorder());

        theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            color = Color.black;
        } else if (theme.equals(Settings.DARK_THEME)) {
            color = Color.white;
        }

        ((TitledBorder) this.insideBorder).setTitleColor(color);
    }

    /* ______________________________________________________________________ */
    public Border() {
        this(5, 5, 5, 5);
    }
}
