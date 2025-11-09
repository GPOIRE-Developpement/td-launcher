module td.launcher.tdlauncher {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.httpserver;
    requires javafx.web;
    requires org.json;

    opens tdlauncher to javafx.fxml;

    exports tdlauncher;
    exports tdlauncher.model;
    exports tdlauncher.controller;
    exports tdlauncher.auth;
}