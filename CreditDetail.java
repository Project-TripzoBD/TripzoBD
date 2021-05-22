package tripzobd.com;

public class CreditDetail {
    public String cardNumber;
    public String cardDate;
    public String cvvNumber;
    public String cardName;

    public CreditDetail(String cardNumber, String cardDate, String cvvNumber) {
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cvvNumber = cvvNumber;
    }
}
