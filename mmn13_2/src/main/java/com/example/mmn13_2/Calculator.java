package com.example.mmn13_2;


public class Calculator {
    double accumulated = 0;
    double inputNumber = 0;
    boolean negativeInputNumber = false;
    int floatingPointIndex = 0;
    boolean floatingPointInput = false;
    boolean firstNumber = true;
    char lastOperation = '?';

    public double parse(String input) {
        try {
            int num = Integer.parseInt(input);
            return digestNumber(num);
        } catch (NumberFormatException ex) {
            return digestFunction(input);
        }
    }

    private double digestNumber(int num) {

        // If the number is typed after an onary operator its a new calculation.
        if(lastOperation != '?' && (this.lastOperation == '=' || this.lastOperation == 'n'))
        {
            this.firstNumber = true;
            this.lastOperation = '?';
        }
        this.inputNumber = this.inputNumber * 10 + num;
        if (this.floatingPointInput) {
            this.floatingPointIndex--;
        }
        double finalNumber = this.inputNumber * Math.pow(10, this.floatingPointIndex);
        finalNumber = this.negativeInputNumber ? -finalNumber : finalNumber;
        return finalNumber;
    }
    private double digestFunction(String input) {

        // If we expect a number don't treat any function. This prevents states like 4**2 or 3.*2

        this.floatingPointInput = false;
        switch (input) {
            case "=":
                double finalNumber = this.inputNumber * Math.pow(10, this.floatingPointIndex);
                finalNumber = this.negativeInputNumber ? -finalNumber : finalNumber;
                doBinaryOperation(finalNumber);

                // Restore state to default so next number can be received
                this.lastOperation = '=';
                this.inputNumber = 0;
                this.floatingPointIndex = 0;
                return this.accumulated;
            case "+/-":
                if (lastOperation == '=' || lastOperation == 'n') {
                    // If last operation is '=' the negation operator should apply to the accumulated result.
                    this.accumulated = -this.accumulated;
                    this.lastOperation = 'n';
                    return this.accumulated;
                }
                // Apply negation on the inputNumber.
                this.negativeInputNumber = !this.negativeInputNumber;
                return this.negativeInputNumber ? -this.inputNumber : this.inputNumber;
            case ".":
                if(this.lastOperation == '=')
                    return this.accumulated;
                // Start counting floating point.
                this.floatingPointInput = true;

                // Return full number state.
                return this.inputNumber * Math.pow(10, this.floatingPointIndex);
            default:
                // If falls here the next operation is a binary operation (+, -, /, etc...)
                binaryOperation(input.charAt(0));

                // New number needs to be received, reset.
                this.inputNumber = 0;
                this.floatingPointIndex = 0;
                return this.accumulated;
        }
    }

    private void binaryOperation(char input) {

        // Calculate the full received number
        double finalNumber = this.inputNumber * Math.pow(10, this.floatingPointIndex);
        finalNumber = this.negativeInputNumber ? -finalNumber : finalNumber;

        // If its the first number typed, add it as the accumulated number
        if (this.firstNumber) {
            this.accumulated = finalNumber;
            this.firstNumber = false;
            this.negativeInputNumber = false;
        } else {
            // Calculate this.accumulated = this.accumulated @ this.inputNumber.
            doBinaryOperation(finalNumber);
        }
        this.lastOperation = input;
    }

    private void doBinaryOperation(double finalNumber) {
        // this.accumulated = this.accumulated @ finalNumber
        // @ could be any binary operation.
        switch (this.lastOperation) {
            case '/':
                this.accumulated /= finalNumber;
                break;
            case '*':
                this.accumulated *= finalNumber;
                break;
            case '-':
                this.accumulated -= finalNumber;
                break;
            case '+':
                this.accumulated += finalNumber;
                break;
            default:
                break;
        }
    }
}
