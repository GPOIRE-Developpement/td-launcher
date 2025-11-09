package tdlauncher;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tdlauncher.view.*;

public class Launcher {
    private final Stage stage;
    private final BorderPane root = new BorderPane();

    public Launcher(Stage stage){
        this.stage = stage;
        root.setTop(new HeaderView(this).getRoot());
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("TD Launcher");
        stage.show();
    }

    public void setView(String name){
        switch(name.toLowerCase()){
            case "home" -> root.setCenter(new HomeView(this).getRoot());
            case "settings" -> root.setCenter(new SettingsView(this).getRoot());
            default -> throw new IllegalArgumentException("Vue inconnue: " + name);
        }
    }
}
