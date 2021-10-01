package com.example.mmn13_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label screen;
    Calculator calc;
    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        double state = calc.parse(clickedButton.getText());
        screen.setText(String.valueOf(state));

    }
    @FXML
    protected void initialize(){
        calc = new Calculator();
    }
}