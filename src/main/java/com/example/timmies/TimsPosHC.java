package com.example.timmies;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
import com.example.timmies.TimsConsumables.HotBeverages.*;

public class TimsPosHC extends Application {
//    public static Button paymentButton = new Button("Pay");
    AnchorPane wholeContainer = new AnchorPane();
    public static VBox majorMenu = new VBox();
    static FlowPane semiMenu = new FlowPane();
    static ScrollPane topRightDisplay = new ScrollPane();
    static FlowPane bottomRightDisplay = new FlowPane();

    public static Button toPay = new Button("Payment");

    static VBox topRightbox = new VBox();
//    public static Button paymentButton = new Button("Pay");

    FlowPane paymentButtonsContainer = new FlowPane();

    File menu= new File("menuSmall.txt");
    Scanner reader = new Scanner(menu);

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
        topRightDisplay.setContent(topRightbox);

        topRightbox.setSpacing(10);


        bottomRightDisplay.prefWidthProperty().bind(theScene.widthProperty().multiply(0.3));
        bottomRightDisplay.prefHeightProperty().bind(theScene.heightProperty().multiply(0.5));
//        bottomRightDisplay.getChildren().add(toPay);

        AnchorPane.setLeftAnchor(semiMenu, majorMenu.getPrefWidth() + 10);
        AnchorPane.setLeftAnchor(topRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setLeftAnchor(bottomRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setTopAnchor(bottomRightDisplay, topRightDisplay.getPrefHeight() + 10);

        majorMenu.getChildren().addAll(ButtonClassHC.majorMenuButtonCreation());


        stage.setScene(theScene);
        stage.setTitle("Tims");
        stage.show();

    }
    public static void onMajorButtonClicked(int index) throws FileNotFoundException {
        switch(index){
            case(0):
                semiMenu.getChildren().addAll(ButtonClassHC.hotDrinkButtonHandler());
                break;
            case(1):
                semiMenu.getChildren().addAll(ButtonClassHC.coldDrinkButtonHandler());
                break;
            case(2):
                semiMenu.getChildren().addAll(ButtonClassHC.food1ButtonHandler());
                break;
            case(3):
                semiMenu.getChildren().addAll(ButtonClassHC.food2ButtonHandler());
                break;
            case(4):
                semiMenu.getChildren().addAll(ButtonClassHC.food3ButtonHandler());
                break;
            case(5):
                semiMenu.getChildren().addAll(ButtonClassHC.food4ButtonHandler());
                break;
            case(6):
                semiMenu.getChildren().addAll(ButtonClassHC.food5ButtonHandler());
                break;
            case(7):
                semiMenu.getChildren().addAll(ButtonClassHC.food6ButtonHandler());
                break;
            case(8):
                semiMenu.getChildren().addAll(ButtonClassHC.food7ButtonHandler());
                break;

        }

    }


}
