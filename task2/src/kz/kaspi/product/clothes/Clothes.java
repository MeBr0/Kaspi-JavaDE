package kz.kaspi.product.clothes;

import kz.kaspi.product.Product;

import java.util.Objects;

/**
 * Base abstract class for clothes
 * Have own fields with enums {@link Size} and {@link Color}
 */
public abstract class Clothes extends Product {

    private Size size;
    private Color color;

    public Clothes(double price, String title, Size size, Color color) {
        super(price, title);
        this.size = size;
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Clothes))
            return false;

        if (!super.equals(o))
            return false;

        Clothes clothes = (Clothes) o;
        return size == clothes.size &&
                color == clothes.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, color);
    }

    @Override
    public String toString() {
        return super.toString() + ", size: " + size.shortName + ", color: " + color.value;
    }

    public enum Size {

        XS("XS", "Extra small"),
        S("S", "Small"),
        M("M", "Medium"),
        L("L", "Large"),
        XL("XL", "Extra large");

        private final String shortName;
        private final String title;

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

        private final String value;

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
