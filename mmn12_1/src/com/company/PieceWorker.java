package com.company;

public class PieceWorker extends Employee{
    private int amountProduced;
    private double productPrice;
    public PieceWorker(String firstName, String lastName, String socialSecurityNumber, BirthDate birthDate, int amountProduced, double productPrice) {
        super(firstName, lastName, socialSecurityNumber, birthDate);
        this.amountProduced = amountProduced;
        this.productPrice = productPrice;
    }

    public int getAmountProduced() {
        return amountProduced;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public double earnings() {
        return this.amountProduced * this.productPrice;
    }

    @Override
    public String toString() {
        return String.format("piece worker: %s%namount produced: %s; product price: $%,.2f",
                super.toString(), this.getAmountProduced(), this.getProductPrice());
    }
}
