package tdlauncher.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import tdlauncher.Launcher;

public class HeaderView {
    private final HBox root = new HBox(10);

    public HeaderView(Launcher launcher) {

    }

    public HBox getRoot() {
        return root;
    }
}