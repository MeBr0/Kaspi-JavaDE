package kz.kaspi.third;

import java.util.Objects;
import java.util.UUID;

public class Clothes {

    private final String ID;
    private String title;
    private Size size;
    private double price;
    private Color color;
    private double purchaseValue;
    private int count;

    {
        ID = UUID.randomUUID().toString();
    }

    public Clothes(String title, Size size, double price, Color color, double purchaseValue, int count) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.size = Objects.requireNonNull(size, "Size cannot be null");
        this.price = Math.max(price, purchaseValue);
        this.color = Objects.requireNonNull(color, "Color cannot be null");
        this.purchaseValue = purchaseValue;
        this.count = count;
    }

    public double getPotentialProfit() {
        return price * count;
    }

    public int getMaxDiscount() {
        return (int) (1 - purchaseValue / price) * 100;
    }

    public String getId() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = Objects.requireNonNull(size, "Size cannot be null");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.max(price, purchaseValue);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = Objects.requireNonNull(color, "Color cannot be null");
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
        this.price = Math.max(price, this.purchaseValue);;
    }

    public int getCount() {
        return count;
    }

    public void updateCount(int value) {
        count += value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id: " + ID + ", title: " + title + ", price: " + price + "]";
    }

    public enum Size {

        XS("XS", "Extra small"),
        S("S", "Small"),
        M("M", "Medium"),
        L("L", "Large"),
        XL("XL", "Extra large");

        private String shortName;
        private String title;

        Size(String shortName, String title) {
            this.shortName = shortName;
            this.title = title;
        }

        public String getShortName() {
            return shortName;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return shortName;
        }
    }

    public enum Color {

        RED("Red"),
        GREEN("Green"),
        BLUE("Blue"),
        YELLOW("Yellow"),
        GREY("Grey"),
        BLACK("Black"),
        WHITE("White"),
        MIXED("Mixed");

        private String value;

        Color(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
