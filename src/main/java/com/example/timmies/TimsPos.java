package com.example.timmies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TimsPos extends Application {
    AnchorPane wholeContainer = new AnchorPane();
    VBox majorMenu = new VBox();
    FlowPane semiMenu = new FlowPane();
    ScrollPane topRightDisplay = new ScrollPane();
    GridPane bottomRightDisplay = new GridPane();
    File menu= new File("menuSmall.txt");
    Scanner reader = new Scanner(menu);
    String[] majorMenuBtns = reader.nextLine().split(",");
    String[] semiMenuButtonsHot = reader.nextLine().split(",");

    String[] semiSemiHotButtons= reader.nextLine().split(",");
    String[] semiMenuButtonsCold = reader.nextLine().split(",");

    String[] semiSemiColdButtons = reader.nextLine().split(",");
    Parent[] paneArray= {majorMenu,semiMenu, topRightDisplay ,bottomRightDisplay};



    public boolean hotDrinksAdded=false;
    public boolean coldDrinksAdded=false;

    public boolean semiSemiHotAdded=false;

//    HashMap<String , ArrayList<String>> semiMenuList= new HashMap<>();

    public TimsPos() throws FileNotFoundException {
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
//        FXMLLoader fxmlLoader = new FXMLLoader(TimsPos.class.getResource("TimsPos.fxml"));


        ArrayList <Button> majorMenuButtonArray= new ArrayList<>();


        for (String typeOfButton:majorMenuBtns){
            Button majorButton= new Button(typeOfButton);
            majorButton.setOnAction(e->{
                majorButtonsHandler(typeOfButton);
            });
            majorButton.getStyleClass().add("major-Menu-Button");
            majorMenu.getChildren().add(majorButton);

        }



        stage.setScene(theScene);
        stage.setTitle("TIms");
        stage.show();

    }


    public void hotDrinkButtonHandler(){
        boolean firstIteration=true;
        if ( !hotDrinksAdded ) {
            for (String semiMenuButton : semiMenuButtonsHot) {
                if (firstIteration){
                    firstIteration=false;
                    continue;
                }
                Button semiButton = new Button(semiMenuButton);
                semiMenu.getChildren().add(semiButton);
                semiButton.setOnAction(e->{
                    addSemiSemiHot();
                });
            }
            hotDrinksAdded = true;
        }
        else {
            System.out.println("Not Hot Drink!");
        }
    }
    public void coldDrinkButtonHandler(){
        boolean firstIteration=true;
        if ( !coldDrinksAdded ) {
            for (String semiMenuButton : semiMenuButtonsCold) {
                if (firstIteration){
                    firstIteration=false;
                    continue;
                }
                Button semiButton = new Button(semiMenuButton);
                semiMenu.getChildren().add(semiButton);
            }
            coldDrinksAdded = true;
        }
        else{
            System.out.println("Not Cold Drink!");
        }
    }
    public void majorButtonsHandler(String typeOfButton){
        switch (typeOfButton){
            case("Hot Drinks"):
                hotDrinkButtonHandler();
            case("Cold Drinks"):
                coldDrinkButtonHandler();
        }
    }
    public void addSemiSemiHot(){

    }

}


