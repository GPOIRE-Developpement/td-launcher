package tdlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tdlauncher.view.*;
import tdlauncher.model.User;

public class Launcher {
    private final Stage stage;
    private final BorderPane root = new BorderPane();
    private User currentUser;

    public Launcher(Stage stage) {
        this.stage = stage;

        try {
            Font.loadFont(getClass().getResourceAsStream("/tdlauncher/fonts/MundialRegular.otf"), 12);
            Font.loadFont(getClass().getResourceAsStream("/tdlauncher/fonts/MundialDemibold.otf"), 12);
            Font.loadFont(getClass().getResourceAsStream("/tdlauncher/fonts/MundialBold.otf"), 12);
            System.out.println("Polices chargées avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des polices: " + e.getMessage());
        }

        root.getStylesheets().add(getClass().getResource("/tdlauncher/styles/style.css").toExternalForm());
        root.getStyleClass().add("root");

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("TD Launcher");
        stage.show();
    }

    public void setView(String name) {
        this.clearRoot();
        switch (name.toLowerCase()) {
            case "home":
                root.setTop(new HeaderView(this).getRoot());
                root.setCenter(new HomeView(this).getRoot());
                break;
            case "login":
                root.setCenter(new LoginView(this).getRoot());
                break;
            default: {
                throw new IllegalArgumentException("Vue inconnue: " + name);
            }
        }
    }

    public void clearRoot() {
        root.setTop(null);
        root.setLeft(null);
        root.setCenter(null);
        root.setRight(null);
        root.setBottom(null);
    }

    public void minimizeStage() {
        if (stage != null)
            stage.setIconified(true);
    }

    public void closeStage() {
        if (stage != null)
            stage.close();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
