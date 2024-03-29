package tools.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class TextArea extends JTextArea {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -972007918977127487L;

    private UndoManager undoManager;

    /* CONSTRUCTORS _________________________________________________________ */
    public TextArea(String text) {
        super(text);

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public TextArea() {
        this("");
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.isControlDown()) {
                    try {
                        if (ke.getKeyCode() == KeyEvent.VK_Z) {
                            undoManager.undo();
                        } else if (ke.getKeyCode() == KeyEvent.VK_Y) {
                            undoManager.redo();
                        }
                    } catch (CannotRedoException | CannotUndoException e) {
                    }
                }
            }
        });
    }
}
