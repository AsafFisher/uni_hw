package com.example.mmn14_2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Iterator;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox phones;

    @FXML
    private TextField fullNameTF;
    @FXML
    private TextField phoneNumberTF;

    PhoneBook phoneBook;

    @FXML
    protected void onAddPhoneAction() {
        try {
            phoneBook.add(fullNameTF.getText(), phoneNumberTF.getText());
        } catch (InvalidPhoneNumberException ex) {
            welcomeText.setText("Invalid Phone Number");
        }
        render();
    }

    @FXML
    protected void onSaveAction() {
        phoneBook.save();
    }

    @FXML
    protected void onLoadAction() {
        phoneBook.load();
        render();
    }

    @FXML
    protected void initialize() {
        phoneBook = new PhoneBook();
    }

    private void render() {
        this.phones.getChildren().clear();
        for (Iterator it = phoneBook.keyIterator(); it.hasNext(); ) {
            String name = (String) it.next();
            createPhoneIndex(name, phoneBook.get(name));
        }
    }

    protected void createPhoneIndex(String fullName, String phoneNumber) {
        HBox hbox = new HBox();
        hbox.setId(fullName);
        Region region1 = new Region();
        region1.setMinWidth(50);
        Region region2 = new Region();
        region2.setMinWidth(10);
        Button delBtn = new Button("Delete");
        delBtn.setId(fullName);
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button btn = (Button) actionEvent.getSource();
                phoneBook.remove(btn.getId());
                render();
            }
        });
        Button updateBtn = new Button("Update");
        updateBtn.setId(fullName);
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button btn = (Button) actionEvent.getSource();
                String phoneNumber = phoneBook.get(btn.getId());
                welcomeText.setText("Please type updated information and press the `add` button");
                fullNameTF.setText(btn.getId());
                phoneNumberTF.setText(phoneNumber);
                phoneBook.remove(btn.getId());
                render();
            }
        });
        hbox.getChildren().addAll(
                new Text(fullName),
                region1,
                new Text(phoneNumber),
                region2,
                delBtn,
                updateBtn);
        phones.getChildren().add(hbox);
    }
}