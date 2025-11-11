package tdlauncher.view;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import tdlauncher.Launcher;

import java.io.InputStream;

public class SteamLocationView extends Page {
    public SteamLocationView(Launcher launcher) {
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
                "Maintenant que tu es connecté, afin de fonctionner correctement, nous devons savoir où est installé votre jeu.\n"
                        + "Met l’emplacement de \n" + "/steamapps/");
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

        Label steamLocationLabel = new Label("Aucun dossier sélectionné");
        steamLocationLabel.setPrefWidth(300);
        steamLocationLabel.setPrefHeight(50);
        steamLocationLabel.setAlignment(Pos.CENTER_LEFT);
        steamLocationLabel.setWrapText(true);
        steamLocationLabel.setMouseTransparent(true);
        steamLocationLabel.setFocusTraversable(false);
        steamLocationLabel.getStyleClass().add("steam-location-box");

        Button steamButton = (steamIcon != null)
                ? new Button("Emplacement de Steam", steamIcon)
                : new Button("Emplacement de Steam");
        steamButton.getStyleClass().add("steam-button");
        steamButton.setContentDisplay(ContentDisplay.LEFT);
        steamButton.setGraphicTextGap(8);
        steamButton.setPrefSize(260, 50);

        steamButton.setOnAction(ev -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Sélectionner le dossier Steam (contenant steamapps)");
            java.io.File selected = null;
            javafx.stage.Window owner = null;
            try {
                owner = ((javafx.scene.Node) ev.getSource()).getScene().getWindow();
                selected = chooser.showDialog(owner);
            } catch (Exception ex) {
                selected = chooser.showDialog(null);
            }
            if (selected != null) {
                String folderName = selected.getName();

                if (!"steamapps".equalsIgnoreCase(folderName)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Emplacement invalide");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez sélectionner le dossier 'steamapps'.");
                    if (owner != null) {
                        alert.initOwner(owner);
                    }
                    alert.showAndWait();
                    return;
                }

                String path = selected.getAbsolutePath();
                steamLocationLabel.setText(path);

                launcher.setSteamLocation(path);

                launcher.setView("home");
            }
        });

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        HBox locationBox = new HBox(steamLocationLabel);
        locationBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(steamButton);
        buttonBox.setAlignment(Pos.CENTER);

        vb2.getChildren().addAll(title, description, spacer, locationBox, buttonBox);

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
