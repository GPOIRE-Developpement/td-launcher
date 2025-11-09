package tdlauncher.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import tdlauncher.Launcher;

public class HeaderView {
    private final HBox root = new HBox(10);

    public HeaderView(Launcher launcher) {
        Button homeBtn = new Button("Accueil");
        Button settingsBtn = new Button("Paramètres");

        homeBtn.setOnAction(e -> launcher.setView("home"));
        settingsBtn.setOnAction(e -> launcher.setView("settings"));

        root.getChildren().addAll(homeBtn, settingsBtn);
        root.setPadding(new Insets(10));
    }

    public HBox getRoot() {
        return root;
    }
}