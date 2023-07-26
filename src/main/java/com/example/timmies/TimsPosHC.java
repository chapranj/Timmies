package com.example.timmies;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.example.timmies.TimsConsumables.*;
import com.example.timmies.TimsItems.*;

public class TimsPosHC extends Application {
    AnchorPane wholeContainer = new AnchorPane();
    VBox majorMenu = new VBox();
    static FlowPane semiMenu = new FlowPane();
    ScrollPane topRightDisplay = new ScrollPane();
    GridPane bottomRightDisplay = new GridPane();
    File menu= new File("menuSmall.txt");
    Scanner reader = new Scanner(menu);
//    String[] majorMenuBtns = reader.nextLine().split(",");
//    String[] semiMenuButtonsHot = reader.nextLine().split(",");
//
//    String[] semiSemiHotButtons= reader.nextLine().split(",");
//    String[] semiMenuButtonsCold = reader.nextLine().split(",");
//    String[] semiSemiColdButtons = reader.nextLine().split(",");

    public TimsPosHC() throws FileNotFoundException {
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

        majorMenu.getChildren().addAll(ButtonClassHC.majorMenuButtonCreation());
//        semiMenu.getChildren().addAll(ButtonClassHC.majorButtonHandler();)
//        FlowPane smallMediumContainerForHD =  ButtonClassHC.hotDrinkButtonHandler();
//        semiMenu.getChildren().add(smallMediumContainerForHD);
        stage.setScene(theScene);
        stage.setTitle("Tims");
        stage.show();

    }

}
