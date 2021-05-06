package dataproviders;

public class CreditCard {

    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCard(String cardNumber, String expiryDate, String cvv){

        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }
}
