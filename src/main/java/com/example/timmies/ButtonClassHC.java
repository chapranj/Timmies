package com.example.timmies;

import com.example.timmies.TimsItems.OrderedButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Flow;


public class ButtonClassHC extends TimsPosHC {
    public static double calcTotal;

    public static String toConc;

    public static ToggleGroup sMLX = new ToggleGroup();


    public static ArrayList<OrderedButton> orderedItems = new ArrayList<>();


    public ButtonClassHC() throws FileNotFoundException {
    }

    public static HashMap <String, Double> priceMap =  new HashMap<>();
    public static HashMap <String, Integer> buttonIndex =  new HashMap<>();

    public static void readPrices() throws FileNotFoundException {
        File prices= new File("prices.txt");
        Scanner pReader = new Scanner(prices);

        while(pReader.hasNextLine()) {
            String priceline = pReader.nextLine();
            String[] itemsNPrices = priceline.split(",");
//          System.out.println(itemsNPrices.toString());
            System.out.println(itemsNPrices.length);
            for (String s : itemsNPrices) {
                System.out.println(s);
            }
            String item = itemsNPrices[0];
            double price = Double.parseDouble(itemsNPrices[1]);
            priceMap.put(item, price);
        }
    }


    public static VBox majorMenuButtonCreation() throws FileNotFoundException {
        TimsPosHC.bottomRightDisplay.getChildren().add(toPay);
        toPay.setDisable(true);
        int majorButtonIndex = 0;
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
//                    TimsPosHC.paymentButton.setDisable(true);

                    int finalMajorButtonIndex = majorButtonIndex;
                    majorButton.setOnAction(e->{
                        try {
                            TimsPosHC.onMajorButtonClicked(finalMajorButtonIndex);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    buttonIndex.put(typeOfButton,majorButtonIndex);
                    majorButtonList.add(majorButton);
                    majorButtonIndex++;
                }
                majorButtonContainer.getChildren().addAll(majorButtonList);

            }
        }
        majorButtonContainer.setSpacing(20);
        readPrices();
        return majorButtonContainer;
    }


    public static VBox hotDrinkButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        HBox smallMediumContainer = new HBox();
        VBox coffeeFrenchHotChocContainer = new VBox();
        VBox wholeHotDrinkSemiContainer= new VBox(20);


