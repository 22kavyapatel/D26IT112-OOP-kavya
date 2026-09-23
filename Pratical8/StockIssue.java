class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        super("Out of stock. Shortfall = " + shortfall);
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException() {
        super("Quantity must be greater than 0");
    }
}

class Warehouse {
    int stock = 10;

    void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0)
            throw new InvalidQuantityException();

        if (qty > stock)
            throw new OutOfStockException(qty - stock);

        stock -= qty;
        System.out.println(item + " issued: " + qty);
    }
}

public class StockIssue {
    public static void main(String[] args) {
        Warehouse w = new Warehouse();

        int[] requests = {3, 5, 7, -2};

        for (int qty : requests) {
            try {
                w.issue("Laptop", qty);
            } catch (Exception e) {
                System.out.println("Request failed: " + e.getMessage());
            }
        }
    }
}

