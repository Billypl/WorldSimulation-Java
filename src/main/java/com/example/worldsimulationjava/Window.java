package com.example.worldsimulationjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application
{
    private static final String TITLE = "Test!";
    private static final Color BACKGROUND_COLOR = Color.DARKRED;
    public static BorderPane borderPane = new BorderPane();
    private final InputController inputController = new InputController();

    @Override
    public void start(Stage stage) throws IOException
    {
        Group root = new Group();
        Scene scene = new Scene(root, BACKGROUND_COLOR);
        borderPane.setPadding(new Insets(20));
        root.getChildren().add(borderPane);
        GUI.render();

        inputController.setControls(scene);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        World.Get().start();
    }

    public static void run(String[] args)
    {
        Application.launch(args);
    }
}
