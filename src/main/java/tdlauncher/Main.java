package tdlauncher;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static tdlauncher.controller.LoginController.getSteamUser;
import static tdlauncher.util.Config.loadSteamId;
import static tdlauncher.util.Config.loadSteamLocation;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Launcher app = new Launcher(stage);
        if(loadSteamId() == null) {
            app.setView("login");
        }else{
            app.setCurrentUser(getSteamUser(loadSteamId()));
            if(loadSteamLocation() == null){
                app.setView("steamlocation");
            }else{
                app.setSteamLocation(loadSteamLocation());
                app.setView("home");
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}