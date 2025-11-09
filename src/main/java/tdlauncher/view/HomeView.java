package tdlauncher.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tdlauncher.Launcher;
import tdlauncher.view.Page;

public class HomeView extends Page {
    public HomeView(Launcher launcher) {
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(new Label("Bienvenue sur la page d'accueil !"));
    }
}