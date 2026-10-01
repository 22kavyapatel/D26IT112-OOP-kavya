package discount;

public class TenPercentDiscount {
    public static DiscountRule rule() {
        return price -> price - (price * 0.10);
    }
}