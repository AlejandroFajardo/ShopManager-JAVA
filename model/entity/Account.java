package polo.model.entity;

public class Account {
    
    private int creditCard;
    private int moneyAmount;
    private int idUser;

    public Account(int creditCard, int moneyAmount, int idUser) {
        this.creditCard = creditCard;
        this.moneyAmount = moneyAmount;
        this.idUser = idUser;
    }
    
    public void decreaseAmount(int amount){
        this.moneyAmount -= amount;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "tarjeta " + creditCard + " dinero " + moneyAmount + " usuario " + idUser;
    }
    
    
}
