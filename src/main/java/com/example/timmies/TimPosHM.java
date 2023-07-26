package com.example.timmies;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TimPosHM extends Application {
    AnchorPane wholeContainer = new AnchorPane();
    VBox majorMenu = new VBox();
    FlowPane semiMenu = new FlowPane();
    ScrollPane topRightDisplay = new ScrollPane();
    GridPane bottomRightDisplay = new GridPane();
    File menu= new File("menuSmall.txt");
    Scanner reader = new Scanner(menu);

    HashMap<String,ArrayList<String>> subMenus = new HashMap<>();

    public TimPosHM() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws Exception {

        wholeContainer.getChildren().addAll(majorMenu, semiMenu);
        majorMenu.setId("major-Menu");
        semiMenu.setId("semi-Menu");
        topRightDisplay.setId("top-Right-Display");
        bottomRightDisplay.setId("bottom-Right-Display");

        wholeContainer.getChildren().add(topRightDisplay);
        wholeContainer.getChildren().add(bottomRightDisplay);

        Scene theScene = new Scene(wholeContainer, 1000, 600);

        theScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        majorMenu.prefWidthProperty().bind(theScene.widthProperty().multiply(0.25));
        majorMenu.prefHeightProperty().bind(theScene.heightProperty());

        semiMenu.prefWidthProperty().bind(theScene.widthProperty().multiply(0.4));
        semiMenu.prefHeightProperty().bind(theScene.heightProperty());

        topRightDisplay.prefWidthProperty().bind(theScene.widthProperty().multiply(0.3));
        topRightDisplay.prefHeightProperty().bind(theScene.heightProperty().multiply(0.5));

        bottomRightDisplay.prefWidthProperty().bind(theScene.widthProperty().multiply(0.3));
        bottomRightDisplay.prefHeightProperty().bind(theScene.heightProperty().multiply(0.5));

        AnchorPane.setLeftAnchor(semiMenu, majorMenu.getPrefWidth() + 10);
        AnchorPane.setLeftAnchor(topRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setLeftAnchor(bottomRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setTopAnchor(bottomRightDisplay, topRightDisplay.getPrefHeight() + 10);

        String[] majorMenuBtns= reader.nextLine().split(",");

        for (String majorButton : majorMenuBtns) {
            String subMenusLine = reader.nextLine();
            String[] subMenuButtons = subMenusLine.split(",");
            ArrayList<String> subMenuList = new ArrayList<>();
            for (int i = 1; i < subMenuButtons.length; i++) {
                subMenuList.add(subMenuButtons[i]);
            }
            subMenus.put(majorButton, subMenuList);
        }


        stage.setScene(theScene);
        stage.setTitle("Tims");
        stage.show();



    }
}
