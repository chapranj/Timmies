package com.example.timmies;

import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class NumberButtons extends PaymentHandler{

    public NumberButtons() throws FileNotFoundException {
    }

    static Button button0 = new Button("0");
    static Button button1 = new Button("1");
    static Button button2 = new Button("2");
    static Button button3 = new Button("3");
    static Button button4 = new Button("4");
    static Button button5 = new Button("5");
    static Button button6 = new Button("6");
    static Button button7 = new Button("7");
    static Button button8 = new Button("8");
    static Button button9 = new Button("9");
    public static void setNumberButtons(){
        button1.prefWidthProperty().bind(bottomRightDisplay.widthProperty().multiply(0.28));
    }

}
