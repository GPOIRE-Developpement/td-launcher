package tdlauncher.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tdlauncher.Launcher;
import tdlauncher.view.Page;


public class SettingsView extends Page {
    public SettingsView(Launcher launcher) {
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(new Label("Paramètres de l'application"));
    }

}
