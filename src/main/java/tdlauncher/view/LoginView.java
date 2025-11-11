package tdlauncher.view;

import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tdlauncher.Launcher;
import tdlauncher.controller.LoginController;
import tdlauncher.util.Config;

import java.io.InputStream;

public class LoginView extends Page {
    public LoginView(Launcher launcher) {
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("page");

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        HBox vb = new HBox();
        vb.setPrefWidth(430);
        vb.setAlignment(Pos.CENTER);

        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER_LEFT);
        vb2.setPrefWidth(300);
        vb2.setSpacing(10);

        Text title = new Text("Treakdown");
        title.setTextAlignment(TextAlignment.LEFT);
        title.getStyleClass().add("title");

        Text description = new Text(
                "Bienvenue sur le launcher de Treakdown. Il est votre seul moyen de venir vous éclater avec nos sur les meilleurs serveurs Gmod actuels.");
        description.getStyleClass().add("login-description");
        description.setWrappingWidth(300);

        String steamIconPath = "/tdlauncher/images/icons/steam_icon.png";
        InputStream is = getClass().getResourceAsStream(steamIconPath);
        ImageView steamIcon = null;
        if (is == null) {
            System.err.println(
                    "⚠️ Image introuvable : " + steamIconPath + " — vérifie l'emplacement et le nom du fichier.");
        } else {
            Image img = new Image(is);
            steamIcon = new ImageView(img);
            steamIcon.setFitWidth(30);
            steamIcon.setFitHeight(30);
            steamIcon.setPreserveRatio(true);
        }

        Button steamButton = (steamIcon != null)
                ? new Button("Se connecter avec Steam", steamIcon)
                : new Button("Se connecter avec Steam");
        steamButton.getStyleClass().add("steam-button");
        steamButton.setContentDisplay(ContentDisplay.LEFT);
        steamButton.setGraphicTextGap(8);
        steamButton.setPrefSize(260, 50);

        LoginController controller = new LoginController(launcher);

        steamButton.setOnAction(ev -> {
            steamButton.setDisable(true);
            controller.loginWithSteam(steamId -> {
                Platform.runLater(() -> {
                    System.out.println("[LoginView] SteamID set: " + steamId);

                    if(Config.loadSteamLocation() == null){
                        launcher.setView("steamlocation");
                    }else{
                        launcher.setView("home");
                    }
                });
            }, err -> {
                Platform.runLater(() -> {
                    System.err.println("[LoginView] Steam login failed: " + err);
                    steamButton.setDisable(false);
                });
            });
        });

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        HBox buttonBox = new HBox(steamButton);
        buttonBox.setAlignment(Pos.CENTER);

        vb2.getChildren().addAll(title, description, spacer, buttonBox);

        vb.getChildren().add(vb2);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        Image bgImage = new Image(getClass().getResourceAsStream("/tdlauncher/images/backgrounds/login.png"));
        iv.setImage(bgImage);

        double gradientWidth = 100;
        double imgHeight = bgImage.getHeight() > 0 ? bgImage.getHeight() : 400; // fallback height
        Rectangle gradientOverlay = new Rectangle(gradientWidth, imgHeight);
        LinearGradient lg = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#151724", 1.0)),
                new Stop(1.0, Color.web("#151724", 0.0)));
        gradientOverlay.setFill(lg);
        gradientOverlay.setMouseTransparent(true);

        StackPane imagePane = new StackPane(iv, gradientOverlay);
        StackPane.setAlignment(gradientOverlay, Pos.CENTER_LEFT);

        hb.getChildren().addAll(vb, imagePane);

        root.getChildren().add(hb);
    }
}
