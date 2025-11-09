package tdlauncher;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Launcher app = new Launcher(stage);
        app.setView("login");
    }

    public static void main(String[] args) {
        launch();
    }
}