        while(reader.hasNextLine()) {

            String line = reader.nextLine();
            String[] btnTexts = line.split(",");

            ArrayList<RadioButton> semiHotbuttonsList = new ArrayList<>();
            ArrayList<String> semiHotbuttonsTextsList = new ArrayList<>();

            if (btnTexts[0].equals("semiHot")) {
                for (int i = 1; i < btnTexts.length; i++){
                    RadioButton semiHotBtn = new RadioButton(btnTexts[i]);
                    semiHotBtn.setToggleGroup(sMLX);
                    semiHotBtn.getStyleClass().add("radio-Menu-Button");

                    if (semiHotBtn.getText().equals("Medium ")){
                        semiHotBtn.setSelected(true);
                    }
                    if (semiHotBtn.isSelected()){
                        toConc = semiHotBtn.getText();
                    }

                    semiHotBtn.setOnAction(e -> {

                        if (semiHotBtn.isSelected()){
                           toConc = semiHotBtn.getText();
                            System.out.println(toConc);
                        }

                        priceMap.forEach((key, value) -> System.out.println(key + ": " + value));

                    });
                    semiHotbuttonsList.add(semiHotBtn);
//                    semiHotbuttonsTextsList.add(semiHotBtn.getText());
                }
                smallMediumContainer.getChildren().addAll(semiHotbuttonsList);
                smallMediumContainer.setSpacing(10);
            }
            if (btnTexts[0].equals("semisemiHot")) {
                ArrayList<Button> semisemiHotButtonsList = new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button semisemiHotBtn = new Button(btnTexts[i]);
                    semisemiHotBtn.setOnAction(e -> {

                        double value=0;

                        if (!toConc.equals("")) {
                            toConc += semisemiHotBtn.getText();
                            System.out.println(toConc);
                            if (priceMap.containsKey(toConc)) {
                                value = priceMap.get(toConc);

                                System.out.print(toConc+" : "+value);

                                OrderedButton orderedButton = new OrderedButton(toConc,value);
                                Label addedLabel= orderedButton.makeButton();
                                Button cancelButton = new Button("Cancel");
                                addedLabel.getStyleClass().add("");
                                addedLabel.setGraphic(cancelButton);
                                addedLabel.setGraphicTextGap(10);
                                addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                                TimsPosHC.topRightbox.getChildren().add(addedLabel);
                                calcTotal+=orderedButton.itemPrice;
                                orderedItems.add(orderedButton);

                                System.out.println();
                                System.out.println(calcTotal);
                                cancelButton.setOnAction(ev->{
                                    TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                    calcTotal-=orderedButton.itemPrice;
                                    System.out.println(calcTotal);
                                    orderedItems.remove(orderedButton);
                                    paymentButtonCheck();
                                });
                                System.out.println(paymentButtonCheck());

                                System.out.println(orderedItems.toString());

                                toConc="";

                            }
                        }

                        TimsPosHC.semiMenu.getChildren().clear();

                    });

                    semisemiHotBtn.getStyleClass().add("semi-Menu-Button");
                    semisemiHotButtonsList.add(semisemiHotBtn);
                }
                coffeeFrenchHotChocContainer.getChildren().addAll(semisemiHotButtonsList);
                coffeeFrenchHotChocContainer.setSpacing(10);

            }
        }
        wholeHotDrinkSemiContainer.getChildren().addAll(smallMediumContainer,coffeeFrenchHotChocContainer);

        wholeHotDrinkSemiContainer.setPadding(new Insets(30, 10, 20, 20)); // Add padding to the right (10 pixels)
        return wholeHotDrinkSemiContainer;
    }

    public static VBox coldDrinkButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        HBox smallMediumContainerForCold = new HBox();
        VBox iceCoffeIceCappQuenchContainer = new VBox();
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

                    if (semiColdBtn.getText().equals("Medium ")){
                        semiColdBtn.setSelected(true);
                    }

                    if (semiColdBtn.isSelected()){
                        toConc = semiColdBtn.getText();
                        System.out.println(toConc);
                    }

                    semiColdBtn.setOnAction(e -> {

                        if (semiColdBtn.isSelected()){
                            toConc = semiColdBtn.getText();
                            System.out.println(toConc);
                        }
                        priceMap.forEach((key, value) -> System.out.println(key + ": " + value));
                    });


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

                    semisemiColdBtn.setOnAction(e -> {

                        double value=0;

                        if (!toConc.equals("")) {
                            toConc += semisemiColdBtn.getText();
                            System.out.println(toConc);
                            if (priceMap.containsKey(toConc)) {
                                value = priceMap.get(toConc);

                                System.out.print(toConc+" : "+value);

                                OrderedButton orderedButton = new OrderedButton(toConc,value);
                                Label addedLabel= orderedButton.makeButton();
                                Button cancelButton = new Button("Cancel");
                                addedLabel.setGraphic(cancelButton);
                                addedLabel.setGraphicTextGap(10);
                                addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                                TimsPosHC.topRightbox.getChildren().add(addedLabel);
                                calcTotal+=orderedButton.itemPrice;
                                orderedItems.add(orderedButton);
                                System.out.println();
                                System.out.println(calcTotal);
                                cancelButton.setOnAction(ev->{
                                    TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                    calcTotal-=orderedButton.itemPrice;
                                    System.out.println(calcTotal);
                                    orderedItems.remove(orderedButton);
                                });
                                System.out.println(orderedItems.toString());

                                toConc="";

                            }
                        }

                        TimsPosHC.semiMenu.getChildren().clear();

                    });


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

    public static VBox food1ButtonHandler() throws FileNotFoundException {
        System.out.println("food1 pressed");
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood1SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food1")){
                ArrayList<Button> food1ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food1btn = new Button(btnTexts[i]);
                    food1btn.getStyleClass().add("semi-Menu-Button");
                    food1ButtonsList.add(food1btn);


                    food1btn.setOnAction(ev->{
                        double value=0;
                        toConc = food1btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });
                            System.out.println(orderedItems.toString());

                        }
                    });


                }
                wholeFood1SemiContainer.getChildren().addAll(food1ButtonsList);
            }
        }
        return wholeFood1SemiContainer;

    }

    public static VBox food2ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood2SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food2")){
                ArrayList<Button> food2ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food2btn = new Button(btnTexts[i]);
                    food2btn.getStyleClass().add("semi-Menu-Button");
                    food2ButtonsList.add(food2btn);

                    food2btn.setOnAction(ev->{
                        double value=0;
                        toConc = food2btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });

                        }
                    });




                }
                wholeFood2SemiContainer.getChildren().addAll(food2ButtonsList);
            }
        }
        return wholeFood2SemiContainer;

    }

    public static VBox food3ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood3SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food3")){
                ArrayList<Button> food3ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food3btn = new Button(btnTexts[i]);
                    food3btn.getStyleClass().add("semi-Menu-Button");
                    food3ButtonsList.add(food3btn);

                    food3btn.setOnAction(ev->{
                        double value=0;
                        toConc = food3btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });

                        }
                    });



                }
                wholeFood3SemiContainer.getChildren().addAll(food3ButtonsList);
            }
        }
        return wholeFood3SemiContainer;

    }

    public static VBox food4ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood4SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food4")){
                ArrayList<Button> food4ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food4btn = new Button(btnTexts[i]);
                    food4btn.getStyleClass().add("semi-Menu-Button");
                    food4ButtonsList.add(food4btn);

                    food4btn.setOnAction(ev->{
                        double value=0;
                        toConc = food4btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                orderedItems.remove(orderedButton);
                                System.out.println(calcTotal);
                            });

                        }
                    });



                }
                wholeFood4SemiContainer.getChildren().addAll(food4ButtonsList);
            }
        }
        return wholeFood4SemiContainer;

    }
    public static VBox food5ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood5SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food5")){
                ArrayList<Button> food5ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food5btn = new Button(btnTexts[i]);
                    food5btn.getStyleClass().add("semi-Menu-Button");
                    food5ButtonsList.add(food5btn);

                    food5btn.setOnAction(ev->{
                        double value=0;
                        toConc = food5btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });

                        }
                    });
                }
                wholeFood5SemiContainer.getChildren().addAll(food5ButtonsList);
            }
        }
        return wholeFood5SemiContainer;

    }

    public static VBox food6ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood6SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food6")){
                ArrayList<Button> food6ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food6btn = new Button(btnTexts[i]);
                    food6btn.getStyleClass().add("semi-Menu-Button");
                    food6ButtonsList.add(food6btn);
                    food6btn.setOnAction(ev->{
                        double value=0;
                        toConc = food6btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });

                        }
                    });


                }
                wholeFood6SemiContainer.getChildren().addAll(food6ButtonsList);
            }
        }
        return wholeFood6SemiContainer;

    }

    public static VBox food7ButtonHandler() throws FileNotFoundException {
        TimsPosHC.semiMenu.getChildren().clear();
        File f= new File("menuSmall.txt");
        Scanner reader= new Scanner(f);
        VBox wholeFood7SemiContainer= new VBox(20);
        while(reader.hasNextLine()){
            String line= reader.nextLine();
            String[] btnTexts = line.split(",");
            if (btnTexts[0].equals("food7")){
                ArrayList<Button> food7ButtonsList= new ArrayList<>();
                for (int i = 1; i < btnTexts.length; i++) {
                    Button food7btn = new Button(btnTexts[i]);
                    food7btn.getStyleClass().add("semi-Menu-Button");
                    food7ButtonsList.add(food7btn);

                    food7btn.setOnAction(ev->{
                        double value=0;
                        toConc = food7btn.getText();
                        if (priceMap.containsKey(toConc)){
                            System.out.println("success");
                            value = priceMap.get(toConc);
                            System.out.println(toConc+" : "+value);

                            OrderedButton orderedButton= new OrderedButton(toConc,value);
                            Label addedLabel = orderedButton.makeButton();
                            Button cancelButton = new Button("Cancel");
                            addedLabel.setGraphic(cancelButton);
                            addedLabel.setGraphicTextGap(10);
                            addedLabel.setContentDisplay(ContentDisplay.RIGHT);
                            TimsPosHC.topRightbox.getChildren().add(addedLabel);
                            calcTotal+=orderedButton.itemPrice;
                            orderedItems.add(orderedButton);
                            System.out.println();
                            System.out.println(calcTotal);
                            cancelButton.setOnAction(eve->{
                                TimsPosHC.topRightbox.getChildren().remove(addedLabel);
                                calcTotal-=orderedButton.itemPrice;
                                System.out.println(calcTotal);
                                orderedItems.remove(orderedButton);
                            });

                        }
                    });


                }
                wholeFood7SemiContainer.getChildren().addAll(food7ButtonsList);
            }
        }
        return wholeFood7SemiContainer;

    }

    public static boolean paymentButtonCheck(){
        if (!orderedItems.isEmpty()){
            System.out.println("not empty");
            System.out.println(orderedItems);
            toPay.setDisable(false);
        }
        else{
            toPay.setDisable(true);
        }
        return orderedItems.isEmpty();
    }
    public static void payButtonClicked() {
//        TimsPosHC.bottomRightDisplay.getChildren().add(paymentButton);
        FlowPane paymentContainer = new FlowPane();
        Button cashButton = new Button("Cash");
        Button cardButton = new Button("Card");
        Button goBack = new Button("Back");
        Label amtGiven = new Label("Enter amount paid: ");
        TextField amtGivenNumber= new TextField();
        Button proceedToPayButton = new Button("Proceed");
        paymentContainer.getChildren().addAll(amtGiven,amtGivenNumber,proceedToPayButton);
        FlowPane paidPane = new FlowPane();
        cashButton.setOnAction(event->{
            TimsPosHC.bottomRightDisplay.getChildren().add(paymentContainer);
        });



        proceedToPayButton.setOnAction(actionEvent -> {
            if (Double.parseDouble(amtGivenNumber.getText()) >= calcTotal){
                Text processed = new Text("Payment processed...change given "+ (Double.parseDouble(amtGivenNumber.getText())-calcTotal));
                paidPane.getChildren().add(processed);
                System.out.println(processed.getText());
                try {
                    TimsPosHC.bottomRightDisplay.getChildren().add(paidPane);
                }catch (Exception e){
                    System.out.println("pressed again should not do it! ");
                }
                for (OrderedButton ord: orderedItems){
                    System.out.println(ord);
                }
            }
        });



        semiMenu.getChildren().clear();
        semiMenu.getChildren().add(cashButton);
        semiMenu.getChildren().add(cardButton);
        semiMenu.getChildren().add(goBack);


        System.out.println("Pay");

    }
}
