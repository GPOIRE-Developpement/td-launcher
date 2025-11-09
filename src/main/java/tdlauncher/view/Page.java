package tdlauncher.view;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public abstract class Page {
    protected final VBox root = new VBox(20);

    public VBox getRoot() {
        return root;
    }
}
