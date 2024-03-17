package com.example.worldsimulationjava;

import com.example.worldsimulationjava.Organisms.Organism;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GUI
{
    static final Point2D BOARD_POS = new Point2D(2, 2);
    static final char BORDER_CHAR = '#';
    static final String AUTHOR = "Michal Pawiłojć, 193159";
    static final int MAX_LOG_CAPACITY = 10;
    public static final String DEFAULT_FIELD_STYLE =
            "-fx-background-color: gray; " +
                    "-fx-border-color: black; " +
                    "-fx-text-fill: white; ";
    public static final Color DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final Color DEFAULT_BG_COLOR = Color.GRAY;

    public static StringBuilder logMessage = new StringBuilder();
    public static Point2D pos = new Point2D(0, 0);

    public static void printToBoard(Point2D position, char ch, Color color)
    {
        if (position.getX() < 0 || position.getY() < 0)
            return;
        Board.getField(position).setBackground(new Background(new BackgroundFill(color, null, null)));
        Board.setFieldValue(position, ch);
    }

    static void render()
    {
        Label author = new Label(AUTHOR);
        author.setTextFill(Color.WHITE);
        author.setPadding(new Insets(0, 0, 10, 0));
        Window.borderPane.topProperty().set(author);
        Window.borderPane.rightProperty().set(generateLegend());
        Window.borderPane.centerProperty().set(generateBoard(Board.getWidth(), Board.getHeight()));
        Window.borderPane.bottomProperty().set(new Label(new String(new char[MAX_LOG_CAPACITY + 1]).replace('\0', '\n')));

    }

    private static GridPane generateBoard(int width, int height)
    {
        GridPane grid = new GridPane();
        grid.setLayoutX(10);
        grid.setLayoutY(50);
        grid.setHgap(2);
        grid.setVgap(2);

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                Label field = new Label();
                field.setPrefHeight(30);
                field.setPrefWidth(30);
                field.setAlignment(Pos.CENTER);
                field.setCursor(Cursor.HAND);
                field.setStyle(DEFAULT_FIELD_STYLE);

                ContextMenu contextMenu = new ContextMenu();

                for (Organism.OrganismType type : Organism.OrganismsNames.keySet())
                {
                    String name = Organism.OrganismsNames.get(type);
                    MenuItem item = new MenuItem(name);
                    int finalI = i;
                    int finalJ = j;
                    item.addEventHandler(ActionEvent.ACTION, event -> {
                        World.Get().getOrganisms().add(Organism.generateOrganism(type, new Point2D(finalI, finalJ)));
                        World.Get().drawOrganisms();
                    });
                    contextMenu.getItems().add(item);
                }
                field.contextMenuProperty().set(contextMenu);

                grid.add(field, i, j);
                Board.worldMap[i][j] = field;
            }
        }
        return grid;
    }

    private static VBox generateLegend()
    {
        Label legend = new Label();
        StringBuilder str = new StringBuilder();
        str.append("Legend: \n")
                .append("↑ - move up\n")
                .append("→ - move right\n")
                .append("↓ - move down\n")
                .append("← - move left\n")
                .append("u - use ult\n")
                .append("space - next turn\n")
                .append("s - save to file\n")
                .append("l - load from file\n");
        legend.setText(str.toString());
        legend.setTextFill(Color.WHITE);

        Button nextTurn = new Button("Next turn");
        nextTurn.setOnAction(e -> {
            World.Get().doTurn();
        });
        nextTurn.setFocusTraversable(false);


        VBox legendBox = new VBox();
        legendBox.setPadding(new Insets(0, 0, 0, 10));
        legendBox.getChildren().add(legend);
        legendBox.getChildren().add(nextTurn);

        return legendBox;
    }

    public static void printLogger(int turnsCounter)
    {
        Label logger = new Label();
        logger.setPadding(new Insets(10, 0, 0, 0));
        logger.setTextFill(Color.WHITE);

        StringBuilder str = new StringBuilder("Logger: (day " + turnsCounter + ")\n");
        String[] messages = logMessage.toString().split("\n");
        for (int i = 0; i < messages.length; i++)
        {
            str.append(messages[i]).append('\n');
            if (i > MAX_LOG_CAPACITY)
            {
                str.append("(And ").append(messages.length - i).append(" other messages...)");
                break;
            }
        }
        logger.setText(str.toString());
        Window.borderPane.bottomProperty().set(logger);
        logMessage.setLength(0);
        ;
    }

}





