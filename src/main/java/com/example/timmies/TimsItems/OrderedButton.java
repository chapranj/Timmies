package com.example.timmies.TimsItems;

import com.example.timmies.ButtonClassHC;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

public class OrderedButton {

    public OrderedButton() throws FileNotFoundException {
    }

    public Label orderedLabel;
    public  String itemName;
    public  double itemPrice;
//    public static final String cancel="X";

    public  double getItemPrice() {
        return itemPrice;
    }
    public OrderedButton(String itemName, double itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public  String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return this.itemName+"  -------   "+this.itemPrice;
    }
    public void checkTotal(){
        if (ButtonClassHC.orderedItems.isEmpty()){
            ButtonClassHC.calcTotal = 0;
        }
    }
    public Label makeButton(){
        orderedLabel = new Label(this.toString());
        orderedLabel.getStyleClass().add("label-Display");
        return orderedLabel;
    }

}
