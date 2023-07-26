package com.example.timmies;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.example.timmies.TimsConsumables.*;
import com.example.timmies.TimsItems.*;

public class ButtonClassHC extends TimsPosHC {

    public Button button;
    public static HashMap<String , Boolean> majorMenuSelections =new HashMap<>();

    public static void initializeMajorButtonSelections(){
        for (String button : majorMenuSelections.keySet()){
            majorMenuSelections.put(button,false);
        }
    }

    public ButtonClassHC() throws FileNotFoundException {
    }

    public static VBox majorMenuButtonCreation() throws FileNotFoundException {
        VBox majorButtonContainer = new VBox();
        File f = new File("menuSmall.txt");
        Scanner reader = new Scanner(f);
        while(reader.hasNextLine()){
            String line = reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("majorButtons")){
                ArrayList<Button> majorButtonList = new ArrayList<>();
                boolean ifFirst = true;
                for (String typeOfButton : btnTexts){
                    if (ifFirst){
                        ifFirst = false;
                        continue;
                    }
                    Button majorButton = new Button(typeOfButton);
                    majorButton.getStyleClass().add("major-Menu-Button");
                    majorButton.setOnAction(e->{
                        try {
                            majorButtonHandler(typeOfButton);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    majorButtonList.add(majorButton);
                }
                majorButtonContainer.getChildren().addAll(majorButtonList);
            }
        }
        return majorButtonContainer;
    }

    public static void majorButtonHandler(String typeOfButton) throws FileNotFoundException {
        switch (typeOfButton){
            case("Hot Drinks"):
                hotDrinkButtonHandler();
            case("Cold Drinks"):
                coldDrinkButtonHandler();
        }
    }
    public static VBox hotDrinkButtonHandler() throws FileNotFoundException {
//        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        HBox smallMediumContainer = new HBox();
        HBox coffeeFrenchHotChocContainer = new HBox();
        VBox wholeHotDrinkSemiContainer= new VBox(20);
        ToggleGroup sMLX = new ToggleGroup();
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("semiHot")){
                ArrayList<RadioButton> semiHotbuttonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    RadioButton semiHotBtn = new RadioButton(btnTexts[i]);
                    semiHotBtn.getStyleClass().add("radio-Menu-Button");
                    semiHotBtn.setToggleGroup(sMLX);
                    semiHotbuttonsList.add(semiHotBtn);
                }
                smallMediumContainer.getChildren().addAll(semiHotbuttonsList);
                smallMediumContainer.setSpacing(10);
            }
            if (btnTexts[0].equals("semisemiHot")){
                ArrayList<Button> semisemiHotButtonsList = new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button semisemiHotBtn = new Button(btnTexts[i]);
                    semisemiHotBtn.getStyleClass().add("semi-Menu-Button");
                    semisemiHotButtonsList.add(semisemiHotBtn);
                }
                coffeeFrenchHotChocContainer.getChildren().addAll(semisemiHotButtonsList);
                coffeeFrenchHotChocContainer.setSpacing(10);
//                coffeeFrenchHotChocContainer.setPadding(new Insets(15));
            }
        }
        wholeHotDrinkSemiContainer.getChildren().addAll(smallMediumContainer,coffeeFrenchHotChocContainer);
//        wholeHotDrinkSemiContainer.setHgap(50);
//        wholeHotDrinkSemiContainer.setPadding();
        wholeHotDrinkSemiContainer.setPadding(new Insets(30, 10, 20, 20)); // Add padding to the right (10 pixels)
        return wholeHotDrinkSemiContainer;


    }
    public static VBox coldDrinkButtonHandler() throws FileNotFoundException {
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        HBox smallMediumContainerForCold = new HBox();
        HBox iceCoffeIceCappQuenchContainer = new HBox();
        VBox wholeColdDrinkSemiContainer= new VBox(20);
        ToggleGroup sMLXforCold = new ToggleGroup();
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("semiCold")){
                ArrayList<RadioButton> semiColdbuttonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    RadioButton semiColdBtn = new RadioButton(btnTexts[i]);
                    semiColdBtn.getStyleClass().add("radio-Menu-Button");
                    semiColdBtn.setToggleGroup(sMLXforCold);
                    semiColdbuttonsList.add(semiColdBtn);
                }
                smallMediumContainerForCold.getChildren().addAll(semiColdbuttonsList);
                smallMediumContainerForCold.setSpacing(10);
            }
            if (btnTexts[0].equals("semisemiCold")){
                ArrayList<Button> semisemiHotButtonsList = new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button semisemiColdBtn = new Button(btnTexts[i]);
                    semisemiColdBtn.getStyleClass().add("semi-Menu-Button");
                    semisemiHotButtonsList.add(semisemiColdBtn);
                }
                iceCoffeIceCappQuenchContainer.getChildren().addAll(semisemiHotButtonsList);
                iceCoffeIceCappQuenchContainer.setSpacing(10);
//                coffeeFrenchHotChocContainer.setPadding(new Insets(15));
            }
        }
        wholeColdDrinkSemiContainer.getChildren().addAll(smallMediumContainerForCold,iceCoffeIceCappQuenchContainer);
//        wholeHotDrinkSemiContainer.setHgap(50);
//        wholeHotDrinkSemiContainer.setPadding();
        wholeColdDrinkSemiContainer.setPadding(new Insets(30, 10, 20, 20)); // Add padding to the right (10 pixels)
        return wholeColdDrinkSemiContainer;

    }

}
