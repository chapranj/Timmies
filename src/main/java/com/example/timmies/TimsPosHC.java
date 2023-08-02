package com.example.timmies;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
    public static AnchorPane wholeContainer = new AnchorPane();
    public static VBox majorMenu = new VBox();
    static FlowPane semiMenu = new FlowPane();
    static ScrollPane topRightDisplay = new ScrollPane();
    static GridPane bottomRightDisplay = new GridPane();

    static GridPane numbersContainer = new GridPane();
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

        Scene theScene = new Scene(wholeContainer, 1300, 800);

        theScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        majorMenu.prefWidthProperty().bind(theScene.widthProperty().multiply(0.25));
        majorMenu.prefHeightProperty().bind(theScene.heightProperty());

        semiMenu.prefWidthProperty().bind(theScene.widthProperty().multiply(0.4));
        semiMenu.prefHeightProperty().bind(theScene.heightProperty());
        semiMenu.setAlignment(Pos.TOP_CENTER);

        topRightDisplay.prefWidthProperty().bind(theScene.widthProperty().multiply(0.3));
        topRightDisplay.prefHeightProperty().bind(theScene.heightProperty().multiply(0.5));
        topRightDisplay.setContent(topRightbox);

        topRightbox.setSpacing(10);


        bottomRightDisplay.prefWidthProperty().bind(theScene.widthProperty().multiply(0.3));
        bottomRightDisplay.prefHeightProperty().bind(theScene.heightProperty().multiply(0.5));

//        bottomRightDisplay.getChildren().add(toPay);
        PaymentHandler.toPay.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button0.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button1.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button2.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button3.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button4.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button5.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button6.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button7.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button8.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));
        NumberButtons.button9.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.34));

        PaymentHandler.toPay.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button0.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button1.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button2.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button3.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button4.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button5.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button6.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button7.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button8.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        NumberButtons.button9.prefHeightProperty().bind(bottomRightDisplay.widthProperty().multiply(0.24));
        bottomRightDisplay.setHgap(2);

        bottomRightDisplay.add(PaymentHandler.toPay,0,0);
        bottomRightDisplay.add(NumberButtons.button0,1,0);
        bottomRightDisplay.add(NumberButtons.button1,0,3);
        bottomRightDisplay.add(NumberButtons.button2,1,3);
        bottomRightDisplay.add(NumberButtons.button3,2,3);
        bottomRightDisplay.add(NumberButtons.button4,0,2);
        bottomRightDisplay.add(NumberButtons.button5,1,2);
        bottomRightDisplay.add(NumberButtons.button6,2,2);
        bottomRightDisplay.add(NumberButtons.button7,0,1);
        bottomRightDisplay.add(NumberButtons.button8,1,1);
        bottomRightDisplay.add(NumberButtons.button9,2,1);
        bottomRightDisplay.add(ButtonClassHC.totalTillNow,2,0);

//        numbersContainer.add(button1,0,0);
//        bottomRightDisplay.getChildren().add(numbersContainer);
        PaymentHandler.toPay.getStyleClass().add("payment-Button");
        PaymentHandler.toPay.setDisable(true);
        PaymentHandler.toPay.setOnAction(actionEvent -> {
            System.out.println("Payment button clicked");
            PaymentHandler.payButtonClicked(stage,theScene);
        });

        AnchorPane.setLeftAnchor(semiMenu, majorMenu.getPrefWidth() + 10);
        AnchorPane.setLeftAnchor(topRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setLeftAnchor(bottomRightDisplay, majorMenu.getPrefWidth() + semiMenu.getPrefWidth() + 20);
        AnchorPane.setTopAnchor(bottomRightDisplay, topRightDisplay.getPrefHeight() + 10);

        majorMenu.getChildren().addAll(ButtonClassHC.majorMenuButtonCreation());

        stage.setTitle("POS Cashier Application");
        stage.setScene(theScene);
//        stage.setTitle("Tim Hortons");
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
