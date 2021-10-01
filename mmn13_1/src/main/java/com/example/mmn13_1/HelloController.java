package com.example.mmn13_1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class HelloController {
    private static final Random rnd = new Random();
    @FXML
    private Label questionLabel;
    @FXML
    private Label successLabel;
    @FXML
    private ToggleGroup choices;
    @FXML
    private Button btnNext;

    QA[] questions;
    int questionIndex = -1;
    int questionsSucceeded = 0;

    @FXML
    protected void onNextBtnClick() {
        if(questionIndex >= 0){
            if(checkCorrect()){
                successLabel.setText("Success!");
                questionsSucceeded++;
            }else{
                successLabel.setText("Wrong Answer!");
            }
        }else{
            // If its the first time button was pressed show the RB.
            // This could have been done using two different buttons, but I've decided to use one.
            toggleRB(true);
        }
        if (questionIndex >= questions.length-1) {
            questionLabel.setText("You won! Success percentage " +
                    ((double)questionsSucceeded/(double)this.questions.length) * 100 + "%");
            initGame();
            btnNext.setText("Retry?");
            return;
        }

        questionIndex++;
        QA currentQuestion = this.questions[questionIndex];
        questionLabel.setText(currentQuestion.question);
        int answerIndex = 0;
        List<String> choices = new ArrayList(Arrays.asList(currentQuestion.answers));
        Collections.shuffle(choices, rnd);
        for (Toggle btn : this.choices.getToggles()) {
            // Assumes all buttons are radio buttons
            RadioButton rbtn = (RadioButton) btn;
            String str = choices.get(answerIndex);
            rbtn.setText(str);
            answerIndex++;
        }

    }

    @FXML
    public void initialize() {
        initGame();
        questionLabel.setText("Press start to start the game!");
        btnNext.setText("Press to start");
    }

    private void initGame(){
        questionIndex = -1;
        questionsSucceeded = 0;
        questions = null;
        toggleRB(false);
        this.questions = loadQA();
        if (this.questions == null) {
            questionLabel.setText("Could not load questions!");
        }
    }

    private QA[] loadQA() {
        String[] splits = null;
        try (FileInputStream fin = new FileInputStream("./questions.txt")) {
            byte[] data = fin.readAllBytes();
            splits = (new String(data)).split("\n");
            if (splits.length % 5 != 0) {
                return null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found..!!");

        } catch (IOException e) {
            System.out.println("An I/O Error Occurred..!!");

        }
        if (splits == null)
            return null;

        int questionCount = splits.length / 5;
        QA[] qas = new QA[questionCount];
        for (int i = 0; i < questionCount; i += 1) {
            int dataIndex = i * 5;
            String[] answers = Arrays.copyOfRange(splits, dataIndex + 1, dataIndex + 5);
            qas[i] = new QA(splits[dataIndex], answers);
        }
        return qas;
    }

    private boolean checkCorrect(){
        QA currentQuestion = this.questions[questionIndex];
        RadioButton btn = (RadioButton)this.choices.getSelectedToggle();
        return btn.getText().equals(currentQuestion.answers[0]);
    }

    private void toggleRB(boolean state){
        for(Toggle tg: choices.getToggles()){
            RadioButton rb = (RadioButton)tg;
            rb.setVisible(state);
        }
    }

}

class QA {
    String question;
    String[] answers;

    public QA(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }
}