package com.example.timmies;

import com.example.timmies.TimsItems.OrderedButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class PaymentHandler extends ButtonClassHC{
    public PaymentHandler() throws FileNotFoundException {
    }
    public static Button toPay = new Button("Payment");
    public static FlowPane payOptions = new FlowPane();
    public static FlowPane paymentContainer = new FlowPane(Orientation.VERTICAL);
    public static VBox paymentPane = new VBox();

    static Button buttonClickedPreviously = null;


    private static Timeline tm;


    public static Label amtGiven = new Label("Enter amount paid: ");

    public static boolean paymentButtonCheck(){
        if (!orderedItems.isEmpty()){
            System.out.println("not empty");
            System.out.println(orderedItems);
            toPay.setDisable(false);
        }
        else{
            toPay.setDisable(true);
            semiMenu.getChildren().clear();
        }
        return orderedItems.isEmpty();
    }

    public static void payButtonClicked(Stage stage,Scene theScene) {
        System.out.println(calcTotal);
        paymentPane.getStyleClass().add("payment-Pane");
        paymentPane.setPrefWidth(majorMenu.getPrefWidth()+semiMenu.getPrefWidth());
        paymentPane.setPrefHeight(majorMenu.getPrefHeight());
        amtGiven.getStyleClass().add("enterMoneyPrompt");


        TimsPosHC.wholeContainer.getChildren().remove(majorMenu);
        TimsPosHC.wholeContainer.getChildren().remove(semiMenu);


        Button cashButton = new Button("Cash");
        Button cardButton = new Button("Card");
        Button goBack = new Button("Back");
        Button proceedToPayButton = new Button("Proceed");

        cashButton.getStyleClass().add("payment-Buttons");
        cardButton.getStyleClass().add("payment-Buttons");
        goBack.getStyleClass().add("payment-Buttons");
        proceedToPayButton.getStyleClass().add("payment-Buttons");

//        proceedToPayButton.getStyleClass().add("payment-Buttons");

//        proceedToPayButton.setOnAction(null);

        TextField amtGivenNumber = new TextField();

        if(payOptions.getChildren().isEmpty()) {
            payOptions.getChildren().addAll(cashButton, cardButton, goBack);
            payOptions.setPadding(new Insets(20));
            payOptions.setHgap(15);
        }

        Text paymentAnswer = new Text();
        Text changeAnswer = new Text();
        Text errorText = new Text();
//        errorText.setText("");

        paymentAnswer.getStyleClass().add("paymentTexts");
        changeAnswer.getStyleClass().add("paymentTexts");
        errorText.getStyleClass().add("errorTexts");

        if (paymentContainer.getChildren().isEmpty()) {
            paymentContainer.getChildren().addAll(amtGiven, amtGivenNumber, proceedToPayButton);
            paymentContainer.getChildren().addAll(paymentAnswer, changeAnswer);
            paymentContainer.getChildren().addAll(errorText);
            paymentContainer.setPadding(new Insets(20));
            paymentContainer.setVgap(10);
        }
        else {
            paymentContainer.getChildren().clear();
            paymentContainer.getChildren().addAll(amtGiven,amtGivenNumber,proceedToPayButton,paymentAnswer,
                    changeAnswer,errorText);
        }


        if (paymentPane.getChildren().isEmpty()) {
            paymentPane.getChildren().add(payOptions);
            TimsPosHC.wholeContainer.getChildren().add(paymentPane);
            System.out.println("is empty and hence added!");
        }
        else {
            System.out.println("it is not empty");
            paymentPane.getChildren().clear();
            paymentPane.getChildren().add(payOptions);
            TimsPosHC.wholeContainer.getChildren().add(paymentPane);
        }

        cardButton.setOnAction(actionEvent -> {
            buttonClickedPreviously=cardButton;
            System.out.println("card button is clicked!");
            paymentPane.getChildren().remove(paymentContainer);

            try {
                PrintWriter writer = new PrintWriter("C:/CollegeStuff/Semester2Sheridan/JAVA_2/Timmies/src" +
                        "/main/java/com/example/timmies/receipt.txt");
                PrintWriter historyWriter = new PrintWriter(new FileWriter("C:/CollegeStuff/Semester2Sheridan" +
                        "/JAVA_2/Timmies/orderHistory.txt",true));
                historyWriter.println("****************Order******************");
                for (OrderedButton ord : orderedItems) {
                    System.out.println(ord);
                    writer.println(ord);
                    historyWriter.println(ord);
                }
                historyWriter.println("Total:  -------------------  " + deciFor.format(calcTotal));
                historyWriter.println("****************Order******************\n");
                historyWriter.println();
                historyWriter.close();
                writer.println("Total:  -------------------  " + deciFor.format(calcTotal));
                writer.println("Paid by Card Successfully");
                writer.close();
                System.out.println("Ordered Items successfully written!");
                System.out.println(calcTotal);
            } catch (IOException e) {
                System.out.println("Error Generating the receipt");
                System.out.println(e.getMessage());
            }



            paymentPane.getChildren().add(paymentAnswer);
            paymentAnswer.setText("Payment processed successfully.....");
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), new KeyValue(paymentAnswer.textProperty(), "")));
            timeline.setOnFinished(actionEvent1 -> {
//                        paymentContainer.getChildren().clear();
//                        paymentPane.getChildren().remove(paymentContainer);
                topRightbox.getChildren().clear();
                setTotalTillNow();
                paymentPane.getChildren().clear();
                TimsPosHC.wholeContainer.getChildren().remove(paymentPane);
                TimsPosHC.wholeContainer.getChildren().addAll(majorMenu, semiMenu);
            });
            timeline.play();
            System.out.println(orderedItems);
            orderedItems.clear();
            calcTotal=0;
            paymentButtonCheck();
        });


            cashButton.setOnAction(event -> {
                System.out.println("cash button clicked");
                if (!(paymentPane.getChildren().contains(paymentContainer))) {
                    paymentPane.getChildren().add(paymentContainer);
                    System.out.println("payment pane does not have payment container");
                    buttonClickedPreviously= cashButton;
                }
            });


            goBack.setOnAction(actionEvent -> {
                System.out.println("go back button clicked");
//                paymentPane.getChildren().clear();
                TimsPosHC.wholeContainer.getChildren().remove(paymentPane);
                System.out.println(paymentPane.getChildren());
                System.out.println(paymentContainer.getChildren());
                TimsPosHC.wholeContainer.getChildren().addAll(majorMenu, semiMenu);
                errorText.setText("");
            });

            proceedToPayButton.setOnAction(actionEvent -> {
                System.out.println(amtGivenNumber.getText());
                System.out.println(calcTotal);
                System.out.println("proceedToPay button clicked");
                if (Double.parseDouble(amtGivenNumber.getText()) >= calcTotal) {
                    System.out.println("enough");
                    paymentAnswer.setText("Payment Processed..generating receipt.");
                    System.out.println("THE CHANGE: "+(Double.parseDouble(amtGivenNumber.getText()) - ButtonClassHC.calcTotal));
                    changeAnswer.setText("Change Given " + deciFor.format(Double.parseDouble(amtGivenNumber.getText()) - ButtonClassHC.calcTotal));
                    topRightbox.getChildren().clear();
                    try {
                        PrintWriter writer = new PrintWriter("C:/CollegeStuff/Semester2Sheridan/JAVA_2/Timmies/src" +
                                "/main/java/com/example/timmies/receipt.txt");
                        PrintWriter historyWriter = new PrintWriter(new FileWriter("C:/CollegeStuff/Semester2Sheridan" +
                                "/JAVA_2/Timmies/orderHistory.txt",true));
                        historyWriter.println("****************Order******************\n");
                        for (OrderedButton ord : orderedItems) {
                            System.out.println(ord);
                            writer.println(ord);
                            historyWriter.println(ord);
                        }
                        historyWriter.println("Total:  -------------------  " + deciFor.format(calcTotal));
                        historyWriter.println("****************Order******************\n");
                        historyWriter.println();
                        historyWriter.close();
                        writer.println("Total:  -------------------  " + deciFor.format(calcTotal));
                        writer.println("Change Given: -------------  "+ deciFor.format(Double.parseDouble(amtGivenNumber.getText()) - ButtonClassHC.calcTotal));
                        writer.close();
                        System.out.println("Ordered Items successfully written!");
                        System.out.println(calcTotal);
                    } catch (IOException e) {
                        System.out.println("Error Generating the receipt");
                        System.out.println(e.getMessage());
                    }


                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(2), new KeyValue(paymentAnswer.textProperty(), ""),
                                    new KeyValue(changeAnswer.textProperty(), ""))
                    );
                    timeline.setOnFinished(actionEvent1 -> {
                        amtGivenNumber.clear();
//                        paymentContainer.getChildren().clear();
//                        paymentPane.getChildren().remove(paymentContainer);
                        setTotalTillNow();
                        paymentPane.getChildren().clear();
                        TimsPosHC.wholeContainer.getChildren().remove(paymentPane);
                        TimsPosHC.wholeContainer.getChildren().addAll(majorMenu, semiMenu);
                    });
                    timeline.play();
                    System.out.println(orderedItems);
                    orderedItems.clear();
                    calcTotal=0;
                    paymentButtonCheck();

                }
                else {
                    errorText.setText("Not Enough to Complete the order!");
                    Timeline tm = new Timeline(new KeyFrame(Duration.seconds(2),
                            new KeyValue(errorText.textProperty(),"")));
                    tm.setOnFinished(actionEvent1 -> {
                        errorText.setText("");
                    });
                    tm.play();
                }

            });

//            toPay.setDisable(true);


    }


    public static void checkForPaymentContainer(){
        if (orderedItems.isEmpty()){
            paymentContainer.getChildren().clear();

        }
    }

}
