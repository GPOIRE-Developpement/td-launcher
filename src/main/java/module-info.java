module td.launcher.tdlauncher {
    requires javafx.controls;
    requires javafx.fxml;


    opens tdlauncher to javafx.fxml;
    exports tdlauncher;
}