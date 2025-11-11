package tdlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tdlauncher.view.*;
import tdlauncher.model.User;
import tdlauncher.util.Config;

import static tdlauncher.util.Config.loadSteamId;

public class Launcher {
    private final Stage stage;
    private final BorderPane root = new BorderPane();
    private User currentUser;
    private String steamLocation;

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

        // Charger la configuration sauvegardée (SteamID et emplacement)
        try {
            Long savedSteamId = loadSteamId();
            String savedSteamLocation = Config.loadSteamLocation();
            if (savedSteamId != null) {
                this.currentUser = new User();
                this.currentUser.setSteamId(savedSteamId);
                System.out.println("[Launcher] Loaded saved SteamID: " + savedSteamId);
            }
            if (savedSteamLocation != null && !savedSteamLocation.isEmpty()) {
                this.steamLocation = savedSteamLocation;
                System.out.println("[Launcher] Loaded saved Steam location: " + savedSteamLocation);
            }
        } catch (Exception e) {
            System.err.println("[Launcher] Failed to load config: " + e.getMessage());
        }

        // Vue initiale : si on a déjà SteamID et emplacement -> home, sinon login
        if (this.currentUser != null && this.steamLocation != null && !this.steamLocation.isEmpty()) {
            setView("home");
        } else {
            setView("login");
        }

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
            case "steamlocation":
                root.setCenter(new SteamLocationView(this).getRoot());
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
        if (user == null) {
            Config.saveSteamId(null);
        } else {
            try {
                Config.saveSteamId(user.getSteamId());
            } catch (Exception e) {
                System.err.println("[Launcher] Failed to save SteamID: " + e.getMessage());
            }
        }
    }

    public String getSteamLocation() {
        return steamLocation;
    }

    public void setSteamLocation(String steamLocation) {
        this.steamLocation = steamLocation;
        System.out.println("[Launcher] Steam location set: " + steamLocation);
        try {
            Config.saveSteamLocation(steamLocation);
        } catch (Exception e) {
            System.err.println("[Launcher] Failed to save steam location: " + e.getMessage());
        }
    }
}
