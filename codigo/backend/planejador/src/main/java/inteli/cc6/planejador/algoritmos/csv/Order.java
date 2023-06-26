package inteli.cc6.planejador.algoritmos.csv;

public class Order {

  private int size;

  private int quantity;

  private int priority;

  public Order(int size, int qty, int priority) {
    this.size = size;
    this.quantity = qty;
    this.priority = priority;
  }

  public int getSize() {
    return size;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return "Order [size=" + size + ", quantity=" + quantity + "]";
  }

}
