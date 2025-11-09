package tdlauncher.view;

import javafx.scene.layout.VBox;

public abstract class Page {
    protected final VBox root = new VBox(20);

    public VBox getRoot(){
        return root;
    }
}
