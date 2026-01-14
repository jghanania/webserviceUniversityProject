package edu.fra.uas.ConverterService.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

/**
 * Used as a data model to ensure valid inputs.
 * The constraints are taken from the jakarta.validation.constraints libraries.
 */
public class ConversionRequestModel {
    @NotNull(message = "Die Ausgangswährung darf nicht null sein")
    private String from;

    @NotNull(message = "Die Zielwährung darf nicht null sein")
    private String to;

    @Min(value = 1, message = "Der Betrag muss mindestens 1 sein")
    private double amount;

    // Getter und Setter
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
