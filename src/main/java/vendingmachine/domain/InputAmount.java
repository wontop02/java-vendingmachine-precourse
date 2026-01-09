package vendingmachine.domain;

public class InputAmount {
    private int amount;

    public InputAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void minusAmount(int price) {
        amount -= price;
    }
}
