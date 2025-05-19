package com.continentalbhansa.model;

public class PaymentModel {
private String nameOnCard;
private String cardNumber;
private String expiryDate; // Format: MM/YY
private String cvv;
private String billingAddress;

public PaymentModel() {}

public PaymentModel(String nameOnCard, String cardNumber, String expiryDate, String cvv, String billingAddress) {
this.nameOnCard = nameOnCard;
this.cardNumber = cardNumber;
this.expiryDate = expiryDate;
this.cvv = cvv;
this.billingAddress = billingAddress;


}

// Getters and Setters
public String getNameOnCard() {
return nameOnCard;
}

public void setNameOnCard(String nameOnCard) {
this.nameOnCard = nameOnCard;
}

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String cardNumber) {
this.cardNumber = cardNumber;
}

public String getExpiryDate() {
return expiryDate;
}

public void setExpiryDate(String expiryDate) {
this.expiryDate = expiryDate;
}

public String getCvv() {
return cvv;
}

public void setCvv(String cvv) {
this.cvv = cvv;
}

public String getBillingAddress() {
return billingAddress;
}

public void setBillingAddress(String billingAddress) {
this.billingAddress = billingAddress;
}
}
