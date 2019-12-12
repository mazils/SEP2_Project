package client.model.spareParts;

public interface ISparePart {
    void setQuantity(int quantity);
    int getQuantity();
    String getName();
    int getAmountNeeded();
    void increment();
    void receivedAmount(int amount);
}
