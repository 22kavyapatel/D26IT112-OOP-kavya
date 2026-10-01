package discount;

public class TwentyPercentDiscount {
    public static DiscountRule rule() {
        return price -> price - (price * 0.20);
    }
}