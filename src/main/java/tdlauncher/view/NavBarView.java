package tdlauncher.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import tdlauncher.util.Image;

public class NavBarView {
    public static Node display() {
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(90, 720);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #212437;");
        vBox.setPrefSize(90, 720);

        BorderPane iconPane = new BorderPane();
        iconPane.setPrefSize(90, 110);

        ImageView appIcon = Image.display("/tdlauncher/images/icons/app_icon.png");
        appIcon.setFitWidth(50);
        appIcon.setFitHeight(50);

        iconPane.setCenter(appIcon);

        Node homeButton = NavBarButton.displayButton("/tdlauncher/images/icons/home.png");

        Node serversButton = NavBarButton.displayButton("/tdlauncher/images/icons/servers.png");

        VBox homeBox = new VBox();
        homeBox.getChildren().addAll(homeButton, serversButton);

        vBox.getChildren().addAll(iconPane, homeBox);
        pane.getChildren().addAll(vBox);

        return pane;
    }
}
